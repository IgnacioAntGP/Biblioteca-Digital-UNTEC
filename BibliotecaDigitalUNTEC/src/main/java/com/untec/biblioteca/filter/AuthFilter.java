package com.untec.biblioteca.filter;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.untec.biblioteca.model.usuario.Usuario;

@WebFilter(urlPatterns = { "/*" })
public class AuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String uri = httpRequest.getRequestURI();
		String contextPath = httpRequest.getContextPath();

		// Login
		boolean loginRequest = uri.equals(contextPath + "/login");

		// Recursos estáticos
		boolean recursosPublicos = uri.startsWith(contextPath + "/assets/") 
                || uri.startsWith(contextPath + "/css/") 
                || uri.startsWith(contextPath + "/js/");
		
		// Páginas públicas
		if (loginRequest || recursosPublicos) {
			chain.doFilter(request, response);
			return;
		}

		// Sesión
		HttpSession session = httpRequest.getSession(false);

		Usuario usuario = (session != null) ? (Usuario) session.getAttribute("usuario") : null;

		// Usuario NO AUTENTICADO
		if (usuario == null) {
            httpResponse.sendRedirect(contextPath + "/login");
            return;
        }

		// AUTORIZACIÓN
		String accion = httpRequest.getParameter("accion");

		// OPERACIONES ADMIN
		boolean opAdmin = uri.endsWith("/admin-catalogo.jsp") || uri.endsWith("/admin-formulario-libro.jsp")
				|| "gestionar".equals(accion) || "formularioNuevo".equals(accion) || "formularioEditar".equals(accion)
				|| "eliminar".equals(accion);
		
		// USUARIOS QUE NO PUEDE ADMINISTRAR
		if (opAdmin && !"ADMIN".equals(usuario.getTipo())) {
            httpResponse.sendRedirect(contextPath + "/catalogo?mensaje=sinPermiso");
            return;
        }
		
		// Continuar
		chain.doFilter(request, response);
	}
}