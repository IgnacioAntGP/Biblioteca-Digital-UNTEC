package com.untec.biblioteca.model.libro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.untec.biblioteca.utils.Conexion;

public class LibroDAO implements ILibroDAO {

	// Objeto para conectar con la BD
	private static final Conexion CONEXION = Conexion.getEstado();

	// Objeto PreparadedStatement para preparar y ejecutar consultas
	private PreparedStatement ps;

	// Objeto ResultSet para capturar los datos obtenidos a partir de consultas
	private ResultSet rs;

	// Métodos de conversión para utilidades
	private Libro convertirLibro(ResultSet rs) throws SQLException {
		Libro l = new Libro();
		l.setId(rs.getInt("id"));
		l.setTitulo(rs.getString("titulo"));
		l.setAutor(rs.getString("autor"));
		l.setEditorial(rs.getString("editorial"));
		l.setCategoria(rs.getString("categoria"));
		l.setIsbn(rs.getString("isbn"));
		l.setAnio_publicacion(rs.getInt("anio_publicacion"));
		l.setDescripcion(rs.getString("descripcion"));
		l.setImg_url(rs.getString("img_url"));
		return l;
	}

	private void asignarParametrosLibro(PreparedStatement ps, Libro libro) throws SQLException {
		ps.setString(1, libro.getTitulo());
		ps.setString(2, libro.getAutor());
		ps.setString(3, libro.getEditorial());
		ps.setString(4, libro.getCategoria());
		ps.setString(5, libro.getIsbn());
		ps.setInt(6, libro.getAnio_publicacion());
		ps.setString(7, libro.getDescripcion());
		ps.setString(8, libro.getImg_url());
	}

	// Métodos para consultas SELECT

	/**
	 * Consulta para buscar libro por ID
	 */

	private static final String SQL_SELECT_BY_ID = "SELECT id, anio_publicacion, titulo, autor, editorial, categoria, isbn, descripcion, img_url "
			+ "FROM libro " + "WHERE id = ?";

	/**
	 * Método para buscar un libro por su atributo ID
	 * 
	 * @params int
	 * @return Libro
	 * @throws SQLException
	 */

