package com.jtoddmason.demo64.service;

import com.jtoddmason.demo64.model.*;

import java.util.List;

public interface ICityService {

    List<City> findAll();
    void createNewCity(City city);
    void updateCity(City city);
    void deleteCity(long id);
}