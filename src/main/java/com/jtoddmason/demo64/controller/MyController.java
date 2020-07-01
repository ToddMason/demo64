package com.jtoddmason.demo64.controller;

import com.jtoddmason.demo64.model.City;
import com.jtoddmason.demo64.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private ICityService cityService;

    @GetMapping("/showCities")
    public List<City> findCities(Model model) {

    	List<City> cities = (List<City>) cityService.findAll();
		System.out.println("We entered the findCities Method");

        return cities;
    }
    
    @PostMapping("/createCity")
    ResponseEntity<MessageResponse> createCity(@RequestBody City city){

    	MessageResponse messageResponse = new MessageResponse();
		
		if(city != null) {
			
			// Invalid city.
			if(city.getName().isEmpty()) {
				messageResponse.setMessage("City name is invalid.");
				messageResponse.setResult("Error");
				return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.BAD_REQUEST);
			}
			else {
				
				// Use the City Service to create the new city.
				cityService.createNewCity(city);
				
				// Send the response
				messageResponse.setMessage("Success");
				messageResponse.setResult(city);
				return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
				
			}
			
		}
		else {
			return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.BAD_REQUEST);
		}

    	
    }
    
    @PostMapping("/updateCity")
    ResponseEntity<MessageResponse> updateCity(@RequestBody City city) {
    	
    	MessageResponse messageResponse = new MessageResponse();
		
		if(city != null) {
			
			// Invalid city.
			if(city.getName().isEmpty()) {
				messageResponse.setMessage("City name is invalid.");
				messageResponse.setResult("Error");
				return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.BAD_REQUEST);
			}
			
			// Invalid population.
			if(city.getPopulation() == 0) {
				messageResponse.setMessage("City population is invalid.");
				messageResponse.setResult("Error");
				return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.BAD_REQUEST);
				
			}
				
			// Use the City Service to create the new city.
			cityService.createNewCity(city);
				
			// Send the response
			messageResponse.setMessage("Success");
			messageResponse.setResult(city);
			return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
				
		}
		else {
			return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.BAD_REQUEST);
		}
    }
    
    @PostMapping("/deleteCity")
    ResponseEntity<MessageResponse> deleteCity(@RequestParam(value = "id") int id) {
    	
    	MessageResponse messageResponse = new MessageResponse();
    	
    	if (id == 0) {
    		messageResponse.setMessage("City not found");
    		messageResponse.setResult("Failed");
    		return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.BAD_REQUEST);
    	}
    	
    	cityService.deleteCity(id);
		messageResponse.setMessage("City deleted");
		messageResponse.setResult("Success");
		
    	return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
    	
    }
}
