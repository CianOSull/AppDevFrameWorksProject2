package ie.cian.forms;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class NewTownForm {
	private String newTownName;
	
	@NotNull
	private int countyId;
}
