package com.alura.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sound.sampled.Port;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private DataSource dataSource ; 
	
	public ConnectionFactory() {

		
		var pooledDataSource = new ComboPooledDataSource();
		
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("1234");
		// Conexiones máximas
		pooledDataSource.setMaxPoolSize(10);
	
		
		this.dataSource = pooledDataSource;
	}

	public Connection recuperaConexion() {
		
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
//		return  DriverManager.getConnection
//				("jdbc:mysql://localhost/control_de_stock?useTimeZone=true&serverTimeZone=UTC",
//				 "root",
//				 "1234");
	}
}
