<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
<%@ include file="components/meta.jspf"%>
<title>Gestión de Catálogo - Admin UNTEC</title>
</head>

<body class="bg-body-tertiary d-flex flex-column min-vh-100">
	<%@ include file="components/navbar.jspf"%>

	<main class="container my-4 flex-grow-1">

		<!-- Encabezado y botón de agregar -->
		<div class="d-flex justify-content-between align-items-center mb-4">
			<h2 class="fw-bold text-body-emphasis mb-0">Gestión de Catálogo</h2>

			<!-- Acción para el Servlet: formularioNuevo -->
			<a
				href="${pageContext.request.contextPath}/catalogo?accion=formularioNuevo"
				class="btn btn-success shadow-sm"> ➕ Agregar Nuevo Libro </a>
		</div>

		<!-- Tabla de Datos -->
		<section class="bg-body p-4 rounded shadow-sm border">
			<div class="table-responsive">
				<table class="table table-hover align-middle mb-0">
					<thead class="table-light">
						<tr>
							<th>#ID</th>
							<th>Título</th>
							<th>Autor</th>
							<th>Categoría</th>
							<th>ISBN</th>
							<th class="text-center">Acciones</th>
						</tr>
					</thead>
					<tbody>
						<!-- Iteramos sobre la lista de todos los libros -->
						<c:forEach var="libro" items="${listaLibros}">
							<tr>
								<td><strong>#${libro.id}</strong></td>
								<td class="fw-semibold text-body-emphasis">${libro.titulo}</td>
								<td class="text-body-secondary">${libro.autor}</td>
								<td><span
									class="badge bg-primary-subtle text-primary border border-primary-subtle">${libro.categoria}</span></td>
								<td class="text-muted small">${libro.isbn}</td>

								<!-- Botones de Acción (Editar y Eliminar) -->
								<td class="text-center">
									<div class="d-flex justify-content-center gap-2">
										<!-- Apunta a formularioEditar pasando el ID -->
										<a
											href="${pageContext.request.contextPath}/catalogo?accion=formularioEditar&id=${libro.id}"
											class="btn btn-outline-primary btn-sm"> ✏️ Editar </a>

										<!-- Apunta a eliminar pasando el ID y pide confirmación JS -->
										<a
											href="${pageContext.request.contextPath}/catalogo?accion=eliminar&id=${libro.id}"
											class="btn btn-outline-danger btn-sm"
											onclick="return confirm('¿Estás seguro de que deseas eliminar este libro del sistema? Esta acción no se puede deshacer.');">
											🗑️ Eliminar </a>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>

	</main>

	<%@ include file="components/footer.jspf"%>
	<%@ include file="components/scripts.jspf"%>
</body>
</html>