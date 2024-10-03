// Toggle between login and register forms
const loginForm = document.getElementById('loginForm');
const registerForm = document.getElementById('registerForm');

document.getElementById('showRegister').addEventListener('click', function() {
    loginForm.classList.add('hidden');
    registerForm.classList.remove('hidden');
});

document.getElementById('showLogin').addEventListener('click', function() {
    registerForm.classList.add('hidden');
    loginForm.classList.remove('hidden');
});

// Handle form submissions
document.getElementById('login').addEventListener('submit', function(event) {
    event.preventDefault();
    const username = document.getElementById('loginUsername').value;
    const password = document.getElementById('loginPassword').value;

    fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    })
    .then(response => response.json())
    .then(data => {
        if (data.jwt) {
            alert('Login successful');
            localStorage.setItem('token', data.jwt); // Store JWT in local storage
        } else {
            alert('Login failed');
        }
    })
    .catch(error => console.error('Error:', error));
});

document.getElementById('register').addEventListener('submit', function(event) {
    event.preventDefault();
    const username = document.getElementById('registerUsername').value;
    const password = document.getElementById('registerPassword').value;

    fetch('http://localhost:8080/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    })
    .then(response => response.text())
    .then(message => {
        alert(message);
        document.getElementById('showLogin').click(); // Switch to login form after successful registration
    })
    .catch(error => console.error('Error:', error));
});
