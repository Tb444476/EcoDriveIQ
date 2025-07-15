document.addEventListener("DOMContentLoaded", () => {
  const userId = localStorage.getItem("userId");
  if (!userId) {
    alert("Login required.");
    window.location.href = "index.html";
    return;
  }

  fetch(`http://localhost:8080/api/users/${userId}`)
    .then(res => res.json())
    .then(user => {
      document.getElementById("profileDetails").innerHTML = `
        <p><strong>Full Name:</strong> ${user.name}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Phone:</strong> ${user.phoneNumber || '-'}</p>
        <p><strong>Organization:</strong> ${user.organizationName || '-'}</p>
        <p><strong>User Type:</strong> ${user.userType || '-'}</p>
        <p><strong>Address:</strong> ${user.address || '-'}</p>
        <p><strong>Joined:</strong> ${new Date(user.registrationDate).toLocaleDateString() || '-'}</p>
      `;
    });
});

function deleteAccount() {
  if (!confirm("Are you sure you want to delete your account permanently?")) return;

  const userId = localStorage.getItem("userId");
  fetch(`http://localhost:8080/api/users/${userId}`, {
    method: "DELETE"
  })
    .then(res => {
      if (res.ok) {
        alert("Account deleted successfully.");
        localStorage.clear();
        window.location.href = "index.html";
      } else {
        alert("Failed to delete account.");
      }
    });
}

function logout() {
  localStorage.clear();
}
