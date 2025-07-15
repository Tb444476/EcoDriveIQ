// document.addEventListener("DOMContentLoaded", fetchVehicles);

// function fetchVehicles() {
//   const userId = localStorage.getItem("userId");
//   if (!userId) {
//     alert("Login first!");
//     window.location.href = "index.html";
//     return;
//   }

//   fetch(`http://localhost:8080/api/vehicles/user/${userId}`)
//     .then(res => res.json())
//     .then(data => {
//       const list = document.getElementById("vehicleList");
//       list.innerHTML = '';
//       data.forEach(v => {
//         const li = document.createElement("li");
//         li.innerHTML = `${v.make} ${v.model} (${v.year}) - ${v.fuelType} 
//           <button onclick="deleteVehicle(${v.id})">Delete</button>`;
//         list.appendChild(li);
//       });
//     });
// }

// function addVehicle() {
//   const userId = localStorage.getItem("userId");
//   if (!userId) return;

//   const make = document.getElementById("make").value;
//   const model = document.getElementById("model").value;
//   const year = parseInt(document.getElementById("year").value);
//   const fuelType = document.getElementById("fuelType").value;
//   const registrationNumber = document.getElementById("registrationNumber").value;
//   const color = document.getElementById("color").value;

//   fetch("http://localhost:8080/api/vehicles", {
//     method: "POST",
//     headers: { "Content-Type": "application/json" },
//     body: JSON.stringify({
//       make, model, year, fuelType, registrationNumber, color, userId
//     })
//   }).then(res => {
//     if (!res.ok) throw new Error("Vehicle creation failed");
//     fetchVehicles(); // Refresh list
//   }).catch(err => alert(err.message));
// }

// function deleteVehicle(id) {
//   fetch(`http://localhost:8080/api/vehicles/${id}`, {
//     method: "DELETE"
//   }).then(() => fetchVehicles());
// }


const apiBase = "http://localhost:8080/api/vehicles";
const userId = localStorage.getItem("userId");

if (!userId) {
  alert("Please log in first.");
  window.location.href = "index.html";
}

// Load vehicles
function loadVehicles() {
  fetch(`${apiBase}/user/${userId}`)
    .then(res => res.json())
    .then(data => {
      const tbody = document.getElementById("vehicleTableBody");
      tbody.innerHTML = "";
      data.forEach((v, index) => {
        tbody.innerHTML += `
          <tr>
            <td>${index + 1}</td>
            <td>${v.make}</td>
            <td>${v.model}</td>
            <td>${v.year}</td>
            <td>${v.fuelType}</td>
            <td>${v.registrationNumber}</td>
            <td>${v.color}</td>
            <td>
              <button class="btn btn-sm btn-warning me-2" onclick='editVehicle(${JSON.stringify(v)})'>
                <i class="fas fa-edit"></i>
              </button>
              <button class="btn btn-sm btn-danger" onclick="deleteVehicle(${v.id})">
                <i class="fas fa-trash"></i>
              </button>
            </td>
          </tr>`;
      });
    });
}
window.onload = loadVehicles;

// Handle Add/Edit Vehicle Form
document.getElementById("vehicleForm").addEventListener("submit", function (e) {
  e.preventDefault();
  const id = document.getElementById("vehicleId").value;

  const vehicle = {
    make: document.getElementById("make").value,
    model: document.getElementById("model").value,
    year: parseInt(document.getElementById("year").value),
    fuelType: document.getElementById("fuelType").value,
    registrationNumber: document.getElementById("registrationNumber").value,
    color: document.getElementById("color").value,
    userId: userId
  };

  const method = id ? "PUT" : "POST";
  const url = id ? `${apiBase}/${id}` : `${apiBase}`;

  fetch(url, {
    method: method,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(vehicle)
  })
    .then(res => {
      if (!res.ok) throw new Error("Operation failed");
      return res.json();
    })
    .then(() => {
      loadVehicles();
      document.getElementById("vehicleForm").reset();
      document.getElementById("vehicleId").value = "";
      bootstrap.Modal.getInstance(document.getElementById("vehicleModal")).hide();
    })
    .catch(err => alert(err.message));
});

// Edit Handler
function editVehicle(vehicle) {
  document.getElementById("vehicleId").value = vehicle.id;
  document.getElementById("make").value = vehicle.make;
  document.getElementById("model").value = vehicle.model;
  document.getElementById("year").value = vehicle.year;
  document.getElementById("fuelType").value = vehicle.fuelType;
  document.getElementById("registrationNumber").value = vehicle.registrationNumber;
  document.getElementById("color").value = vehicle.color;

  new bootstrap.Modal(document.getElementById("vehicleModal")).show();
}

// Delete
function deleteVehicle(id) {
  fetch(`${apiBase}/${id}`, { method: "DELETE" })
    .then(() => loadVehicles());
}