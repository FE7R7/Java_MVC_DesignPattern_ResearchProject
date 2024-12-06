package semester_1_assignment_3_Research;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServerWaterResearch {

	public static void main(String[] args) {

		System.out.println("Server has started");

		try {
			SerializationInterfaceResearch sR = new SerializationInterfaceResearch();

			// Start RMI Registry
			Registry registry = LocateRegistry.createRegistry(1234);

			// Bind the SerializationResearch instance as SerializationResearchRemote
			registry.rebind("researchController", sR);

			System.out.println("Manager bound to registry. Server is ready.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}