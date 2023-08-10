package com.alura.jdbc.controller;

import java.util.List;


import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;
import com.alura.jdbc.persistencia.ProductoDAO;

public class ProductoController {

	private ProductoDAO productoDAO;
	
	public ProductoController() {
		// TODO Auto-generated constructor stub
		 this.productoDAO =  new ProductoDAO(new ConnectionFactory().recuperaConexion());
	}
	
	public int modificar(String nombre, String descripcion,Integer cantidad,Integer id) {
		
	return productoDAO.modificarProducto(nombre, descripcion, cantidad, id);

	}

	public int eliminar(Integer id)  {
		// TODO
		return productoDAO.eliminarProducto(id);

	}

	public List<Producto> listar()  {
		// TODO
		return productoDAO.listar();
		
	}
	
	public List<Producto> listar(Categoria categoria){
		return productoDAO.listar(categoria.getId());
	}

	public void guardar(Producto producto, Integer categoriaId)  {
		producto.setCategoriaId(categoriaId);	 
		productoDAO.guardarProducto(producto);

	}



}
