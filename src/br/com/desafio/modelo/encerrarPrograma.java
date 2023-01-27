package br.com.desafio.modelo;

import java.sql.SQLException;

import br.com.desafio.modelo.service.Service;
import br.com.desafio.modelo.service.ValidaService;

public class encerrarPrograma {
	public static void posMenu() throws SQLException {
		System.out.println("PROGRAMA ENCERRADO!");
		
		System.out.println("Deseja reiniciar o programa: (1)Sim - (2)Não");
		String transforma = "";
		int escolha = ValidaService.ValidaScannerInt(transforma);
		if (escolha == 1) {
			System.out.println("Iniciando o menu!");
			Menu.menu();
		} else if(escolha == 2) {
			System.out.println("SISTEMA DESLIGADO!");
			Service.fechaConexao();
		} else {
			System.out.println("Selecione uma opcao valida!");
			posMenu();
		}
		
	}
}
