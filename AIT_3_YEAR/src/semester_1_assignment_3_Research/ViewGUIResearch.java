package semester_1_assignment_3_Research;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ViewGUIResearch extends JFrame {

	private final ControllerResearch cR;
	private JTextArea researchDisplayArea;
	private JTextField titleField, populationField, deathsField, descriptionField;
	private JPanel researchPanel;
	private JRadioButton[] researchRadioButtons;
	private ButtonGroup researchButtonGroup;

	// Constructor to initialize the view with the controller
	public ViewGUIResearch(ControllerResearch cR) {
		this.cR = cR; // Assign the controller object passed into the constructor
		initialize();
	}

	// Initializes the GUI components and layout
	private void initialize() {
		setTitle("Research -> Water is a Basic Human Need");
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the display area for the research list (non-editable)
		researchDisplayArea = new JTextArea();
		researchDisplayArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(researchDisplayArea);

		// Buttons to add, delete, display all, and update research
		JButton addButton = new JButton("Add Research");
		addButton.addActionListener(e -> handleAdd());

		JButton deleteButton = new JButton("Delete Research");
		deleteButton.addActionListener(e -> handleDelete());

		JButton displayButton = new JButton("Display All");
		displayButton.addActionListener(e -> handleDisplayAll());

		JButton updateButton = new JButton("Update Research");
		updateButton.addActionListener(e -> handleUpdate());

		// Fields for user to input the research details
		titleField = new JTextField(20);
		populationField = new JTextField(20);
		deathsField = new JTextField(20);
		descriptionField = new JTextField(20);

		// Panel to display input fields vertically
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.add(new JLabel("Title:"));
		inputPanel.add(titleField);
		inputPanel.add(new JLabel("Affected Population:"));
		inputPanel.add(populationField);
		inputPanel.add(new JLabel("Deaths Per Year:"));
		inputPanel.add(deathsField);
		inputPanel.add(new JLabel("Description:"));
		inputPanel.add(descriptionField);

		// Panel to hold buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(displayButton);
		buttonPanel.add(updateButton);

		// Panel to display radio buttons for each research item
		researchPanel = new JPanel();
		researchPanel.setLayout(new BoxLayout(researchPanel, BoxLayout.Y_AXIS));

		// Main panel layout to hold the display area, input fields, and buttons
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(scrollPane, BorderLayout.CENTER);
		mainPanel.add(inputPanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(researchPanel, BorderLayout.WEST);

		add(mainPanel); // Add the main panel to the frame
	}

	// Handle adding a new research entry
	private void handleAdd() {
		try {
			// Create a new research object from the fields
			ModelResearch research = new ModelResearch(titleField.getText(), Long.parseLong(populationField.getText()), Long.parseLong(deathsField.getText()), descriptionField.getText());
			// Add the research via the controller
			cR.addResearch(research);
			// Refresh the displayed research list
			refreshListDisplay();
		} catch (Exception e) {
			e.printStackTrace(); // Handle potential errors
		}
	}

	// Handle deleting a selected research item
	private void handleDelete() {
		try {
			// Find the selected radio button and delete the corresponding research item
			for (int i = 0; i < researchRadioButtons.length; i++) {
				if (researchRadioButtons[i].isSelected()) {
					cR.removeResearch(i); // Remove by index
					break;
				}
			}
			// Refresh the displayed research list
			refreshListDisplay();
		} catch (Exception e) {
			e.printStackTrace(); // Handle potential errors
		}
	}

	// Handle updating a selected research item
	private void handleUpdate() {
		try {
			// Find the selected radio button and update the corresponding research item
			for (int i = 0; i < researchRadioButtons.length; i++) {
				if (researchRadioButtons[i].isSelected()) {
					// Create an updated research object from the fields
					ModelResearch updatedResearch = new ModelResearch(titleField.getText(), Long.parseLong(populationField.getText()), Long.parseLong(deathsField.getText()), descriptionField.getText());
					// Update the research via the controller
					cR.updateResearch(i, updatedResearch);
					break;
				}
			}
			// Refresh the displayed research list
			refreshListDisplay();
		} catch (Exception e) {
			e.printStackTrace(); // Handle potential errors
		}
	}

	// Handle displaying all the research items in the display area
	private void handleDisplayAll() {
		refreshListDisplay(); // Refresh the list to show all research items
	}

	// Refresh the list of research items displayed in the UI
	private void refreshListDisplay() {
		try {
			// Retrieve the updated list of research from the controller
			List<ModelResearch> researchList = cR.getResearchList();
			researchPanel.removeAll(); // Clear existing radio buttons
			researchDisplayArea.setText(""); // Clear the display area
			researchButtonGroup = new ButtonGroup(); // Initialize a new button group

			// Create a radio button for each research item and display it
			researchRadioButtons = new JRadioButton[researchList.size()];
			for (int i = 0; i < researchList.size(); i++) {
				ModelResearch research = researchList.get(i);

				// Display research information in the text area
				researchDisplayArea.append("Title: " + research.getTitle() + "\n");
				researchDisplayArea.append("Population: " + research.getAffectedPopulation() + "\n");
				researchDisplayArea.append("Deaths: " + research.getDeathsPerYear() + "\n");
				researchDisplayArea.append("Description: " + research.getDescription() + "\n");
				researchDisplayArea.append("\n");

				// Create a radio button for selecting a research item
				JRadioButton radioButton = new JRadioButton(research.getTitle());
				int index = i; // Capture the index for the action listener
				radioButton.addActionListener((ActionEvent e) -> populateFields(researchList.get(index))); // Populate fields when radio button is selected
				researchButtonGroup.add(radioButton); // Add radio button to the group
				researchRadioButtons[i] = radioButton;
				researchPanel.add(radioButton); // Add radio button to the panel
			}
			researchPanel.revalidate(); // Refresh the panel to reflect changes
			researchPanel.repaint(); // Repaint the panel
		} catch (RemoteException e) {
			e.printStackTrace(); // Handle potential errors
		}
	}

	// Populate the input fields with the selected research item
	private void populateFields(ModelResearch research) {
		titleField.setText(research.getTitle());
		populationField.setText(String.valueOf(research.getAffectedPopulation()));
		deathsField.setText(String.valueOf(research.getDeathsPerYear()));
		descriptionField.setText(research.getDescription());
	}
}
