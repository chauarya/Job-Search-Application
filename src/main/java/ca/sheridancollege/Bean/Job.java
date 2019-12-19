package ca.sheridancollege.Bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data


public class Job implements Serializable {
 
	private static final long serialVersionUID = -602937287928620830L;

	private String jobid;
	private String company;
	private String position;
	private String ptype;
	private String status;
	private int id;
	 
	
	
	
	
	
}
