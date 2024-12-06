package semester_1_assignment_3_Research;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class RMIClientWaterResearch {

	public static void main(String[] args) {

		try {
			// Connect to RMI server
			Registry registry = LocateRegistry.getRegistry("localhost", 1234);

			// Retrieve the remote object and cast to the remote interface
			SerializationResearchRemote sR = (SerializationResearchRemote) registry.lookup("researchController");

			// Interact with the SerializationResearch, using the serialized object
			ModelResearch research = sR.getResearch();

			System.out.println("Loaded Research:");
			System.out.println(research);

			// Update research title (example)
			research.setTitle("Updated Title - Water Crisis Solutions");

			// Find the index of the research to update
			List<ModelResearch> researchList = sR.getResearchList();
			int index = researchList.indexOf(research); // Get the index of the existing research

			if (index != -1) {
				// Update the research on the server
				sR.updateResearch(index, research);
				System.out.println("Research updated!");
			} else {
				System.out.println("Research not found in the list!");
			}

			// After interacting with the data, launch the GUI
			ControllerResearch cR = new ControllerResearch(sR); // Pass the remote interface here
			ViewGUIResearch view = new ViewGUIResearch(cR);
			view.setVisible(true); // Display the GUI

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
