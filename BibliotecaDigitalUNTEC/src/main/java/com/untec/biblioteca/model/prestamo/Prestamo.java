package com.untec.biblioteca.model.prestamo;

import java.util.Date;

import com.untec.biblioteca.model.libro.Libro;

public class Prestamo {
	// Atributos
	private int id, id_libro, id_usuario;
	private Date fecha_prestamo, fecha_tope;
	private String estado;
	// Para consultar sobre los libros
	private Libro libro;

	// Constructores
	public Prestamo() {
	};

	public Prestamo(int id, int id_libro, int id_usuario, Date fecha_prestamo, Date fecha_tope, String estado) {
		super();
		this.id = id;
		this.id_libro = id_libro;
		this.id_usuario = id_usuario;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_tope = fecha_tope;
		this.estado = estado;
	}

	// Getters y Setters

	public int getId() {
		return id;
	}

	public int getId_libro() {
		return id_libro;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public Date getFecha_prestamo() {
		return fecha_prestamo;
	}

	public Date getFecha_tope() {
		return fecha_tope;
	}

	public String getEstado() {
		return estado;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public void setFecha_prestamo(Date fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}

	public void setFecha_tope(Date fecha_tope) {
		this.fecha_tope = fecha_tope;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	// Getters y Setters de Libro
	public Libro getLibro() {
	    return libro;
	}
	public void setLibro(Libro libro) {
	    this.libro = libro;
	}

}
