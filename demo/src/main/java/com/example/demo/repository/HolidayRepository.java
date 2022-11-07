package com.example.demo.repository;

import com.example.demo.model.Holiday;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String>  {

//    @Query("select h from Holiday h")
//    List<Holiday> getAllHolidays();

}

