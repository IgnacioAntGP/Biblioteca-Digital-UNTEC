<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
<%@ include file="components/meta.jspf"%>
<title>Prestamo ${libro.titulo} - Biblioteca Digital UNTEC</title>
<meta name="description"
	content="Biblioteca virtual de la Universidad Nacional Tecnológica">
</head>

<body>
	<%@ include file="components/navbar.jspf"%>
	<main class="container my-4">
		<form action="prestamo" method="POST" id="prestamo-form" class="mb-4">
			<input type="hidden" name="idLibro" value="${libro.id}">
			<fieldset disabled>
				<div class="mb-3">
					<label for="titulo" class="form-label">Título</label> <input
						type="text" class="form-control" id="titulo" name="titulo"
						required readonly value="${libro.titulo}">
				</div>
				<div class="mb-3">
					<label for="autor" class="form-label">Autor</label> <input
						type="text" class="form-control" id="autor" name="autor" required
						readonly value="${libro.autor}">
				</div>
				<div class="mb-3">
					<label for="editorial" class="form-label">Editorial</label> <input
						type="text" class="form-control" id="editorial" name="editorial"
						required readonly value="${libro.editorial}">
				</div>
				<div class="mb-3">
					<label for="anio_publicacion" class="form-label">Año de
						publicación</label> <input type="number" class="form-control"
						id="anio_publicacion" name="anio_publicacion" required readonly
						value="${libro.anio_publicacion}">
				</div>
				<img src="<c:url value='/${libro.img_url}'/>"
					class="img-fluid rounded shadow-sm my-5"
					style="height: 200px; object-fit: contain;" alt="${libro.titulo}">
			</fieldset>
			<div class="d-flex gap-2 mt-4">
				<button type="submit" class="btn btn-primary">Pedir
					Préstamo</button>
				<a href="${pageContext.request.contextPath}/catalogo"
					class="btn btn-secondary">Cancelar</a>
			</div>
		</form>
	</main>
	<%@ include file="components/footer.jspf"%>

	<%@ include file="components/scripts.jspf"%>
</body>

</html>