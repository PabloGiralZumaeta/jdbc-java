package com.alura.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.modelo.Categoria;
import com.alura.jdbc.modelo.Producto;

public class CategoriaDAO {

	private Connection con;

	public CategoriaDAO(Connection con) {
		// TODO Auto-generated constructor stub
		this.con = con;
	}

	public List<Categoria> listar() {
		// TODO Auto-generated method stub
		List<Categoria> resultado = new ArrayList<>();

		try {
			var querySelect = "SELECT ID, NOMBRE FROM CATEGORIA";
			System.out.println(querySelect);
			final PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {

				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						var categoria = new Categoria(resultSet.getInt("ID"), resultSet.getString("NOMBRE"));
						resultado.add(categoria);
					}

				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

	public List<Categoria> listarConProductos() {
		// TODO Auto-generated method stub
		List<Categoria> resultado = new ArrayList<>();
		
		try {
			var querySelect = "SELECT C.ID, C.NOMBRE, P.ID, P.NOMBRE, P.CANTIDAD FROM CATEGORIA C"
					+ " INNER JOIN PRODUCTO P ON C.ID = P.CATEGORIA_ID";
			System.out.println(querySelect);
			final PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {

				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						Integer categoriaId = resultSet.getInt("ID");
						String categoriaNombre = resultSet.getString("NOMBRE");

						var categoria = resultado
								.stream()
								.filter(cat -> cat.getId().equals(categoriaId))
								.findAny()
								.orElseGet(() -> {
									Categoria cat = new Categoria(categoriaId, categoriaNombre);
									resultado.add(cat);

									return cat;
								});

						Producto producto = new Producto(resultSet.getInt("P.ID"), resultSet.getString("P.NOMBRE"),
								resultSet.getInt("P.CANTIDAD")

						);

						categoria.agregar(producto);
						
//						System.out.println(categoria);

					
					}

				}

			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultado;
	}

}