	@Override
	public Libro buscarPorId(int id) throws SQLException {
		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_SELECT_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			Libro l = null;
			if (rs.next()) {
				l = convertirLibro(rs);
			}
			return l;
		} finally {
			CONEXION.cerrarConexion();
		}
	}

	/**
	 * Consulta para buscar un libro por su título / nombre
	 */

	private static final String SQL_SELECT_BY_TITULO = "SELECT id, anio_publicacion, titulo, autor, editorial, categoria, isbn, descripcion, img_url "
			+ "FROM libro " + "WHERE titulo = ?";

	/**
	 * Método para buscar libros por su titulo o nombre
	 * 
	 * @params String
	 * @return List<Libro>
	 * @throws SQLException
	 */

	@Override
	public List<Libro> buscarPorTitulo(String titulo) throws SQLException {
		try {
			List<Libro> libros = new ArrayList<Libro>();
			ps = CONEXION.getConexion().prepareStatement(SQL_SELECT_BY_TITULO);
			ps.setString(1, titulo);
			rs = ps.executeQuery();
			Libro l = null;
			while (rs.next()) {
				l = convertirLibro(rs);
				libros.add(l);
			}
			return libros;
		} finally {
			CONEXION.cerrarConexion();
		}
	}

	/**
	 * Consulta para buscar un libro por su autor
	 */

	private static final String SQL_SELECT_BY_AUTOR = "SELECT id, anio_publicacion, titulo, autor, editorial, categoria, isbn, descripcion, img_url "
			+ "FROM libro " + "WHERE autor = ?";

	/**
	 * Método para buscar libros por su titulo o nombre
	 * 
	 * @params String
	 * @return List<Libro>
	 * @throws SQLException
	 */
	@Override
	public List<Libro> buscarPorAutor(String autor) throws SQLException {
		try {
			List<Libro> libros = new ArrayList<Libro>();
			ps = CONEXION.getConexion().prepareStatement(SQL_SELECT_BY_AUTOR);
			ps.setString(1, autor);
			rs = ps.executeQuery();
			Libro l = null;
			while (rs.next()) {
				l = convertirLibro(rs);
				libros.add(l);
			}
			return libros;
		} finally {
			CONEXION.cerrarConexion();
		}
	}

	/**
	 * Consulta para buscar un libro por su categoria
	 */

	private static final String SQL_SELECT_BY_CATEGORIA = "SELECT id, anio_publicacion, titulo, autor, editorial, categoria, isbn, descripcion, img_url "
			+ "FROM libro " + "WHERE categoria = ?";

	/**
	 * Método para buscar libros por su titulo o nombre
	 * 
	 * @params String
	 * @return List<Libro>
	 * @throws SQLException
	 */
	@Override
	public List<Libro> buscarPorCategoria(String categoria) throws SQLException {
		try {
			List<Libro> libros = new ArrayList<Libro>();
			ps = CONEXION.getConexion().prepareStatement(SQL_SELECT_BY_CATEGORIA);
			ps.setString(1, categoria);
			rs = ps.executeQuery();
			Libro l = null;
			while (rs.next()) {
				l = convertirLibro(rs);
				libros.add(l);
			}
			return libros;
		} finally {
			CONEXION.cerrarConexion();
		}
	}

	/**
	 * Consulta para buscar un libro por 3 parámetros en orden: titulo, autor y
	 * categoria
	 */

	private static final String SQL_SELECT_BY_TITULO_OR_AUTOR_OR_CATEGORIA = "SELECT id, anio_publicacion, titulo, autor, editorial, categoria, isbn, descripcion, img_url"
			+ "FROM libro " + "WHERE titulo = ? OR autor = ? OR categoria = ?";

	/**
	 * Método para buscar libros por parámetros: titulo, autor, categoria
	 * 
	 * @params String: criterio de búsqueda
	 * @return List<Libro>
	 * @throws SQLException
	 */
	@Override
	public List<Libro> buscarPorTituloOrAutorOrCategoria(String criterio) throws SQLException {
		try {
			List<Libro> libros = new ArrayList<Libro>();
			ps = CONEXION.getConexion().prepareStatement(SQL_SELECT_BY_TITULO_OR_AUTOR_OR_CATEGORIA);
			String parametro = "%" + criterio + "%";
			ps.setString(1, parametro);
			ps.setString(2, parametro);
			ps.setString(3, parametro);
			rs = ps.executeQuery();
			Libro l = null;
			while (rs.next()) {
				l = convertirLibro(rs);
				libros.add(l);
			}
			return libros;
		} finally {
			CONEXION.cerrarConexion();
		}
	}

	/**
	 * Consulta para mostrar todos los libros ordenados por id
	 */

	private static final String SQL_SELECT_ALL = "SELECT id, anio_publicacion, titulo, autor, editorial, categoria, isbn, descripcion, img_url "
			+ "FROM libro " + "ORDER BY id ASC";

	/**
	 * Método para mostrar todos los libros guardados en el sistema
	 * 
	 * @return List<Libro>
	 * @throws SQLException
	 */

	@Override
	public List<Libro> listarTodos() throws SQLException {
		try {
			List<Libro> libros = new ArrayList<Libro>();
			ps = CONEXION.getConexion().prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();
			while (rs.next()) {
				Libro l = convertirLibro(rs);
				libros.add(l);
			}
			return libros;
		} finally {
			CONEXION.cerrarConexion();
		}
	}

	/**
	 * Consulta para mostrar insertar un objeto libro en la Base de Datos
	 */

	private static final String SQL_INSERT = "INSERT INTO libro (titulo, autor, editorial, categoria, isbn, anio_publicacion, descripcion, img_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	/**
	 * Método para realizar la transacción de inserción en la Base de Datos
	 * 
	 * @param obj Libro
	 * @return boolean: filas insertadas
	 * @throws SQLException
	 */

	@Override
	public boolean crear(Libro libro) throws SQLException {
		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_INSERT);

			asignarParametrosLibro(ps, libro);

			int filas = ps.executeUpdate();

			return filas > 0;

		} finally {
			CONEXION.cerrarConexion();
		}
	}

	/**
	 * Consulta para actualizar un libro específico mediante id
	 */

	private static final String SQL_UPDATE = "UPDATE libro "
			+ "SET titulo = ?, autor = ?, editorial = ?, categoria = ?, isbn = ?, anio_publicacion = ?, descripcion = ?, img_url = ? "
			+ "WHERE id = ?";

	/**
	 * Método para mostrar todos los libros guardados en el sistema
	 * 
	 * @param obj Libro
	 * @return boolean: filas actualizadas
	 * @throws SQLException
	 */

	@Override
	public boolean actualizar(Libro libro) throws SQLException {
		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_UPDATE);

			asignarParametrosLibro(ps, libro);

			ps.setInt(9, libro.getId());

			int filas = ps.executeUpdate();

			return filas > 0;

		} finally {
			CONEXION.cerrarConexion();
		}
	}

	/**
	 * Consulta para eliminar un libro específico mediante id
	 */

	private static final String SQL_DELETE = "DELETE FROM libro WHERE id = ?";

	/**
	 * Método para mostrar todos los libros guardados en el sistema
	 * 
	 * @param int: id_libro
	 * @return boolean: filas eliminadas
	 * @throws SQLException
	 */

	@Override
	public boolean eliminar(int id) throws SQLException {
		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_DELETE);
			ps.setInt(1, id);
			int filas = ps.executeUpdate();
			return filas > 0;
		} finally {
			CONEXION.cerrarConexion();
		}
	}

}
