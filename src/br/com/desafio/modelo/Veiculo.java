package br.com.desafio.modelo;

public class Veiculo {
	

	private Integer id;
	private String modelo;
	private String placa;
	private int ano;
	private String tipo;
	private String estacionado = "NÃO";
	

	public Veiculo() {
		
	}

	public Veiculo(String placa, String modelo, int ano, String tipo, String estacionado) {
		this.placa = placa;
		this.modelo = modelo;
		this.ano = ano;
		this.tipo = tipo;
		this.estacionado = estacionado;
			
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getEstacionado() {
		return estacionado;
	}

	public void setEstacionado(String estacionado) {
		this.estacionado = estacionado;
	}

	@Override
	public String toString() {
		return "Veiculo modelo:" + modelo + ", placa:" + placa + ", ano:" + ano + ", tipo:" + tipo + ", estacionado:" + estacionado;
	}


	
	
	
	
	

}
