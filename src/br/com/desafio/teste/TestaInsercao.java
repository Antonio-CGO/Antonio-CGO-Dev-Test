package br.com.desafio.teste;

import java.sql.Connection;
import java.sql.SQLException;
import br.com.desafio.DAO.VeiculoDAO;
import br.com.desafio.connection.ConnectionFactory;
import br.com.desafio.modelo.Veiculo;

public class TestaInsercao {
	public static void main(String[] args) throws SQLException {
		Veiculo veiculo = new Veiculo("KGB1986","Corsa",2008,"Carro","Sim");
		try(Connection connection = new ConnectionFactory().recuperaConexao()){
			VeiculoDAO veiculoDAO = new VeiculoDAO(connection);
			veiculoDAO.salvarVeiculo(veiculo);
		}
	}
}
