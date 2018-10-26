package com.hbjoe.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hbjoe.Spittle;
import com.hbjoe.data.SpittleRepository;

@Controller
@RequestMapping("/spittles")
public class SpittleController {
	private SpittleRepository spittleRepository;
	
	private static final String MAX_LONG_AS_STRING = Long.toString(Long.MAX_VALUE);
	
	@Autowired
	public SpittleController(SpittleRepository spittleRepository) {
		this.spittleRepository = spittleRepository;
	}
	
//	@RequestMapping(method=RequestMethod.GET)
//	public String spittles(Model model) {
//		model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
//		return "spittles";
//	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Spittle> spittles(@RequestParam("max") long max, @RequestParam("count") int count) {
		return spittleRepository.findSpittles(max,count);
	}
	
	// like... /spittles/show?spittle_id=12345
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public String showSpittle(@RequestParam("spittle_id") long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}
	
	@RequestMapping(value="/{spittleId}", method=RequestMethod.GET)
	public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showRegistrationForm() {
		return "registerForm";
	}
	
}
