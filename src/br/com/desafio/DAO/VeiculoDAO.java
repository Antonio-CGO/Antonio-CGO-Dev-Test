package br.com.desafio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.desafio.modelo.Veiculo;

public class VeiculoDAO {
	private Connection connection;

	public VeiculoDAO(Connection connection) {
		this.connection = connection;
	}

	public void salvarVeiculo(Veiculo veiculo) throws SQLException {
		String sql = "INSERT INTO CadastroDeVeiculo (PLACA, MODELO, ANO, TIPO, ESTACIONADO) VALUES(?, ?, ?, ?, ?)";

		try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			pstm.setString(1, veiculo.getPlaca());
			pstm.setString(2, veiculo.getModelo());
			pstm.setInt(3, veiculo.getAno());
			pstm.setString(4, veiculo.getTipo());
			pstm.setString(5, veiculo.getEstacionado());
			pstm.execute();
			{
				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						veiculo.setId(1);
					}
				}
			}
		}
	}
	
	public List<Veiculo> listaCadastrados() throws SQLException{
		List<Veiculo> listaVeiculo = new ArrayList<>();
		String sql = "SELECT PLACA, MODELO, ANO, TIPO, ESTACIONADO FROM CadastroDeVeiculo";
		try (PreparedStatement pstm = connection.prepareStatement(sql)) {
			pstm.execute();
			try (ResultSet rst = pstm.getResultSet()) {
				while (rst.next()) {
					Veiculo veiculo = new Veiculo(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getString(4), rst.getString(5));
					listaVeiculo.add(veiculo);
				}
		}
	}
		return listaVeiculo;
	}
}
