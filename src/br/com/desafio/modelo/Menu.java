package br.com.desafio.modelo;

import br.com.desafio.modelo.service.Service;
import br.com.desafio.modelo.service.ValidaService;

public class Menu {
	
	static Service servico = new Service();
	
	
	public static void menu() {

		System.out.println("============MENU============");
		System.out.println("(1) -  CADASTRAR VEICULO");
		System.out.println("(2) -  CONSULTAR POR PLACA");
		System.out.println("(3) -  ESTACIONAR VEICULO");
		System.out.println("(4) -  RETIRAR VEICULO");
		System.out.println("(5) -  LISTAR CARROS CADASTRADOS");
		System.out.println("(6) -  LISTAR CARROS ESTACIONADOS");
		System.out.println("(7) -  Sair");
		
		String transforma = "";
		int opcao = ValidaService.ValidaScannerInt(transforma);
		switch (opcao) {
		case 1:
			servico.cadastrarVeiculo();
			menu();
			break;
		case 2:
			servico.consultarPlaca();
			menu();
			break;
		case 3:
			servico.estacionarVeiculo();
			menu();
			break;
		case 4:
			servico.retirarVeiculo();
			menu();
			break;
		case 5:
			servico.listarCadastrado();
			menu();
			break;
		case 6:
			servico.listarEstacionado();
			menu();
			break;
		case 7:
			encerrarPrograma.posMenu();
			break;
		default:
			System.out.println("Insira um valor válido!");
			menu();
		}
	}
	
}
