package br.com.desafio.modelo;

public class Veiculo {
	
	private String modelo;
	private String placa;
	private int ano;
	private String tipo;
	
	
	public Veiculo() {
		
	}

	public Veiculo(String modelo, String placa, int ano) {
		super();
		this.modelo = modelo;
		this.placa = placa;
		this.ano = ano;
			
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return "Veiculo modelo:" + modelo + ", placa:" + placa + ", ano:" + ano + ", tipo:" + tipo;
	}


	
	
	
	
	

}
