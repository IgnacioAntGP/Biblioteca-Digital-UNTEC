package com.untec.biblioteca.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.untec.biblioteca.model.usuario.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private List<Usuario> usuarios;

	@Override
	public void init() throws ServletException {
		usuarios = new ArrayList<>();
		usuarios.add(new Usuario(1, "admin", "admin123", "", "ADMIN"));
		usuarios.add(new Usuario(2, "usuario", "user123", "", "USER"));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		Usuario usuarioEncontrado = null;

		// BUSCAR USUARIO
		for (Usuario usuario : usuarios) {
			if (usuario.getUsername().equals(username) && usuario.getPassword().equals(password)) {
				usuarioEncontrado = usuario;
				break;
			}
		}

		// LOGIN CORRECTO
		if (usuarioEncontrado != null) {
			HttpSession session = request.getSession();
			session.setAttribute("usuario", usuarioEncontrado);
			session.setAttribute("rol", usuarioEncontrado.getTipo());
			response.sendRedirect(request.getContextPath() + "/catalogo");
			return;
		}

		// LOGIN INCORRECTO
		request.setAttribute("errorLogin", "Usuario o contraseña incorrectos.");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}