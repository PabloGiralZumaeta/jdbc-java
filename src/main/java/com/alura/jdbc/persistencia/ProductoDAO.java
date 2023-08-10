package com.alura.jdbc.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.alura.jdbc.factory.ConnectionFactory;
import com.alura.jdbc.modelo.Producto;

public class ProductoDAO {

	final private Connection con;

	public ProductoDAO(Connection con) {
		// TODO Auto-generated constructor stub
		this.con = con;

	}

	public void guardarProducto(Producto producto) {
		// Nota: con ctrl + 1 podemos extraer en una variable el contenido :o
//		String nombre = producto.getNombre();
//		String descripcion = producto.getDescripcion();
//		Integer cantidad = producto.getCantidad();
//		Integer cantidadMaxima = Integer.valueOf(50);

//		ConnectionFactory factory = new ConnectionFactory();
//		final Connection con = factory.recuperaConexion();

		try  {
			// QUITA LA DELEGACION AL JDBC Y LA CONTROLAMOS
//			con.setAutoCommit(false);

			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO PRODUCTO" + "(nombre,descripcion,cantidad, categoria_id) " + "VALUES(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);

			try (statement) {
//				do {

				// CANTIDAD PARA GUARDARA SERA ESCOGIDA ENTRE EL NUMERO O 50
//					int cantidadParaGuardar = Math.min(cantidad, cantidadMaxima);

//					ejecutaRegistro(producto, cantidadParaGuardar, statement);
				ejecutaRegistro(producto, statement);

//					cantidad -= cantidadMaxima;

//				} while (cantidad > 0);
				// Enviara un commit a la base de datos al finalizar lo de arriba
//				con.commit();
//				System.out.println("Commit");

			}
		} catch (SQLException e) {
			// Este metodo revierte cualquier cambio realizado durante la transacción
//			con.rollback();
//			System.out.println("Rollback");
			throw new RuntimeException(e);
		}

	}

	private void ejecutaRegistro(Producto producto, PreparedStatement statement) throws SQLException {

//		if (cantidad < 50) {
//			throw new RuntimeException("Error al compilar");
//		}

		statement.setString(1, producto.getNombre());
		statement.setString(2, producto.getDescripcion());
		statement.setInt(3, producto.getCantidad());
		statement.setInt(4, producto.getCategoriaId());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();
		try (resultSet) {
			while (resultSet.next()) {
				producto.setId(resultSet.getInt(1));
				System.out.println(String.format("Fue insertado el producto  %s ", producto));
			}
		}
	}

	public List<Producto> listar() {

		List<Producto> resultado = new ArrayList<>();

//		final Connection con = new ConnectionFactory().recuperaConexion();
//		Statement statement = con.createStatement();
		try  {

			final PreparedStatement statement = con
					.prepareStatement("SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO");

			try (statement) {
				statement.execute();

				// DEVUELVE UN ITERABLE DE RESULTSET QUE CONTENDRA LOS REGISTROS
				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {

					while (resultSet.next()) {
						Producto fila = new Producto(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("DESCRIPCION"), resultSet.getInt("CANTIDAD"));

						resultado.add(fila);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	public int modificarProducto(String nombre, String descripcion, Integer cantidad, Integer id) {
		// TODO
		try  {
			final PreparedStatement statement = con.prepareStatement("UPDATE PRODUCTO SET " + "NOMBRE = ? ,"
					+ " DESCRIPCION = ? ," + " CANTIDAD = ? " + " WHERE ID = ?");

			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, descripcion);
				statement.setInt(3, cantidad);
				statement.setInt(4, id);

				statement.execute();

				int resultado = statement.getUpdateCount();

				return resultado;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public int eliminarProducto(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM PRODUCTO WHERE ID = ?");

			try (statement) {
				statement.setInt(1, id);

				statement.execute();
				// DEVULEVE UN ENTERO DE LA CANTIDAD DE FILAS QUE SE HAN MODIFICADO O ELIMINADO
				 int updateCount = statement.getUpdateCount();

		            return updateCount;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	public List<Producto> listar(Integer categoriaId) {
		// TODO Auto-generated method stub
		List<Producto> resultado = new ArrayList<>();

//		final Connection con = new ConnectionFactory().recuperaConexion();
//		Statement statement = con.createStatement();
		try  {
			var querySelect  = "SELECT ID, NOMBRE, DESCRIPCION, CANTIDAD FROM PRODUCTO"
					+ " WHERE CATEGORIA_ID = ?";
			System.out.println(querySelect);
			final PreparedStatement statement = con
					.prepareStatement(querySelect);

			try (statement) {
				statement.setInt(1, categoriaId);
				
				statement.execute();

				// DEVUELVE UN ITERABLE DE RESULTSET QUE CONTENDRA LOS REGISTROS
				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {

					while (resultSet.next()) {
						Producto fila = new Producto(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("DESCRIPCION"), resultSet.getInt("CANTIDAD"));

						resultado.add(fila);
					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}





}
