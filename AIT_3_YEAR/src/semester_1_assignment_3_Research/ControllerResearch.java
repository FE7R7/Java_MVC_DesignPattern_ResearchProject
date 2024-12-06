package semester_1_assignment_3_Research;

import java.rmi.RemoteException;
import java.util.List;

public class ControllerResearch {

	private final SerializationResearchRemote sR;

	public ControllerResearch(SerializationResearchRemote sR) {
		this.sR = sR; // Initialize with the remote interface
	}

	public void addResearch(ModelResearch newResearch) throws RemoteException {
		sR.addResearch(newResearch); // Add new research to the remote server
	}

	public void removeResearch(int index) throws RemoteException {
		sR.removeResearch(index); // Remove research by index from the remote server
	}

	public void updateResearch(int index, ModelResearch updatedResearch) throws RemoteException {
		sR.updateResearch(index, updatedResearch); // Update research at a specific index on the remote server
	}

	public List<ModelResearch> getResearchList() throws RemoteException {
		return sR.getResearchList(); // Retrieve the list of research from the remote server
	}

	public int getIndexOfResearch(ModelResearch research) throws RemoteException {
		return sR.getResearchList().indexOf(research); // Find the index of the given research
	}
}
