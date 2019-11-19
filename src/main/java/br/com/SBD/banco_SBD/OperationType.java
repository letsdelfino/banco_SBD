package br.com.SBD.banco_SBD;

import java.util.Map;
import java.util.HashMap;

public enum OperationType {
	DEPOSIT(0), WITHDRAW(1), TRANSFERENCE(2), ACCOUNT_OPEN(3), ACCOUNT_CLOSE(4);

	private Integer id;

	private OperationType(Integer id)
	{
		this.id = id;
	}

	private static final Map<Integer, OperationType> idMap = new HashMap<>();

	static {
		for (OperationType e : OperationType.values())
			idMap.put(e.getId(), e);
	}

	public static OperationType byId(Integer id)
	{
		return idMap.get(id);
	}

	public final int getId()
	{
		return id;
	}
}
