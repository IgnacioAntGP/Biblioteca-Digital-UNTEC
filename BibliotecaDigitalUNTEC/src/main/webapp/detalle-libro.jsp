<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
<%@ include file="components/meta.jspf"%>
<title>${libro.titulo} - Biblioteca Digital UNTEC</title>
<meta name="description"
	content="Detalle bibliográfico del libro en la Biblioteca Digital UNTEC">
</head>

<body class="bg-body text-body d-flex flex-column min-vh-100">
	<%@ include file="components/navbar.jspf"%>

	<main class="container my-4 flex-grow-1">

		<div class="card border-0 shadow-sm bg-body-secondary p-3 p-md-4">
			<div class="row g-4 align-items-center">

				<div class="col-12 col-md-4 text-center">
					<div
						class="bg-body-tertiary p-3 rounded shadow-sm d-inline-block w-100">
						<img src="${pageContext.request.contextPath}/${libro.img_url}" alt="${libro.titulo}"
							class="img-fluid rounded shadow"
							style="max-height: 380px; object-fit: contain;">
					</div>
				</div>

				<div class="col-12 col-md-8">
					<div class="d-flex flex-wrap gap-2 mb-2">
						<span
							class="badge bg-primary-subtle text-primary border border-primary-subtle fs-6">
							${libro.categoria}</span> <span
							class="badge bg-success-subtle text-success border border-success-subtle fs-6">
							Disponible para Préstamo </span>
					</div>

					<h1 class="display-6 fw-bold text-body-emphasis mb-2">
						${libro.titulo}</h1>

					<p class="fs-5 text-body-secondary mb-3">
						Por <strong class="text-body-emphasis">${libro.autor}</strong>
					</p>

					<hr class="my-3 opacity-25">

					<div class="row row-cols-1 row-cols-sm-2 g-3 mb-4">
						<div class="col">
							<div class="p-2 bg-body-tertiary rounded">
								<span class="d-block text-body-tertiary small">Editorial</span>
								<strong class="text-body-emphasis">${libro.editorial}</strong>
							</div>
						</div>
						<div class="col">
							<div class="p-2 bg-body-tertiary rounded">
								<span class="d-block text-body-tertiary small">Año de
									publicación</span> <strong class="text-body-emphasis">${libro.anio_publicacion}</strong>
							</div>
						</div>
						<div class="col">
							<div class="p-2 bg-body-tertiary rounded">
								<span class="d-block text-body-tertiary small">Código
									ISBN</span> <strong class="text-body-emphasis">${libro.isbn}</strong>
							</div>
						</div>
					</div>

					<div class="mb-4">
						<h5 class="fw-bold text-body-emphasis mb-2">Descripción</h5>
						<p class="text-body-secondary lh-base">${libro.descripcion}</p>
					</div>

					<div class="d-flex flex-wrap gap-2 pt-2 border-top">
						<a href="prestamo?accion=solicitar&idLibro=${libro.id}"
							class="btn btn-primary btn-lg px-4 shadow-sm"> Solicitar
							préstamo </a> <a href="catalogo"
							class="btn btn-outline-secondary btn-lg px-4"> Volver al
							catálogo </a>
					</div>

				</div>
			</div>
		</div>

	</main>

	<%@ include file="components/footer.jspf"%>

	<%@ include file="components/scripts.jspf"%>
</body>

</html>