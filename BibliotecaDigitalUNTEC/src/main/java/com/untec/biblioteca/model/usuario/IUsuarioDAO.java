package com.untec.biblioteca.model.usuario;

import java.sql.SQLException;
import java.util.List;

public interface IUsuarioDAO {
    Usuario validarLogin(String username, String password) throws SQLException;
    
    // CRUD general
    Usuario buscarPorId(int id) throws SQLException;
    Usuario buscarPorUsername(String username) throws SQLException;
    List<Usuario> listarTodos() throws SQLException;
    boolean crear(Usuario usuario) throws SQLException;
    boolean actualizar(Usuario usuario) throws SQLException;
    boolean eliminar(int id) throws SQLException;

}
