import br.com.SBD.banco_SBD.*;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Worker {
	public static void main(String args[]) {
		try {
			Bank obj = BankImpl.getInstance();
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("Bank", obj);
			System.out.println("Waiting for work...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
