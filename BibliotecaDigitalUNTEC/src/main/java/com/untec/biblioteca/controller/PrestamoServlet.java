package com.untec.biblioteca.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.untec.biblioteca.model.libro.Libro;
import com.untec.biblioteca.model.libro.LibroDAO;
import com.untec.biblioteca.model.prestamo.Prestamo;
import com.untec.biblioteca.model.prestamo.PrestamoDAO;
import com.untec.biblioteca.model.usuario.Usuario;

@WebServlet("/prestamo")
public class PrestamoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LibroDAO libroDAO = new LibroDAO();
	private PrestamoDAO prestamoDAO = new PrestamoDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuario") == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}

		Usuario usuarioActual = (Usuario) session.getAttribute("usuario");

		String accion = request.getParameter("accion");
		if (accion == null)
			accion = "misPrestamos";

		try {
			switch (accion) {
			case "solicitar":
				// Vista previa del préstamo
				int idLibro = Integer.parseInt(request.getParameter("idLibro"));
				Libro libro = libroDAO.buscarPorId(idLibro);
				request.setAttribute("libro", libro);
				request.getRequestDispatcher("prestamo.jsp").forward(request, response);
				break;

			case "devolver":
				int idPrestamoDevolver = Integer.parseInt(request.getParameter("idPrestamo"));
				prestamoDAO.actualizarEstado(idPrestamoDevolver, "DEVUELTO");
				response.sendRedirect(request.getContextPath() + "/prestamo?accion=misPrestamos");
				break;

			case "misPrestamos":
			default:
				// Historial del usuario
				List<Prestamo> misPrestamos = prestamoDAO.listarPorUsuario(usuarioActual.getId());
				for (Prestamo p : misPrestamos) {
					Libro libroDetalle = libroDAO.buscarPorId(p.getId_libro());
					p.setLibro(libroDetalle);
				}
				request.setAttribute("listaPrestamos", misPrestamos);
				request.getRequestDispatcher("mis-prestamos.jsp").forward(request, response);
				break;
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/catalogo");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Validar sesión
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("usuario") == null) {
			response.sendRedirect(request.getContextPath() + "/");
			return;
		}

		Usuario usuarioLogueado = (Usuario) session.getAttribute("usuario");

		try {
			int idLibro = Integer.parseInt(request.getParameter("idLibro"));

			Prestamo nuevoPrestamo = new Prestamo();
			nuevoPrestamo.setId_libro(idLibro);
			nuevoPrestamo.setId_usuario(usuarioLogueado.getId());

			boolean exito = prestamoDAO.crear(nuevoPrestamo);

			if (exito) {
				request.setAttribute("mensajeExito", "Tu préstamo ha sido registrado correctamente.");
				request.getRequestDispatcher("prestamo-exitoso.jsp").forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo registrar el préstamo.");
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/catalogo");
		}
	}
}