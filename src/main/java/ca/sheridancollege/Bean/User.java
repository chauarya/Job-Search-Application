package ca.sheridancollege.Bean;
import java.io.Serializable;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
	 

	/**
	 * 
	 */
	private static final long serialVersionUID = 7540792097330253967L;
	private long userId;
	private String userName;
	private String encryptedPassword;
	private String role;

	
}
