document.addEventListener('DOMContentLoaded', function() {
    fetchAlumnos();
});

function fetchAlumnos() {
    fetch('http://localhost:8080/alumno/traer')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            if (!Array.isArray(data)) {
                throw new Error('Data is not an array');
            }
            const alumnosTableBody = document.getElementById('alumnos-body');
            alumnosTableBody.innerHTML = ''; // Limpia la tabla antes de agregar nuevos datos

            data.forEach(alumno => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${alumno.id}</td>
                    <td>${alumno.nombre}</td>
                    <td>${alumno.apellidoPaterno}</td>
                    <td>${alumno.apellidoMaterno}</td>
                    <td>${alumno.direccion}</td>
                    <td>${alumno.carrera}</td>
                    <td>${alumno.num}</td>
                `;
                alumnosTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Error fetching alumnos:', error));
}
