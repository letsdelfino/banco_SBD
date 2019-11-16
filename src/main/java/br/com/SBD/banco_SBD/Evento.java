package br.com.SBD.banco_SBD;

import java.sql.Date;

public class Evento {
	private Date data_evento;
	private Integer id_evento;
	private Long valor;
	private String tipo_evento;
	private Integer id_destino_evento;
	private Integer id_origem_evento;

	public Date getDataEvento(){
		return data_evento;
	}

	public void setDataEvento(Date data_evento){
		this.data_evento = data_evento;
	}

	public Integer getId() {
		return id_evento;
	}

	public void setId(Integer id) {
		this.id_evento = id;
	}

	public Long getValor() {
		return valor;
	}

	public void setValor(Long valor) {
		this.valor = valor;
	}

	public String getTipoOperacao() {
		return tipo_evento;
	}

	public void setTipoOperacao(String tipo_operacao) {
		this.tipo_evento = tipo_operacao;
	}

	public Integer getIdDestinoEvento() {
		return id_destino_evento;
	}

	public void setIdDestinoEvento(Integer id_destino_evento) {
		this.id_destino_evento = id_destino_evento;
	}

	public Integer getIdOrigemEvento() {
		return id_origem_evento;
	}

	public void setIdOrigemEvento(Integer id_origem_evento) {
		this.id_origem_evento = id_origem_evento;
	}

	@Override
	public String toString() {
		return "Evento{"
			+ "data_evento='" + data_evento + "'"
			+ "id_evento='" + id_evento + "'"
			+ "valor='" + valor + "'"
			+ "tipo_evento='" + tipo_evento + "'"
			+ "id_destino_evento='" + id_destino_evento + "'"
			+ "id_origem_evento='" + id_origem_evento + "'"
			+ "}";
	}
}
