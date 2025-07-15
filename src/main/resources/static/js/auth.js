// Login function
function loginUser() {
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  fetch("http://localhost:8080/api/users/login", {
    method: "POST",
    headers: {'Content-Type': 'application/json'},
    body: JSON.stringify({email, password})
  }).then(response => {
    if (!response.ok) throw new Error("Login failed");
    return response.json();
  }).then(data => {
    localStorage.setItem("userId", data.id);
    window.location.href = "home.html";
  }).catch(err => alert(err.message));
}

function logoutUser() {
  localStorage.removeItem("userId");
  window.location.href = "index.html";
}


// Register function
// function registerUser() {
//   const name = document.getElementById("name").value;
//   const email = document.getElementById("email").value;
//   const password = document.getElementById("password").value;

//   fetch("http://localhost:8080/api/users/register", {
//     method: "POST",
//     headers: {'Content-Type': 'application/json'},
//     body: JSON.stringify({name, email, password})
//   }).then(response => {
//     if (!response.ok) throw new Error("Registration failed");
//     alert("Registered successfully. You can now log in.");
//     window.location.href = "index.html";
//   }).catch(err => alert(err.message));
// }
function registerUser(event) {
  event.preventDefault(); // Prevent form reload

  const form = document.getElementById("registerForm");
  const formData = new FormData(form);
  const data = Object.fromEntries(formData.entries());

  fetch("http://localhost:8080/api/users/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  })
    .then(response => {
      if (!response.ok) throw new Error("Registration failed");
      alert("Registered successfully. You can now log in.");
      window.location.href = "index.html";
    })
    .catch(err => alert(err.message));
}
