<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Horóscopo Chino - Panel principal</title>
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
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item"><a class="nav-link active" href="Logout">Cerrar
							Sesión</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Main Content -->
	<div class="container my-5">
		<section class="py-5">
			<div class="container text-center">
				<h2 class="fw-bold">Bienvenido ${userName}</h2>
				<p class="text-muted">Panel de administrador</p>
				<div class="row mt-5">
					<div class="col-md-6 col-lg-3">
						<div class="d-flex align-items-center justify-content-center mb-3">
							<i class="bi bi-star fs-2 text-primary"></i>
						</div>
						<h5 class="fw-bold">Bootstrap 5</h5>
						<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis
							vitae leo.</p>
					</div>
					<div class="col-md-6 col-lg-3">
						<div class="d-flex align-items-center justify-content-center mb-3">
							<i class="bi bi-pencil fs-2 text-primary"></i>
						</div>
						<h5 class="fw-bold">Customizable</h5>
						<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis
							vitae leo.</p>
					</div>
					<div class="col-md-6 col-lg-3">
						<div class="d-flex align-items-center justify-content-center mb-3">
							<i class="bi bi-phone fs-2 text-primary"></i>
						</div>
						<h5 class="fw-bold">Responsive</h5>
						<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis
							vitae leo.</p>
					</div>
					<div class="col-md-6 col-lg-3">
						<div class="d-flex align-items-center justify-content-center mb-3">
							<i class="bi bi-arrow-repeat fs-2 text-primary"></i>
						</div>
						<h5 class="fw-bold">All Browser Compatibility</h5>
						<p class="text-muted">Lorem ipsum dolor sit amet, consectetur
							adipiscing elit. Nunc quam urna, dignissim nec auctor in, mattis
							vitae leo.</p>
					</div>
				</div>
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
