package ca.sheridancollege.patejayp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.patejayp.beans.Capstone;
import ca.sheridancollege.patejayp.database.DatabaseAccess;

@RestController
@RequestMapping("/capstone/") 
public class CapstoneController {

	@Autowired
	private DatabaseAccess da;

	@GetMapping
	public List<Capstone> getCapstoneCollection() {
		return da.findAll();
	}

	@GetMapping(value = "/{id}")
	public Capstone getIndividualCapstone(@PathVariable Long id) {
		return da.findById(id);
	}

	@GetMapping(value = "/delete/{id}")
	public String deleteIndiviualCapstone(@PathVariable Long id) {
		da.deleteById(id);
		return "Delete";
	}

	@GetMapping(value = "/voteUp/{id}")
	public String voteUpIndiviualCapstone(@PathVariable Long id) {
		Capstone capstone = da.getVote(id);
		da.VoteUp(capstone, id);
		return "Vote Up";
	}
	
	@GetMapping(value = "/voteDown/{id}")
	public String voteDownIndiviualCapstone(@PathVariable Long id) {
		Capstone capstone = da.getVote(id);
		da.VoteDown(capstone, id);
		return "Vote Down";
	}
	
	@PostMapping(consumes = "application/json")
	public String postCapstone(@RequestBody Capstone capstone) {
		return "http://localhost:8080/capstone/" + da.save(capstone);
	}

	@PutMapping(consumes = "application/json")
	public String putCapstoneCollection(@RequestBody List<Capstone> capstoneList) {
		da.deleteAll();
		da.saveAll(capstoneList);
		return "Total records: " + da.count();
	}
}