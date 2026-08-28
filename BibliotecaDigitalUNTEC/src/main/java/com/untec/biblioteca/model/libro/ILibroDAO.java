package com.untec.biblioteca.model.libro;

import java.sql.SQLException;
import java.util.List;

public interface ILibroDAO {
	
    Libro buscarPorId(int id) throws SQLException;
    List<Libro> buscarPorTitulo(String titulo) throws SQLException;
    List<Libro> buscarPorAutor(String autor) throws SQLException;
    List<Libro> buscarPorCategoria(String categoria) throws SQLException;
    List<Libro> buscarPorTituloOrAutorOrCategoria(String parametro) throws SQLException;
    // CRUD general
    List<Libro> listarTodos() throws SQLException;
    boolean crear(Libro libro) throws SQLException;
    boolean actualizar(Libro libro) throws SQLException;
    boolean eliminar(int id) throws SQLException;

}
