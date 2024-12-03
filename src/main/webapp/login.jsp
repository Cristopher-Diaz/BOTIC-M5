<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Horóscopo Chino - Inicio de sesión</title>
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
		</div>
	</nav>

	<!-- Main Content -->
	<div class="container my-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="text-center mb-4">
					<h1 class="h3">Inicio de sesión</h1>
					<p>
						En caso de no tener un usuario puede crear uno haciendo click <a
							href="CreateUser">aquí</a>
					</p>
				</div>
				<div class="card shadow p-4">
					<div class="text-center mb-4">
						<i class="bi bi-person-circle text-primary"
							style="font-size: 4rem;"></i>
					</div>
					<form action="Login" method="post">
						<div class="mb-3">
							<label for="userName" class="form-label">Nombre de
								usuario</label> <input type="text" class="form-control" id="userName"
								name="userName" placeholder="Ingrese su nombre de usuario"
								required>
						</div>
						<div class="mb-3">
							<label for="password" class="form-label">Contraseña</label> <input
								type="text" class="form-control" id="password" name="password"
								placeholder="Ingrese su contraseña" required>
						</div>
						<div class="d-grid">
							<button type="submit" class="btn btn-primary">Iniciar
								sesión</button>
						</div>
					</form>
				</div>
				<!-- Alertas -->
				<c:if test="${not empty alertMessage}">
					<div class="alert alert-${alertVariant} mt-2" role="alert">
						${alertMessage}</div>
				</c:if>
			</div>
		</div>
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
