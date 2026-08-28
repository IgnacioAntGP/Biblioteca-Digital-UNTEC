package com.untec.biblioteca.model.usuario;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String username;
    private String password;
    private String email;
    private String tipo; // 'ADMIN', 'ALUMNO', 'DOCENTE'

    // Constructores
    public Usuario() {}

    public Usuario(int id, String username, String password, String email, String tipo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.tipo = tipo;
    }

    public Usuario(String username, String password, String email, String tipo) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.tipo = tipo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", username=" + username + ", email=" + email + ", tipo=" + tipo + "]";
    }
}