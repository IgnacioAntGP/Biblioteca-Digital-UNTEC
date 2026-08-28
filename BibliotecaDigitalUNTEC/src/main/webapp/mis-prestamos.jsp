<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">

<head>
<%@ include file="components/meta.jspf"%>
<title>Mis Prestamos - Biblioteca Digital UNTEC</title>
<meta name="description"
	content="Biblioteca virtual de la Universidad Nacional Tecnológica">
</head>

<body>
	<%@ include file="components/navbar.jspf"%>
	<main class="container my-4">
		<section class="container py-5">
			<div class="table-responsive shadow-sm rounded border">
				<table class="table table-hover align-middle mb-0">
					<thead class="table-light">
						<tr>
							<th>#ID</th>
							<th>Libro</th>
							<th>Autor</th>
							<th>Fecha Préstamo</th>
							<th>Fecha Límite</th>
							<th>Estado</th>
							<th class="text-center">Acción</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="p" items="${listaPrestamos}">
							<tr>
								<td><strong>#${p.id}</strong></td>
								<td class="fw-semibold text-body-emphasis">${p.libro.titulo}</td>
								<td class="text-body-secondary">${p.libro.autor}</td>
								<td>${p.fecha_prestamo}</td>
								<td><span class="text-danger fw-semibold">${p.fecha_tope}</span></td>
								<td><span
									class="badge ${p.estado == 'PRESTADO' ? 'bg-primary' : (p.estado == 'DEVUELTO' ? 'bg-success' : 'bg-danger')}">
										${p.estado} </span></td>
								<td class="text-center"><c:if
										test="${p.estado == 'PRESTADO'}">
										<a
											href="${pageContext.request.contextPath}/prestamo?accion=devolver&idPrestamo=${p.id}"
											class="btn btn-outline-primary btn-sm"> Devolver </a>
									</c:if> <c:if test="${p.estado != 'PRESTADO'}">
										<span class="text-muted small">Sin acciones</span>
									</c:if></td>
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