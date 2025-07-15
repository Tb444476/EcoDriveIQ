let vehicles = [];

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
      vehicles = data;
      populateDropdown("vehicle1Dropdown", data);
      populateDropdown("vehicle2Dropdown", data);

      document.getElementById("vehicle1Dropdown").addEventListener("change", compareVehicles);
      document.getElementById("vehicle2Dropdown").addEventListener("change", compareVehicles);

      compareVehicles(); // Initial load
    });
});

function populateDropdown(id, vehicles) {
  const dropdown = document.getElementById(id);
  dropdown.innerHTML = `<option value="">-- Select Vehicle --</option>`;
  vehicles.forEach(v => {
    const option = document.createElement("option");
    option.value = v.id;
    option.text = `${v.make} ${v.model} (${v.year})`;
    dropdown.appendChild(option);
  });
}

function compareVehicles() {
  const v1Id = document.getElementById("vehicle1Dropdown").value;
  const v2Id = document.getElementById("vehicle2Dropdown").value;

  if (!v1Id || !v2Id || v1Id === v2Id) return;

  Promise.all([
    fetch(`http://localhost:8080/api/metrics/vehicle/${v1Id}`).then(r => r.json()),
    fetch(`http://localhost:8080/api/metrics/vehicle/${v2Id}`).then(r => r.json()),
    fetch(`http://localhost:8080/api/eco-score/${v1Id}`).then(r => r.json()),
    fetch(`http://localhost:8080/api/eco-score/${v2Id}`).then(r => r.json())
  ]).then(([data1, data2, score1, score2]) => {
    const labels1 = data1.map(d => new Date(d.timestamp).toLocaleTimeString());
    const labels2 = data2.map(d => new Date(d.timestamp).toLocaleTimeString());
    const labels = labels1.length > labels2.length ? labels1 : labels2;

    updateChart("fuelChart", labels, data1.map(d => d.fuelUsed), data2.map(d => d.fuelUsed), "Fuel Used (L)");
    updateChart("emissionChart", labels, data1.map(d => d.emissions), data2.map(d => d.emissions), "Emissions (g/km)");
    updateChart("speedChart", labels, data1.map(d => d.speed), data2.map(d => d.speed), "Speed (km/h)");
    updateChart("tempChart", labels, data1.map(d => d.engineTemp), data2.map(d => d.engineTemp), "Engine Temp (°C)");

    document.getElementById("ecoScoreCompareText").innerText =
      `EcoScore: Vehicle 1 → ${score1.score.toFixed(2)} | Vehicle 2 → ${score2.score.toFixed(2)}`;
  });
}

const charts = {};
function updateChart(canvasId, labels, data1, data2, label) {
  const ctx = document.getElementById(canvasId).getContext("2d");
  if (charts[canvasId]) charts[canvasId].destroy();

  charts[canvasId] = new Chart(ctx, {
    type: "line",
    data: {
      labels,
      datasets: [
        {
          label: "Vehicle 1",
          data: data1,
          borderColor: "blue",
          backgroundColor: "rgba(0, 0, 255, 0.2)",
          fill: false,
          tension: 0.2
        },
        {
          label: "Vehicle 2",
          data: data2,
          borderColor: "green",
          backgroundColor: "rgba(0, 255, 0, 0.2)",
          fill: false,
          tension: 0.2
        }
      ]
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: "top"
        }
      }
    }
  });
}

function logout() {
  localStorage.clear();
}
