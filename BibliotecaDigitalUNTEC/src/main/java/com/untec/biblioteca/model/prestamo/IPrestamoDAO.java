package com.untec.biblioteca.model.prestamo;

import java.sql.SQLException;
import java.util.List;

public interface IPrestamoDAO {
    List<Prestamo> listarPorUsuario(int idUsuario) throws SQLException;
	
	// CRUD general
    List<Prestamo> listarTodos() throws SQLException;
    boolean crear(Prestamo prestamo) throws SQLException;
    boolean actualizar(Prestamo prestamo) throws SQLException;
    boolean eliminar(int id) throws SQLException;
}
