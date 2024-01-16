package ca.sheridancollege.patejayp.beans;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Capstone {
	
	private Long id;
	private String name;
	private String description;
	private String author;
	private int vote;
}
