package br.com.desafio.modelo.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import br.com.desafio.DAO.VeiculoDAO;
import br.com.desafio.connection.ConnectionFactory;
import br.com.desafio.modelo.Menu;
import br.com.desafio.modelo.Veiculo;

public class Service {

	private Veiculo veiculo;
	private Scanner scan = new Scanner(System.in);
	private LocalDate data = LocalDate.now();
	private static Connection connection;
	private VeiculoDAO veiculoDAO;

	public Service() {
		try {
			connection = new ConnectionFactory().recuperaConexao();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void cadastrarVeiculo() throws SQLException {

		veiculoDAO = new VeiculoDAO(connection);
		String placa = "";
		veiculo = new Veiculo();
		while (!(ValidaService.validaPlaca(placa) == true)) {
			System.out.println("Informe a Placa do veiculo Ex(XXX0000): ");
			placa = scan.next().toUpperCase();
			if (ValidaService.validaPlaca(placa) == false) {
				System.out.println("PLACA INVALIDA!");
				Menu.menu();
			}

			pesquisaPlaca(placa);

			System.out.println("Informe o modelo do veiculo:");
			String modelo;
			modelo = scan.next().toUpperCase();
			while (!(ValidaService.validaModelo(modelo) == true)) {
				System.out.println("Não use caracteres especiais e não informe apenas números");
				System.out.println("Informe o modelo do veiculo válido ex(COROLA , HB20): ");
				modelo = scan.next().toUpperCase();
				ValidaService.validaModelo(modelo);
			}

			this.veiculo.setModelo(modelo);

			System.out.println("Informe o ano do veiculo:");
			int ano;
			while (true) {
				try {
					ano = scan.nextInt();
					break;
				} catch (Exception e) {
					System.out.println("Valor Invalido, tente novamente");
					scan.nextLine();
				}
			}
			while (!(ano > 1886 && ano <= data.getYear() + 1)) {
				System.out.println("Insira o ano correto do veiculo:");
				try {
					ano = scan.nextInt();
					break;
				} catch (Exception e) {
					System.out.println("Valor Invalido, tente novamente");
					scan.nextLine();
				}
			}

			this.veiculo.setAno(ano);

			System.out.println("Selecione o tipo do veiculo: (1)Carro - (2)Moto - (3)Caminhão");
			String transforma = "";
			int valor = ValidaService.ValidaScannerInt(transforma);
			while (!(valor <= 3 && valor >= 1)) {
				System.out.println("Selecione o tipo do veiculo: (1)Carro - (2)Moto - (3)Caminhão");
				valor = ValidaService.ValidaScannerInt(transforma);
			}
			if (valor == 1) {
				this.veiculo.setTipo("CARRO");
			} else if (valor == 2) {
				this.veiculo.setTipo("MOTO");
			} else if (valor == 3) {
				this.veiculo.setTipo("CAMINHÃO");
			}

			this.veiculo.setEstacionado("NÃO");
			veiculoDAO.salvarVeiculo(veiculo);

			System.out.println("---------Veiculo Cadastrado!---------");
		}
	}



	public Veiculo consultarPlaca() throws SQLException {
		String placa = "";
		veiculoDAO = new VeiculoDAO(connection);
		while (!(ValidaService.validaPlaca(placa) == true)) {
			System.out.println("Informe a Placa do veiculo Ex(XXX0000): ");
			placa = scan.next().toUpperCase();
			ValidaService.validaPlaca(placa);
			if (veiculoDAO.listaCadastrados().isEmpty() || veiculoDAO.listaCadastrados() == null) {
				System.out.println("Não há nenhum veiculo cadastrado");
				Menu.menu();
			}

			if (ValidaService.validaPlaca(placa) == false) {
				System.out.println("Placa invalida!");
				Menu.menu();
			}
			pesquisaPlaca(placa);
		}

		return veiculo;

	}

	public void estacionarVeiculo() throws SQLException {
		veiculoDAO = new VeiculoDAO(connection);
		String placa = "";
		while (!(ValidaService.validaPlaca(placa) == true)) {
			System.out.println("Informe a Placa do veiculo Ex(XXX0000): ");
			placa = scan.next().toUpperCase();

			if (ValidaService.validaPlaca(placa) == false) {
				System.out.println("Placa invalida");
				Menu.menu();
			}

			if (veiculoDAO.listaCadastrados().isEmpty() || veiculoDAO.listaCadastrados() == null) {
				System.out.println("Não há veiculos cadastrado");
				Menu.menu();
			}

			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CadastroDeVeiculo WHERE placa = ?");
			pstm.setString(1, placa);
			ResultSet rst = pstm.executeQuery();

			if (rst.next()) {
				if (rst.getString(6).equals("SIM")) {
					System.out.println("O veículo já está estacionado");
					Menu.menu();
				} else {
					Veiculo veiculo = new Veiculo(rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5),
							rst.getString(6));
					System.out.println(veiculo);
				}
			}

			System.out.println("Deseja estacionar este veiculo: (1)Sim - (2)Não");

			if (rst.next()) {
				if (!(rst.getString(6).equals(placa))) {
					System.out.println("Placa não Cadastrada");
					Menu.menu();
				}
			}

			String transforma = "";
			int selecione = ValidaService.ValidaScannerInt(transforma);
			while (!(selecione <= 3 && selecione >= 1)) {
				System.out.println("Deseja estacionar este veiculo: (1)Sim - (2)Não");
				selecione = ValidaService.ValidaScannerInt(transforma);
			}
			if (selecione == 1) {

				String sql = "UPDATE CadastroDeVeiculo SET ESTACIONADO = 'SIM' WHERE PLACA = ?";
				pstm = connection.prepareStatement(sql);
				pstm.setString(1, placa);
				pstm.executeUpdate();

				System.out.println("Veiculo estacionado!");
				Menu.menu();
			}else if (selecione == 2) {
				System.out.println("Cliente desistiu de estacionar");
				Menu.menu();
			}
		}

		System.out.println("Placa não localizada cadastre o veiculo para estacionar!");

	}

	public void retirarVeiculo() throws SQLException {
		veiculoDAO = new VeiculoDAO(connection);
		String placa = "";
		while (!(ValidaService.validaPlaca(placa) == true)) {
			System.out.println("Informe a Placa do veiculo Ex(XXX0000): ");
			placa = scan.next().toUpperCase();

			if (ValidaService.validaPlaca(placa) == false) {
				System.out.println("Placa invalida ou não encontrada");
				Menu.menu();
			}

			if (veiculoDAO.listaCadastrados().isEmpty() || veiculoDAO.listaCadastrados() == null) {
				System.out.println("Não há veiculos cadastrado");
				Menu.menu();
			}

			PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CadastroDeVeiculo WHERE placa = ?");
			pstm.setString(1, placa);
			ResultSet rst = pstm.executeQuery();

			if (rst.next()) {
				if (rst.getString(6).equals("NÃO")) {
					System.out.println("O veículo não está estacionado");
					Menu.menu();
				} else {
					Veiculo veiculo = new Veiculo(rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5),
							rst.getString(6));
					System.out.println(veiculo);
				}
			}

			if (rst.next()) {
				if (!(rst.getString(6).equals(placa))) {
					System.out.println("Placa não Cadastrada");
					Menu.menu();
				}
			}
			System.out.println("Deseja retirar este veiculo: (1)Sim - (2)Não");
			String transforma = "";
			int selecione = ValidaService.ValidaScannerInt(transforma);
			while (!(selecione <= 3 && selecione >= 1)) {
				System.out.println("Deseja retirar este veiculo: (1)Sim - (2)Não");
				selecione = ValidaService.ValidaScannerInt(transforma);
			}
			if (selecione == 1) {

				String sql = "UPDATE CadastroDeVeiculo SET ESTACIONADO = 'NÃO' WHERE PLACA = ?";
				pstm = connection.prepareStatement(sql);
				pstm.setString(1, placa);
				pstm.executeUpdate();

				System.out.println("Veiculo saiu do estacionamento!");
				Menu.menu();
			}else if (selecione == 2) {
				System.out.println("Cliente desistiu de estacionar");
				Menu.menu();
			}
		}

		System.out.println("Este veiculo não está estacionado!");

	}

