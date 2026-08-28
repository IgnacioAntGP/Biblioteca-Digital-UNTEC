<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <%@ include file="components/meta.jspf"%>
    <title>Préstamo Exitoso - Biblioteca Digital UNTEC</title>
    
    <meta http-equiv="refresh" content="3;url=${pageContext.request.contextPath}/catalogo">
</head>
<body class="bg-body-tertiary d-flex flex-column min-vh-100">
    <%@ include file="components/navbar.jspf"%>

    <main class="container d-flex align-items-center justify-content-center flex-grow-1">
        <div class="text-center p-5 bg-body rounded shadow-sm border-top border-success border-4" style="max-width: 500px; width: 100%;">
            
            <h2 class="fw-bold text-success mb-3">¡Préstamo Aprobado!</h2>
            <p class="text-body-secondary fs-5">${mensajeExito}</p>
            
            <div class="spinner-border text-success my-4" role="status">
                <span class="visually-hidden">Cargando...</span>
            </div>
            
            <p class="text-muted small mb-4">Serás redirigido al catálogo en unos segundos...</p>
            
            <a href="${pageContext.request.contextPath}/catalogo" class="btn btn-outline-secondary w-100">
                Volver ahora
            </a>
        </div>
    </main>

    <%@ include file="components/scripts.jspf"%>
</body>
</html>