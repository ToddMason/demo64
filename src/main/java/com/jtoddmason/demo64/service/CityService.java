package com.jtoddmason.demo64.service;

import com.jtoddmason.demo64.model.*;
import com.jtoddmason.demo64.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService implements ICityService {

    @Autowired
    private CityRepository repository;

    @Override
    public List<City> findAll() {

    	List<City> cities = (List<City>) repository.findAll();

        return cities;
    }

	@Override
	public void createNewCity(City city) {
		
		repository.save(new City(city.getName(), city.getPopulation()));
		
	}

	@Override
	public void updateCity(City updateCity) {
		
		Optional<City> optCity = repository.findById(updateCity.getId());
		if (optCity.isPresent()) {
			City city = optCity.get();
			city.setName(city.getName());
			city.setPopulation(city.getPopulation());
			repository.save(city);
		}
				
	}

	@Override
	public void deleteCity(long id) {
		
		Optional<City> optCity = repository.findById(id);
		if (optCity.isPresent()) {
			City city = optCity.get();
			city.setName(city.getName());
			city.setPopulation(city.getPopulation());
			repository.delete(city);;
		}
		
				
	}

	

	
}