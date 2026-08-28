<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html lang="es">
<head>
<%@ include file="components/meta.jspf"%>
<title>${libro != null ? 'Editar Libro' : 'Nuevo Libro'} - Admin
	UNTEC</title>
</head>
<body class="bg-body-tertiary d-flex flex-column min-vh-100">
	<%@ include file="components/navbar.jspf"%>

	<main class="container my-4 flex-grow-1">
		<div class="row justify-content-center">
			<div class="col-md-8">
				<div class="card shadow-sm border-0">
					<div class="card-header bg-primary text-white">
						<h4 class="mb-0 fw-semibold">${libro != null ? 'Editar Libro' : 'Agregar Nuevo Libro'}
						</h4>
					</div>

					<div class="card-body p-4">
						<!-- El formulario apunta al controlador principal mediante POST -->
						<form action="${pageContext.request.contextPath}/catalogo"
							method="POST">

							<!-- Campo oculto vital para identificar el registro al editar -->
							<input type="hidden" name="id"
								value="${libro != null ? libro.id : ''}">

							<div class="row g-3">
								<div class="col-md-6">
									<label class="form-label">Título</label> <input type="text"
										class="form-control" name="titulo"
										value="${libro != null ? libro.titulo : ''}" required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Autor</label> <input type="text"
										class="form-control" name="autor"
										value="${libro != null ? libro.autor : ''}" required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Editorial</label> <input type="text"
										class="form-control" name="editorial"
										value="${libro != null ? libro.editorial : ''}" required>
								</div>
								<div class="col-md-6">
									<label class="form-label">Categoría</label> <input type="text"
										class="form-control" name="categoria"
										value="${libro != null ? libro.categoria : ''}" required>
								</div>
								<div class="col-md-4">
									<label class="form-label">ISBN</label> <input type="text"
										class="form-control" name="isbn"
										value="${libro != null ? libro.isbn : ''}" required>
								</div>
								<div class="col-md-4">
									<label class="form-label">Año</label> <input type="number"
										class="form-control" name="anio_publicacion"
										value="${libro != null ? libro.anio_publicacion : ''}"
										required>
								</div>
								<div class="col-md-5">
									<label class="form-label">Nombre de la Portada</label>
									<div class="input-group shadow-sm">
										<!-- Texto fijo que da contexto visual -->
										<span
											class="input-group-text bg-body-tertiary text-muted small">/assets/img/libro/</span>

										<!-- Campo donde el admin solo escribe el nombre del archivo -->
										<input type="text" class="form-control" name="nombre_imagen"
											value="${libro != null ? libro.img_url : ''}"
											placeholder="ej: portada_libro.jpg">
									</div>
									<div class="form-text small">Incluye la extensión (.jpg,
										.png)</div>
								</div>
								<div class="col-12">
									<label class="form-label">Descripción</label>
									<textarea class="form-control" name="descripcion" rows="3">${libro != null ? libro.descripcion : ''}</textarea>
								</div>
							</div>

							<div
								class="d-flex justify-content-end gap-2 mt-4 pt-3 border-top">
								<a
									href="${pageContext.request.contextPath}/catalogo?accion=gestionar"
									class="btn btn-secondary">Cancelar</a>
								<button type="submit" class="btn btn-primary">Guardar
									Registro</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>

	<%@ include file="components/footer.jspf"%>
	<%@ include file="components/scripts.jspf"%>
</body>
</html>