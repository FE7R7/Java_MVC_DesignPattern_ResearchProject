package semester_1_assignment_3_Research;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SerializationResearchRemote extends Remote {

	// Add new research
	void addResearch(ModelResearch newResearch) throws RemoteException;

	// Remove research by object
	void removeResearch(ModelResearch researchToDelete) throws RemoteException;

	// Remove research by index
	void removeResearch(int index) throws RemoteException;

	// Get a single research item
	ModelResearch getResearch() throws RemoteException;

	// Get list of all research items
	List<ModelResearch> getResearchList() throws RemoteException;

	// Update research details by index
	void updateResearch(int index, ModelResearch updatedResearch) throws RemoteException;
}
