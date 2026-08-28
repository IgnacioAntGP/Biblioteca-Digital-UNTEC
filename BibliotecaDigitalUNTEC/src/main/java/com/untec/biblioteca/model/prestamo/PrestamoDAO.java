package com.untec.biblioteca.model.prestamo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.untec.biblioteca.utils.Conexion;

public class PrestamoDAO implements IPrestamoDAO {

	// Objeto para conectar con la BD
	private static final Conexion CONEXION = Conexion.getEstado();

	// Objeto PreparadedStatement para preparar y ejecutar consultas
	private PreparedStatement ps;

	// Objeto ResultSet para capturar los datos obtenidos a partir de consultas
	private ResultSet rs;

	// Métodos de conversión para utilidades
	private Prestamo convertirPrestamo(ResultSet rs) throws SQLException {
		Prestamo p = new Prestamo();
		p.setId(rs.getInt("id"));
		p.setId_libro(rs.getInt("id_libro"));
		p.setId_usuario(rs.getInt("id_usuario"));
		p.setFecha_prestamo(rs.getDate("fecha_prestamo"));
		p.setFecha_tope(rs.getDate("fecha_tope"));
		p.setEstado(rs.getString("estado"));
		return p;
	}

	private void asignarParametrosPrestamo(PreparedStatement ps, Prestamo prestamo) throws SQLException {
		ps.setInt(1, prestamo.getId_libro());
		ps.setInt(2, prestamo.getId_usuario());
		ps.setDate(3, new java.sql.Date(prestamo.getFecha_prestamo().getTime()));
		ps.setDate(4, new java.sql.Date(prestamo.getFecha_tope().getTime()));
		ps.setString(5, prestamo.getEstado());
	}

	private static final String SQL_SELECT_BY_USUARIO = "SELECT id, id_libro, id_usuario, fecha_prestamo, fecha_tope, estado "
			+ "FROM prestamo " + "WHERE id_usuario = ?";

	@Override
	public List<Prestamo> listarPorUsuario(int idUsuario) throws SQLException {
		List<Prestamo> prestamos = new ArrayList<>();
		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_SELECT_BY_USUARIO);
			ps.setInt(1, idUsuario);

			rs = ps.executeQuery();

			while (rs.next()) {
				prestamos.add(convertirPrestamo(rs));
			}

		} finally {
			CONEXION.cerrarConexion();
		}

		return prestamos;
	}

	private static final String SQL_SELECT_ALL = "SELECT id, id_libro, id_usuario, fecha_prestamo, fecha_tope, estado "
			+ "FROM prestamo";

	@Override
	public List<Prestamo> listarTodos() throws SQLException {
		List<Prestamo> prestamos = new ArrayList<>();

		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_SELECT_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				prestamos.add(convertirPrestamo(rs));
			}

		} finally {
			CONEXION.cerrarConexion();
		}

		return prestamos;
	}

	private static final String SQL_INSERT = "INSERT INTO prestamo (id_libro, id_usuario, fecha_prestamo, fecha_tope, estado) "
			+ "VALUES (?, ?, ?, ?, ?)";

	@Override
	public boolean crear(Prestamo prestamo) throws SQLException {
		LocalDate fechaHoy = LocalDate.now();
		LocalDate fechaTope = fechaHoy.plusDays(7);

		prestamo.setFecha_prestamo(java.sql.Date.valueOf(fechaHoy));
		prestamo.setFecha_tope(java.sql.Date.valueOf(fechaTope));
		prestamo.setEstado("PRESTADO");

		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_INSERT);

			asignarParametrosPrestamo(ps, prestamo);

			int filas = ps.executeUpdate();
			return filas > 0;
		} finally {
			CONEXION.cerrarConexion();
		}
	}

	private static final String SQL_UPDATE = "UPDATE prestamo "
			+ "SET id_libro = ?, id_usuario = ?, fecha_prestamo = ?, fecha_tope = ?, estado = ? " + "WHERE id = ?";

	@Override
	public boolean actualizar(Prestamo prestamo) throws SQLException {

		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_UPDATE);

			asignarParametrosPrestamo(ps, prestamo);
			ps.setInt(6, prestamo.getId());

			return ps.executeUpdate() > 0;

		} finally {
			CONEXION.cerrarConexion();
		}
	}

	private static final String SQL_UPDATE_ESTADO = "UPDATE prestamo SET estado = ? " + "WHERE id = ?";

	public boolean actualizarEstado(int idPrestamo, String nuevoEstado) throws SQLException {

		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_UPDATE_ESTADO);

			ps.setString(1, nuevoEstado);
			ps.setInt(2, idPrestamo);

			return ps.executeUpdate() > 0;
		} finally {
			CONEXION.cerrarConexion();
		}
	}

	private static final String SQL_DELETE = "DELETE FROM prestamo " + "WHERE id = ?";

	@Override
	public boolean eliminar(int id) throws SQLException {
		try {
			ps = CONEXION.getConexion().prepareStatement(SQL_DELETE);

			ps.setInt(1, id);
			return ps.executeUpdate() > 0;

		} finally {
			CONEXION.cerrarConexion();
		}
	}
}