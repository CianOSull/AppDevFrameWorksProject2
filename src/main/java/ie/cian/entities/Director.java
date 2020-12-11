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
	
	/*
	 * For some reason directorName is needed, don't know why but program doesn't work without it
	 */
	@Column(nullable = false, unique = true)
	private String directorName;
	
	@OneToMany(mappedBy = "filmDirector", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Film> directorFilms = new ArrayList<>();

	public Director(String fn, String sm) {
		this.firstname = fn;
		this.surname = sm;
		this.directorName = fn + " " + sm;
	}
}