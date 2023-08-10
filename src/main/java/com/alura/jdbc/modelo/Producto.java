package com.alura.jdbc.modelo;

public class Producto {



	private Integer id;
	private String nombre;
	private String descripcion;
	private Integer	cantidad;
	private Integer categoriaId;  
	
	
	public Producto(String nombre, String descripcion, int cantidad) {
		
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		
	}

	public Producto(int id, String nombre, String descripcion, int cantidad) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.cantidad = cantidad;
	}

	public Producto(int id, String nombre, int cantidad) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.nombre= nombre;
		this.cantidad = cantidad;
	}

	public Integer getId() {
		return id;
	}
	
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
		// TODO Auto-generated method stub
		
	}
	public int getCategoriaId() {
		// TODO Auto-generated method stub
		return this.categoriaId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format(
				"{id: %d , nombre: %s , descripción: %s , cantidad: %d }", 
				this.id , 
				this.nombre ,
				this.descripcion ,
				this.cantidad
				);
	}

	
	
	
}
