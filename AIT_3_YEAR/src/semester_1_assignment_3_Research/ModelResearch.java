package semester_1_assignment_3_Research;

import java.io.Serializable;
import java.util.Objects;

public class ModelResearch implements Serializable {

	private String title;
	private long affectedPopulation;
	private long deathsPerYear;
	private String description;

	// Constructor
	public ModelResearch(String title, long affectedPopulation, long deathsPerYear, String description) {
		this.title = title;
		this.affectedPopulation = affectedPopulation;
		this.deathsPerYear = deathsPerYear;
		this.description = description;
	}

	// Getters and setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getAffectedPopulation() {
		return affectedPopulation;
	}

	public void setAffectedPopulation(long affectedPopulation) {
		this.affectedPopulation = affectedPopulation;
	}

	public long getDeathsPerYear() {
		return deathsPerYear;
	}

	public void setDeathsPerYear(long deathsPerYear) {
		this.deathsPerYear = deathsPerYear;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// toString method for printing
	@Override
	public String toString() {
		return "Title: " + title + "\n" + "Affected Population: " + affectedPopulation + "\n" + "Deaths Per Year: " + deathsPerYear + "\n" + "Description: " + description;
	}

	// Override equals method to compare ModelResearch objects by field values
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true; // Check if both references are the same
		if (obj == null || getClass() != obj.getClass())
			return false; // Check for null or different class

		ModelResearch that = (ModelResearch) obj; // Cast the object to ModelResearch
		return affectedPopulation == that.affectedPopulation && deathsPerYear == that.deathsPerYear && title.equals(that.title) && description.equals(that.description);
	}

	// Override hashCode method to ensure consistency with equals method
	@Override
	public int hashCode() {
		return Objects.hash(title, affectedPopulation, deathsPerYear, description);
	}
}
