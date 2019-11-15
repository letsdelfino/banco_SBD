package br.com.SBD.banco_SBD;

import java.math.BigDecimal;
import java.sql.Date;

public class Evento {
	private Date data_evento;
	private Integer id_evento;
	private BigDecimal valor;
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
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
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

	public String toString() {
		StringBuilder strRetorno = new StringBuilder();
		strRetorno.append("-------- ");
		strRetorno.append("\nData operacao: "+getDataEvento());
		strRetorno.append("\nTipo operacao: "+getTipoOperacao());
		strRetorno.append("\nValor:"+getValor().doubleValue());
		strRetorno.append("\nConta Destino: "+getId());
		strRetorno.append("\n-------- ");
		
		return strRetorno.toString(); 
	}
}
