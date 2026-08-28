package com.untec.biblioteca.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.untec.biblioteca.model.libro.Libro;
import com.untec.biblioteca.model.libro.LibroDAO;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LibroDAO libroDAO = new LibroDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accion = request.getParameter("accion");
		if (accion == null)
			accion = "catalogo";

		try {
			switch (accion) {
			// Sección pública
			case "detalle":
				int id = Integer.parseInt(request.getParameter("idLibro"));
				Libro libro = libroDAO.buscarPorId(id);
				request.setAttribute("libro", libro);
				request.getRequestDispatcher("detalle-libro.jsp").forward(request, response);
				break;

			case "catalogo":
				List<Libro> listaLibros = libroDAO.listarTodos();
				request.setAttribute("listaLibros", listaLibros);
				request.getRequestDispatcher("catalogo.jsp").forward(request, response);
				break;

			// Sección del administrador
			case "gestionar":
				
				request.setAttribute("listaLibros", libroDAO.listarTodos());
				request.getRequestDispatcher("admin-catalogo.jsp").forward(request, response);
				break;

			case "formularioNuevo":
				
				request.getRequestDispatcher("admin-formulario-libro.jsp").forward(request, response);
				break;

			case "formularioEditar":
				
				int idEditar = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("libro", libroDAO.buscarPorId(idEditar));
				request.getRequestDispatcher("admin-formulario-libro.jsp").forward(request, response);
				break;

			case "eliminar":
				
				int idEliminar = Integer.parseInt(request.getParameter("id"));
				libroDAO.eliminar(idEliminar);
				response.sendRedirect(request.getContextPath() + "/catalogo?accion=gestionar");
				break;

			default:
				response.sendRedirect("catalogo?accion=catalogo");
				break;
			}
		} catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la base de datos.");
		} catch (NumberFormatException e) {
			response.sendRedirect("catalogo?accion=catalogo");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			

			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String editorial = request.getParameter("editorial");
			String categoria = request.getParameter("categoria");
			String isbn = request.getParameter("isbn");
			int anio = Integer.parseInt(request.getParameter("anio_publicacion"));
			String descripcion = request.getParameter("descripcion");

			String nombreImagen = request.getParameter("nombre_imagen");
			String rutaCompleta = "/assets/img/libros/" + nombreImagen;

			Libro nuevoLibro = new Libro();
			nuevoLibro.setTitulo(titulo);
			;
			nuevoLibro.setAutor(autor);
			;
			nuevoLibro.setEditorial(editorial);
			;
			nuevoLibro.setCategoria(categoria);
			;
			nuevoLibro.setIsbn(isbn);
			;
			nuevoLibro.setAnio_publicacion(anio);
			;
			nuevoLibro.setDescripcion(descripcion);
			nuevoLibro.setImg_url(rutaCompleta);

			String idStr = request.getParameter("id");

			if (idStr == null || idStr.isEmpty()) {
				libroDAO.crear(nuevoLibro);
			} else {
				nuevoLibro.setId(Integer.parseInt(idStr));
				libroDAO.actualizar(nuevoLibro);
			}

			response.sendRedirect(request.getContextPath() + "/catalogo?accion=gestionar");

		} catch (Exception e) {
			e.printStackTrace();
			if (!response.isCommitted()) {
				response.sendRedirect(request.getContextPath() + "/catalogo?accion=gestionar");
			}
		}
	}
}