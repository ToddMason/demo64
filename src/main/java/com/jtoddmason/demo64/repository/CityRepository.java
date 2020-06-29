package com.jtoddmason.demo64.repository;

import com.jtoddmason.demo64.model.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

}