	public void listarCadastrado() throws SQLException {
		veiculoDAO = new VeiculoDAO(connection);
		System.out.println("========= Exibindo veiculos cadastrados! =========");
		if (veiculoDAO.listaCadastrados().isEmpty()) {
			System.out.println("Não há nenhum veículo Cadastrado!");
		} else {
			veiculoDAO.listaCadastrados().forEach(cadastro -> System.out.println(cadastro));
		}
	}

	public void listarEstacionado() throws SQLException {
		veiculoDAO = new VeiculoDAO(connection);
		System.out.println("========= Exibindo veiculos estacionados! =========");
		PreparedStatement stmt = connection
				.prepareStatement("SELECT * FROM CadastroDeVeiculo WHERE estacionado = 'SIM'");
		ResultSet rst = stmt.executeQuery();

		while (rst.next()) {
			Veiculo veiculo = new Veiculo(rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5),
					rst.getString(6));
			System.out.println("estacionado: " + veiculo);
		}

	}
	public void pesquisaPlaca(String placa) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM CadastroDeVeiculo WHERE placa = ?");
		stmt.setString(1, placa);
		ResultSet rst = stmt.executeQuery();

		if (rst.next()) {
			Veiculo veiculo = new Veiculo(rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5),
					rst.getString(6));
			System.out.println(veiculo);
			System.out.println("O veículo já está cadastrado");
			Menu.menu();
		} else {
			this.veiculo.setPlaca(placa);
		}
	}

	public static void fechaConexao() throws SQLException {
		connection.close();
	}
}
