let selectedVehicleId = null;
const charts = {};

document.addEventListener("DOMContentLoaded", () => {
  const userId = localStorage.getItem("userId");
  if (!userId) {
    alert("Login required.");
    window.location.href = "index.html";
    return;
  }

  fetch(`http://localhost:8080/api/vehicles/user/${userId}`)
    .then(res => res.json())
    .then(data => {
      const dropdown = document.getElementById("vehicleDropdown");
      dropdown.innerHTML = '';
      data.forEach(vehicle => {
        const option = document.createElement("option");
        option.value = vehicle.id;
        option.text = `${vehicle.make} ${vehicle.model} (${vehicle.year})`;
        dropdown.appendChild(option);
      });
      selectedVehicleId = dropdown.value;
      dropdown.addEventListener("change", () => {
        selectedVehicleId = dropdown.value;
        loadMetrics();
      });

      if (selectedVehicleId) loadMetrics();
    });

  // Automatically simulate metrics every 30 seconds
  setInterval(() => {
    if (selectedVehicleId) {
      simulateMetric();
    }
  }, 30000);
});

function simulateMetric() {
  if (!selectedVehicleId) return;
  fetch(`http://localhost:8080/api/metrics/simulate/${selectedVehicleId}`, {
    method: "POST"
  }).then(() => loadMetrics());
}

// function loadMetrics() {
//   fetch(`http://localhost:8080/api/metrics/vehicle/${selectedVehicleId}`)
//     .then(res => res.json())
//     .then(data => {
//       data.sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp));
//       const labels = data.map(d => new Date(d.timestamp).toLocaleTimeString());
//       updateChart("fuelChart", labels, data.map(d => d.fuelUsed), "Fuel Used (liters)");
//       updateChart("emissionChart", labels, data.map(d => d.emissions), "Emissions (g/km)");
//       updateChart("speedChart", labels, data.map(d => d.speed), "Speed (km/h)");
//       updateChart("tempChart", labels, data.map(d => d.engineTemp), "Engine Temp (°C)");
//     });
//   loadEcoScore(selectedVehicleId);
//   loadAlerts(selectedVehicleId);
// }


// function loadMetrics() {
//   fetch(`http://localhost:8080/api/metrics/vehicle/${selectedVehicleId}`)
//     .then(res => res.json())
//     .then(data => {
//       const recentData = data.slice(-20); // <-- Only last 20 points
//       const labels = recentData.map(d => new Date(d.timestamp).toLocaleTimeString());
//       updateChart("fuelChart", labels, recentData.map(d => d.fuelUsed), "Fuel Used (liters)");
//       updateChart("emissionChart", labels, recentData.map(d => d.emissions), "Emissions (g/km)");
//       updateChart("speedChart", labels, recentData.map(d => d.speed), "Speed (km/h)");
//       updateChart("tempChart", labels, recentData.map(d => d.engineTemp), "Engine Temp (°C)");
//     });
//   loadEcoScore(selectedVehicleId);
//   loadAlerts(selectedVehicleId);
// }
function loadMetrics() {
  fetch(`http://localhost:8080/api/metrics/vehicle/${selectedVehicleId}`)
    .then(res => res.json())
    .then(data => {
      // ✅ Sort the full dataset by timestamp ascending
      const sortedData = data.sort((a, b) => new Date(a.timestamp) - new Date(b.timestamp));

      // ✅ Take the last 20 entries from the sorted array
      const recentData = sortedData.slice(-20);

      // ✅ Generate labels and values in proper chronological order
      const labels = recentData.map(d => new Date(d.timestamp).toLocaleTimeString());
      updateChart("fuelChart", labels, recentData.map(d => d.fuelUsed), "Fuel Used (liters)");
      updateChart("emissionChart", labels, recentData.map(d => d.emissions), "Emissions (g/km)");
      updateChart("speedChart", labels, recentData.map(d => d.speed), "Speed (km/h)");
      updateChart("tempChart", labels, recentData.map(d => d.engineTemp), "Engine Temp (°C)");
    });
    
  loadEcoScore(selectedVehicleId);
  loadAlerts(selectedVehicleId);
}


function updateChart(canvasId, labels, data, label) {
  const ctx = document.getElementById(canvasId).getContext("2d");

  // Destroy previous chart if it exists
  if (charts[canvasId]) {
    charts[canvasId].destroy();
  }

  // Create and save new chart
  charts[canvasId] = new Chart(ctx, {
    type: "line",
    data: {
      labels,
      datasets: [{
        label,
        data,
        fill: false,
        borderColor: "blue",
        tension: 0.1
      }]
    }
  });
}

function loadEcoScore(vehicleId) {
  fetch(`http://localhost:8080/api/eco-score/calculate/${vehicleId}`, {
    method: "POST"
  })
    .then(() => {
      return fetch(`http://localhost:8080/api/eco-score/${vehicleId}`);
    })
    .then(res => res.json())
    .then(score => {
      if (!score || typeof score.score !== "number") {
        throw new Error("Invalid score object");
      }
      document.getElementById("ecoScoreText").innerText = `${score.score.toFixed(2)}`;
    })
    .catch(err => {
      console.error("EcoScore error:", err);
      document.getElementById("ecoScoreText").innerText = "Not available";
    });
}



function loadAlerts(vehicleId) {
  fetch(`http://localhost:8080/api/alerts/vehicle/${vehicleId}`)
    .then(res => res.json())
    .then(alerts => {
      const list = document.getElementById("alertsList");
      list.innerHTML = "";

      // Sort alerts by timestamp descending and take latest 5
      const latestAlerts = alerts
        .sort((a, b) => new Date(b.timestamp) - new Date(a.timestamp))
        .slice(0, 5);

      latestAlerts.forEach(alert => {
        const li = document.createElement("li");
        li.classList.add("list-group-item", "list-group-item-danger");
        li.innerText = `${alert.alertType || 'Alert'} - ${alert.message} (${new Date(alert.timestamp).toLocaleString()})`;

       
        list.appendChild(li);
      });
    })
    .catch(err => {
      console.error("Alerts error:", err);
    });
}
