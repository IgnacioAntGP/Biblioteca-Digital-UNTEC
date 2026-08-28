package com.untec.biblioteca.model.libro;

public class Libro {
	
	// Atributos
	// CORREGIR POR ORDEN QUE ESTÁ EN LA DB
	private int id, anio_publicacion;
	private String titulo, autor, editorial, categoria, isbn, descripcion, img_url;
	
	// Constructores
	public Libro(){}

	public Libro(int id, int anio_publicacion, String titulo, String autor, String editorial, String categoria,
			String isbn, String descripcion, String img_url) {
		super();
		this.id = id;
		this.anio_publicacion = anio_publicacion;
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.categoria = categoria;
		this.isbn = isbn;
		this.descripcion = descripcion;
		this.img_url = img_url;
	}
	
	// Getters y Setters

	public int getId() {
		return id;
	}

	public int getAnio_publicacion() {
		return anio_publicacion;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAnio_publicacion(int anio_publicacion) {
		this.anio_publicacion = anio_publicacion;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	};

}
