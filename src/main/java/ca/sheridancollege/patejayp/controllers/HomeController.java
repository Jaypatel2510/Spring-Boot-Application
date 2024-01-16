package ca.sheridancollege.patejayp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.patejayp.beans.Capstone;

@Controller
public class HomeController {

	final String REST_URL = "http://localhost:8080/capstone/";

	@GetMapping("/")
	public String index(Model model, RestTemplate restTemplate) {
		ResponseEntity<Capstone[]> responseEntity = restTemplate.getForEntity(REST_URL, Capstone[].class);

		model.addAttribute("capstone", new Capstone());
		model.addAttribute("capstoneList", responseEntity.getBody());

		return "index";
	}

	@GetMapping(value = "/getCapstone/{id}", produces = "application/json")
	@ResponseBody
	public Capstone getCapstone(@PathVariable int id, RestTemplate restTemplate) {

		ResponseEntity<Capstone> responseEntity = restTemplate.getForEntity(REST_URL + id, Capstone.class);

		return responseEntity.getBody();
	}

	@PostMapping("/insertCapstone")
	public String insertCapstone(Model model, RestTemplate restTemplate, @ModelAttribute Capstone capstone) {
		
		restTemplate.postForLocation(REST_URL, capstone);
		
		ResponseEntity<Capstone[]> responseEntity = restTemplate.getForEntity(REST_URL, Capstone[].class);
		
		model.addAttribute("capstone", new Capstone());
		model.addAttribute("capstoneList", responseEntity.getBody());

		return "index";
	}

	
	@GetMapping("/deleteCapstone/{id}")
	public String deleteCapstone(Model model, RestTemplate restTemplate, @PathVariable Long id) {
			
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_URL + "delete/" + id, String.class);
		ResponseEntity<Capstone[]> responseEntity2 = restTemplate.getForEntity(REST_URL, Capstone[].class);
		
		model.addAttribute("capstone", new Capstone());
		model.addAttribute("capstoneList", responseEntity2.getBody());
		
		System.out.println(responseEntity);
		

		return "index";
	}
	
	@GetMapping("/voteUp/{id}")
	public String voteUpCapstone(Model model, RestTemplate restTemplate, @PathVariable Long id) {
			
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_URL + "voteUp/" + id, String.class);
		ResponseEntity<Capstone[]> responseEntity2 = restTemplate.getForEntity(REST_URL, Capstone[].class);
		
		model.addAttribute("capstone", new Capstone());
		model.addAttribute("capstoneList", responseEntity2.getBody());
		
		System.out.println(responseEntity);
		

		return "index";
	}
	
	@GetMapping("/voteDown/{id}")
	public String voteDownCapstone(Model model, RestTemplate restTemplate, @PathVariable Long id) {
			
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(REST_URL + "voteDown/" + id, String.class);
		ResponseEntity<Capstone[]> responseEntity2 = restTemplate.getForEntity(REST_URL, Capstone[].class);
		
		model.addAttribute("capstone", new Capstone());
		model.addAttribute("capstoneList", responseEntity2.getBody());
		
		System.out.println(responseEntity);
		

		return "index";
	}
}