package com.untec.biblioteca.model.usuario;

import com.untec.biblioteca.utils.Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO implements IUsuarioDAO {

	// Objeto para conectar con la BD
	private static final Conexion CONEXION = Conexion.getEstado();

	// Objeto PreparadedStatement para preparar y ejecutar consultas
	private PreparedStatement ps;

	// Objeto ResultSet para capturar los datos obtenidos a partir de consultas
	private ResultSet rs;

	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT id, username, passwrd, email, tipo "
			+ "FROM usuarios " + "WHERE username = ? AND passwrd = ?";

	@Override
	public Usuario validarLogin(String username, String password) throws SQLException {
		Usuario usuario = null;

		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_SELECT_BY_USERNAME_AND_PASSWORD);

			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setUsername(rs.getString("username"));
				usuario.setPassword(rs.getString("passwrd"));
				usuario.setEmail(rs.getString("email"));
				usuario.setTipo(rs.getString("tipo"));
			}
		} finally {
			CONEXION.cerrarConexion();
		}
		return usuario;
	}

	private static final String SQL_SELECT_BY_ID = "SELECT id, username, passwrd, email, tipo " + "FROM usuarios "
			+ "WHERE id = ?";

	@Override
	public Usuario buscarPorId(int id) throws SQLException {
		Usuario usuario = null;

		try {

			ps = CONEXION.getConexion().prepareStatement(SQL_SELECT_BY_ID);

			ps.setInt(1, id);

			rs = ps.executeQuery();
			if (rs.next()) {
				usuario = new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("passwrd"),
						rs.getString("email"), rs.getString("tipo"));
			}
		} finally {
			CONEXION.cerrarConexion();
		}

		return usuario;
	}

	private static final String SQL_SELECT_BY_USERNAME = "SELECT id, username, passwrd, email, tipo " + "FROM usuarios "
			+ "WHERE username = ?";

	@Override
	public Usuario buscarPorUsername(String username) throws SQLException {
		Usuario usuario = null;

		try {

			ps = CONEXION.getConexion().prepareStatement(SQL_SELECT_BY_USERNAME);

			ps.setString(1, username);

			rs = ps.executeQuery();
			if (rs.next()) {
				usuario = new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("passwrd"),
						rs.getString("email"), rs.getString("tipo"));
			}
		} finally {
			CONEXION.cerrarConexion();
		}
		return usuario;
	}

	private static final String SQL_SELECT_ALL = "SELECT id, username, passwrd, email, tipo " + "FROM usuarios";

	@Override
	public List<Usuario> listarTodos() throws SQLException {
		List<Usuario> lista = new ArrayList<>();

		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_SELECT_ALL);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Usuario u = new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("passwrd"),
						rs.getString("email"), rs.getString("tipo"));
				lista.add(u);
			}
		} finally {
			CONEXION.cerrarConexion();
		}

		return lista;
	}

	private static final String SQL_INSERT = "INSERT INTO usuarios (username, passwrd, email, tipo) "
			+ "VALUES (?, ?, ?, ?)";

	@Override
	public boolean crear(Usuario usuario) throws SQLException {

		try { 
			ps = CONEXION.getConexion().prepareStatement(SQL_INSERT); 

			ps.setString(1, usuario.getUsername());
			ps.setString(2, usuario.getPassword());
			ps.setString(3, usuario.getEmail());
			ps.setString(4, usuario.getTipo());

			return ps.executeUpdate() > 0;

		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	private static final String SQL_UPDATE = "UPDATE usuarios "
			+ "SET username = ?, passwrd = ?, email = ?, tipo = ? "
			+ "WHERE id = ?";

	@Override
	public boolean actualizar(Usuario usuario) throws SQLException {

		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_UPDATE);

			ps.setString(1, usuario.getUsername());
			ps.setString(2, usuario.getPassword());
			ps.setString(3, usuario.getEmail());
			ps.setString(4, usuario.getTipo());
			ps.setInt(5, usuario.getId());

			return ps.executeUpdate() > 0;

		} finally {
			CONEXION.cerrarConexion();
		}
	}
	
	private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id = ?";

	@Override
	public boolean eliminar(int id) throws SQLException {

		try {ps = CONEXION.getConexion().prepareStatement(SQL_DELETE);

			ps.setInt(1, id);
			return ps.executeUpdate() > 0;

		} finally {
			CONEXION.cerrarConexion();
		}
	}
}