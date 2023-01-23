package br.com.desafio.modelo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.desafio.modelo.Menu;
import br.com.desafio.modelo.Veiculo;

public class Service {

	private Veiculo veiculo;
	private List<Veiculo> listaCadastrado = new ArrayList<>();
	private List<Veiculo> listaEstacionado = new ArrayList<>();
	private Scanner scan = new Scanner(System.in);
	private LocalDate data = LocalDate.now();

	public void cadastrarVeiculo() {

		String placa = "";
		veiculo = new Veiculo();
		while (!(ValidaService.validaPlaca(placa) == true)) {
			System.out.println("Informe a Placa do veiculo Ex(XXX0000): ");
			placa = scan.next().toUpperCase();
			if (ValidaService.validaPlaca(placa) == false) {
				System.out.println("PLACA INVALIDA!");
				Menu.menu();
			}
			if (listaCadastrado != null) {
				 for (int i = 0; i < listaCadastrado.size(); i++) {
						if (listaCadastrado.get(i).getPlaca().equals(placa)) {
						System.out.println("Veiculo já cadastrado!");
						Menu.menu();
					}
				}
			}
		}
		this.veiculo.setPlaca(placa);

		System.out.println("Informe o modelo do veiculo:");
		String modelo;
		modelo = scan.next().toUpperCase();
		while (!(ValidaService.validaModelo(modelo) == true)) {
			System.out.println("Não use numeros e caracteres especiais ");
			System.out.println("Informe o modelo do veiculo ex(COROLA): ");
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
		int opcao = ValidaService.ValidaScannerInt(transforma);
		while (!(opcao <= 3 && opcao >= 1)) {
			System.out.println("Selecione o tipo do veiculo: (1)Carro - (2)Moto - (3)Caminhão");
			opcao = ValidaService.ValidaScannerInt(transforma);
		}
		switch (opcao) {
		case 1:
			this.veiculo.setTipo("CARRO");
			break;
		case 2:
			this.veiculo.setTipo("MOTO");
			break;
		case 3:
			this.veiculo.setTipo("CAMINHÃO");
			break;
		

		}
		listaCadastrado.add(veiculo);

		System.out.println("---------Veiculo Cadastrado!---------");
	
	}

	public Veiculo consultarPlaca() {
		String placa = "";

		while (!(ValidaService.validaPlaca(placa) == true)) {
			System.out.println("Informe a Placa do veiculo Ex(XXX0000): ");
			placa = scan.next().toUpperCase();
			ValidaService.validaPlaca(placa);
			if (listaCadastrado.isEmpty() || listaCadastrado == null) {
				System.out.println("Não há nenhum veiculo cadastrado");
				Menu.menu();
			}

			if (ValidaService.validaPlaca(placa) == false) {
				System.out.println("Placa invalida!");
				Menu.menu();
			}
			for (Veiculo comparaList : listaCadastrado) {
				if (comparaList.getPlaca().equals(placa)) {
					System.out.println(comparaList);
					Menu.menu();
				}
			}
			for (Veiculo comparaList : listaCadastrado) {
				if (!(comparaList.getPlaca().equals(placa))) {
					System.out.println("Veiculo não Cadastrado!");
				}
			}

		}

		return veiculo;

	}

	public void estacionarVeiculo() {

		String placa = "";
		while (!(ValidaService.validaPlaca(placa) == true)) {
			System.out.println("Informe a Placa do veiculo Ex(XXX0000): ");
			placa = scan.next().toUpperCase();

			if (ValidaService.validaPlaca(placa) == false) {
				System.out.println("Placa invalida ou não encontrada");
				Menu.menu();
			}

			if (listaCadastrado.isEmpty() || listaCadastrado == null) {
				System.out.println("Não há veiculos cadastrado");
				Menu.menu();
			}

			for (Veiculo comparaList : listaCadastrado) {
				if (comparaList.getPlaca().equals(placa)) {
					 for (int i = 0; i < listaEstacionado.size(); i++) {
							if (listaEstacionado.get(i).getPlaca().equals(placa)) {
							System.out.println("Este veiculo já está estacionado");
							Menu.menu();
						}
					}
					System.out.println(comparaList);
					System.out.println("Deseja estacionar este veiculo: (1)Sim - (2)Não");
					String transforma = "";
					int opcao = ValidaService.ValidaScannerInt(transforma);
					switch (opcao) {
					case 1:
						listaEstacionado.add(comparaList);
						System.out.println("O veiculo foi estacionado!");
						Menu.menu();
						break;
					case 2:
						System.out.println("Cliente desistiu de estacionar");
						Menu.menu();
						break;
					default:
						System.out.println("Selecione uma opcao valida!");
					}
				}
			}
			System.out.println("Placa não localizada cadastre o veiculo para estacionar!");
		}
	}

	public void retirarVeiculo() {

		String placa = "";
		while (!(ValidaService.validaPlaca(placa) == true)) {
			System.out.println("Informe a Placa do veiculo Ex(XXX0000): ");
			placa = scan.next().toUpperCase();

			if (ValidaService.validaPlaca(placa) == false) {
				System.out.println("Placa invalida ou não encontrada");
				Menu.menu();
			}

			if (listaEstacionado.isEmpty() || listaEstacionado == null) {
				System.out.println("Não há veiculos estacionados");
				Menu.menu();
			}
			

			 for (int i = 0; i < listaEstacionado.size(); i++) {
				if (listaEstacionado.get(i).getPlaca().equals(placa)) {
					System.out.println("Deseja retirar este veiculo: (1)Sim - (2)Não");
					String transforma = "";
					int opcao = ValidaService.ValidaScannerInt(transforma);
					switch (opcao) {
					case 1:
						listaEstacionado.remove(i);
						System.out.println("O veiculo foi retirado!");
						Menu.menu();
						break;
					case 2:
						System.out.println("Cliente desistiu de retirar o veiculo");
						Menu.menu();
						break;
					default:
						System.out.println("Selecione uma opcao valida!");
					}
				}
			}
			 System.out.println("Este veiculo não está estacionado!");
		}
		

	}

	public void listarCadastrado() {
		System.out.println("========= Exibindo veiculos cadastrados! =========");
		if (listaCadastrado.isEmpty()) {
			System.out.println("Nenhum veiculo cadastrado!");
		}
		for (Veiculo veiculo : listaCadastrado) {
			System.out.println(veiculo);

		}
	}

	public void listarEstacionado() {
		System.out.println("========= Exibindo veiculos estacionados! =========");
		if (listaEstacionado.isEmpty()) {
			System.out.println("Nenhum veiculo estacionado!");
		}
		for (Veiculo veiculo : listaEstacionado) {
			System.out.println(veiculo);

		}
	}
}
