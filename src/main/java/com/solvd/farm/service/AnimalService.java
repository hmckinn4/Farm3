package com.solvd.farm.service;

import com.solvd.farm.binary.Animal;

public interface AnimalService {

    Animal breedAnimal(String type);

    void viewAllAnimals();

    boolean slaughterAnimalById(long id);

}
