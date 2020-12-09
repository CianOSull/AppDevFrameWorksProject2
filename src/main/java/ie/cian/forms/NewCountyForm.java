package ie.cian.forms;

import lombok.Data;
//import javax.validation.constraints.Size;

@Data
public class NewCountyForm {
	
	// This class only consists of this field
	/*
	 * The idea is:
	 * You have a form, you have fields in that form.
	 * You have an object, and every piece of inforamtion
	 * the user provides corresponds to a field in that object
	 */
	// This specifies that this string must be min 4 and max 30
	// Doesn't work though because the improt above doesn't work
	// @Size(min = 4, max = 30)
	private String newCountyName;
}
