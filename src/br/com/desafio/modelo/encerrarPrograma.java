package br.com.desafio.modelo;

import br.com.desafio.modelo.service.ValidaService;

public class encerrarPrograma {
	public static void posMenu() {
		System.out.println("PROGRAMA ENCERRADO!");
		
		System.out.println("Deseja reiniciar o programa: (1)Sim - (2)Não");
		String transforma = "";
		int escolha = ValidaService.ValidaScannerInt(transforma);
		if (escolha == 1) {
			System.out.println("Iniciando o menu!");
			Menu.menu();
		} else if(escolha == 2) {
			System.out.println("SISTEMA DESLIGADO!");
		} else {
			System.out.println("Selecione uma opcao valida!");
			posMenu();
		}
	}
}
