<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Horóscopo Chino - Listado de usuarios</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa; /* Color de fondo claro */
	display: grid;
	min-height: 100dvh;
	grid-template-rows: auto 1fr auto;
}

.card {
	border-radius: 15px;
}

.footer {
	background-color: #212529; /* Color oscuro */
	color: #ffffff; /* Texto blanco */
	padding: 10px 0;
}
</style>
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
		<div class="container">
			<a class="navbar-brand" href="Login">Horóscopo Chino</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link active" href="Login">Volver</a></li>
					<li class="nav-item"><a class="nav-link active" href="Logout">Cerrar
							Sesión</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Main Content -->
	<div class="container my-5">
		<section class="py-5">
			<%@ taglib prefix="c" uri="jakarta.tags.core"%>

			<!-- Main Content -->
			<div class="container my-5">
				<section class="py-5">
					<h2 class="text-center mb-4">Lista de Usuarios</h2>
					<div class="table-responsive">
						<table class="table table-striped table-bordered">
							<thead class="thead-dark">
								<tr>
									<th>ID</th>
									<th>Nombre</th>
									<th>Username</th>
									<th>Email</th>
									<th>Fecha de Nacimiento</th>
									<th>Password</th>
									<th>Animal</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="usuario" items="${usuarios}">
									<tr>
										<td>${usuario.id}</td>
										<td>${usuario.nombre}</td>
										<td>${usuario.fechaNacimiento}</td>
										<td>${usuario.email}</td>
										<td>${usuario.fechaNacimiento}</td>
										<td>${usuario.password}</td>
										<td>${usuario.animal}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</section>
			</div>

		</section>

	</div>

	<!-- Footer -->
	<footer class="footer text-center">
		<div class="container">
			<p class="mb-0">© 2024 Evaluación final M5 - Bootcamp Java</p>
		</div>
	</footer>

	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>