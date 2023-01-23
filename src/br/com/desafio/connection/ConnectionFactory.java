package br.com.desafio.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection conexao() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost/Estacionamento?useTimezone=true&serverTimezone=UTC", "root" ,"05784394762");
		
		
	}
}
