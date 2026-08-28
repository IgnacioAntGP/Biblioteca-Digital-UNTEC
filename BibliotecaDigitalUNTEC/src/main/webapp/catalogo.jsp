<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
<%@ include file="components/meta.jspf"%>
<title>Catálogo de Libros Digitales - Biblioteca Digital UNTEC</title>
<meta name="description"
	content="Biblioteca virtual de la Universidad Nacional Tecnológica">
</head>

<body>
	<%@ include file="components/navbar.jspf"%>
	<main class="container my-4">
		<section class="container py-5">
			<div class="d-flex justify-content-between align-items-center mb-4">
				<h2>Catálogo de Libros</h2>
				<span class="text-body-secondary">Mostrando 3 libros</span>
			</div>

			<div class="row row-cols-1 row-cols-md-2 row-cols-lg-3 g-4">
				<c:forEach var="libro" items="${listaLibros}">
					<div class="col">
						<div class="card h-100 shadow-sm border-0 bg-body-secondary">
							<div class="p-3 text-center bg-body-tertiary">
								<img
									src="<c:url value='/${libro.img_url}'/>"
									class="img-fluid rounded shadow-sm"
									style="height: 200px; object-fit: contain;"
									alt="${libro.titulo}">
							</div>
							<div class="card-body d-flex flex-column">
								<span
									class="badge bg-primary-subtle text-primary border border-primary-subtle w-auto align-self-start mb-2">
									${libro.categoria} </span>
								<h5 class="card-title text-body-emphasis">${libro.titulo}</h5>
								<p class="card-text text-body-secondary small mb-1">
									<strong>Autor:</strong> ${libro.autor}
								</p>
								<p class="card-text text-body-tertiary small">
									<strong>Editorial:</strong> ${libro.editorial}
									(${libro.anio_publicacion})
								</p>

								<div class="mt-auto pt-3 border-top d-grid gap-2">
									<!-- Botón modificado para apuntar al Servlet de préstamos con el ID -->
									<a href="${pageContext.request.contextPath}/prestamo?accion=solicitar&idLibro=${libro.id}" class="btn btn-primary">
										Pedir Préstamo </a>
									<!-- Botón modificado para apuntar al detalle dinámico -->
									<a href="${pageContext.request.contextPath}/catalogo?accion=detalle&idLibro=${libro.id}"
										class="btn btn-outline-secondary btn-sm"> Ver Ficha </a>
								</div>
							</div>
							<div
								class="card-footer bg-transparent border-0 text-muted small text-center">
								ISBN: ${libro.isbn}</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</section>
	</main>
	<%@ include file="components/footer.jspf"%>

	<%@ include file="components/scripts.jspf"%>
</body>

</html>