package br.com.SBD.banco_SBD;

import java.sql.Date;

public class Event {
	private Integer id;
	private OperationType action;
	private Date date;
	private Long amount;
	private Conta source;
	private Conta destination;

	public Event(Integer id, OperationType action, Date date, Long amount,
	             Conta source, Conta destination) {
		this.id = id;
		this.action = action;
		this.date = date;
		this.amount = amount;
		this.source = source;
		this.destination = destination;
	}

	public Conta getDestination() {
		return destination;
	}

	public void setDestination(Conta destination) {
		this.destination = destination;
	}

	public Conta getSource() {
		return source;
	}

	public void setSource(Conta source) {
		this.source = source;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public OperationType getAction() {
		return action;
	}

	public void setAction(OperationType action) {
		this.action = action;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Event{"
			+ "id='" + id + "'"
			+ "action='" + action + "'"
			+ "date='" + date + "'"
			+ "amount='" + amount + "'"
			+ "source='" + source + "'"
			+ "destination='" + destination + "'"
			+ "}";
	}
}
