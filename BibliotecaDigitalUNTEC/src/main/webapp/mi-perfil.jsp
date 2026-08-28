<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
<%@ include file="components/meta.jspf"%>
<title>Mi Perfil - Biblioteca Digital UNTEC</title>
<meta name="description"
	content="Biblioteca virtual de la Universidad Nacional TecnolÃ³gica">
</head>

<body>
	<%@ include file="components/navbar.jspf"%>
	<main class="container my-4">
		<section class="my-2">
			<div class="card">
				<div class="card-header">
					<h2>Mi Perfil</h2>
				</div>
				<div class="card-body">
					<form action="#" method="post" id="perfil-form">
						<div class="mb-3">
							<label for="nombre" class="form-label">Nombre</label> <input
								type="text" class="form-control" id="nombre" name="nombre"
								value="${usuario.nombre}" required>
						</div>
						<div class="mb-3">
							<label for="email" class="form-label">Correo ElectrÃ³nico</label>
							<input type="email" class="form-control" id="email" name="email"
								value="${usuario.email}" required>
						</div>
						<button type="submit" class="btn btn-primary">Actualizar
							Perfil</button>
					</form>
				</div>
			</div>
		</section>
	</main>
	<%@ include file="components/footer.jspf"%>

	<%@ include file="components/scripts.jspf"%>
</body>

</html>