package ie.cian.entities;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "director")
public class Director {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int directorId;
	
	@Column(nullable = false, unique = true  )
	private String firstname;
	
	@Column(nullable = false, unique = true)
	private String surname;
	
	// One County has many towns
	// When fetching a County from a query, do not fetch the list of towns. 
	// If the county is removed from the database, also remove the towns. 
	// One county to many towns
	// Mapped by is specifically saying what field to use in town
	// Use the fetch type field, lazy means when getting county
	// information, dont get the twon information at the same
	// time. It will rely on you getting the town information.
	// Saves on resources if you dont need all of the town 
	// Cascade means if a county is deleted to remove all towns in that county
	@OneToMany(mappedBy = "filmDirector", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Film> directorFilms = new ArrayList<>();

	public Director(String fn, String sm) {
		this.firstname = fn;
		this.surname = sm;
	}
}