package semester_1_assignment_3_Research;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class SerializationInterfaceResearch extends UnicastRemoteObject implements SerializationResearchRemote {

	private List<ModelResearch> researchList;
	private final String filePath = "research.ser"; // File path for storing the serialized data

	// Constructor initializes the research list and loads data from file
	public SerializationInterfaceResearch() throws RemoteException {
		super();
		researchList = new ArrayList<>(); // Initialize the research list as empty
		loadResearch(); // Load existing research data from file (if available)
	}

	// Method to load research data from the serialized file
	private void loadResearch() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
			Object object = ois.readObject(); // Read object from the file
			if (object instanceof List<?>) { // Check if the object is a list
				List<?> tempList = (List<?>) object;
				if (!tempList.isEmpty() && tempList.get(0) instanceof ModelResearch) {
					researchList = (List<ModelResearch>) tempList; // If valid, cast to List<ModelResearch>
				} else {
					researchList = new ArrayList<>(); // If invalid, set as empty list
				}
			} else {
				researchList = new ArrayList<>(); // If file doesn't contain valid data, set as empty list
			}
		} catch (IOException | ClassNotFoundException e) {
			researchList = new ArrayList<>(); // If error, initialize as empty list
		}
	}

	// Method to save the research list into a serialized file
	public void saveResearch() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
			oos.writeObject(researchList); // Write the research list to the file
		} catch (IOException e) {
			e.printStackTrace(); // Handle IO exceptions during saving
		}
	}

	// Method to get the first research item (returns a default if list is empty)
	@Override
	public ModelResearch getResearch() throws RemoteException {
		if (researchList.isEmpty()) {
			return new ModelResearch("Default Title", 0L, 0L, "Default Description"); // Default object if list is empty
		}
		return researchList.get(0); // Return the first research item
	}

	// Method to add a new research item to the list
	@Override
	public void addResearch(ModelResearch newResearch) throws RemoteException {
		researchList.add(newResearch); // Add new research to the list
		saveResearch(); // Save the updated list to the file
	}

	// Method to remove a specific research item from the list
	@Override
	public void removeResearch(ModelResearch researchToDelete) throws RemoteException {
		researchList.remove(researchToDelete); // Remove the specified research item
		saveResearch(); // Save the updated list to the file
	}

	// Method to remove a research item by index
	@Override
	public void removeResearch(int index) throws RemoteException {
		if (index >= 0 && index < researchList.size()) { // Check if index is valid
			researchList.remove(index); // Remove the item at the specified index
			saveResearch(); // Save the updated list to the file
		}
	}

	// Method to get the full list of research items
	@Override
	public List<ModelResearch> getResearchList() throws RemoteException {
		return researchList; // Return the list of research items
	}

	// Method to update a research item at a specific index with a new object
	@Override
	public void updateResearch(int index, ModelResearch updatedResearch) throws RemoteException {
		if (index >= 0 && index < researchList.size()) { // Check if index is valid
			researchList.set(index, updatedResearch); // Replace the research item at the index
			saveResearch(); // Save the updated list to the file
		}
	}
}
