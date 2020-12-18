package ie.cian.forms;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class NewFilmForm {
	private String newFilmName;
	
	@NotNull
	private int directorId;
}
