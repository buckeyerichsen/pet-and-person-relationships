package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Long> {
    List<Pet> findByName(String name);
}
