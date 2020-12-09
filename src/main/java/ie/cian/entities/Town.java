package ie.cian.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//Better to set table name like this rather than in entity
@Table(name = "town")
public class Town {
	@Id
	// This defines the primary key generation, common is identity
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int townId;
	
	@Column(nullable = false )
	private String townName;
	
	/* IMPORTANT NOTE
	 * This is saying that a town has a county.
	 * Normally this would be done in a database using a
	 * foreign key.
	 * But since this is ORM and we are modelling objects
	 * This needs an object instead
	 */
	// This is a setting up that a county can have many towns
	// This is the owning side of the relationship
	// Many towns are in one county. 
	@ManyToOne
	// Name this joining column "column_id"
	// This is the same as the column annotaiton but for a join
	// relationship
	@JoinColumn(name = "county_id", nullable = false)
	private County townCounty;
	
	// This method is needed because when committing to the database, 
	// a Town object is first created but it does not have the townId field. 
	// Hence we need this constructor.
	public Town(String townName, County townCounty) {
		this.townName = townName;
		this.townCounty = townCounty;
	}
}