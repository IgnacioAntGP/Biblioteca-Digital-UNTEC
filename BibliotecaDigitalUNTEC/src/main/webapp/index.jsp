<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
<%@ include file="components/meta.jspf"%>
<title>Login - Biblioteca Digital UNTEC </title>
<meta name="description"
	content="Biblioteca virtual de la Universidad Nacional Tecnológica">
</head>

<body>
	<%@ include file="components/navbar.jspf"%>
	<main class="container my-4">
		<div class="card">
			<div class="card-body">
				<c:if test="${not empty errorLogin}">
					<div class="alert alert-danger" role="alert">${errorLogin}</div>
				</c:if>

				<form action="login" method="post" id="login-form">
					<div class="mb-2">
						<label for="username" class="form-label">Usuario</label> <input
							type="text" class="form-control" id="username" name="username"
							required>
					</div>
					<div class="mb-2">
						<label for="password" class="form-label">Contraseña</label> <input
							type="password" class="form-control" id="password"
							name="password" required>
					</div>
					<button type="submit" class="btn btn-primary">Iniciar
						sesión</button>
				</form>
			</div>
		</div>
	</main>
	<%@ include file="components/footer.jspf"%>

	<%@ include file="components/scripts.jspf"%>
</body>

</html>