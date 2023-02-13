package com.solvd.farm.service.jdbcservicesimpl;

import com.solvd.farm.DAO.AnimalDAO;
import com.solvd.farm.DAO.jdbcimpl.AnimalDAOImpl;
import com.solvd.farm.binary.Animal;
import com.solvd.farm.service.AnimalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

public class AnimalServiceImpl implements AnimalService {

    private static final Logger logger = LogManager.getLogger(AnimalServiceImpl.class);

    @Override
    public Animal breedAnimal(String type) {
        String[] names = {"Max", "Rex", "Lou", "Jeff"};
        String[] breeds = {"Angus", "Charolais", "Gelbvieh", "Hereford", "Limousin"};

        Random random = new Random();
        int randomIndex = random.nextInt(names.length);
        String randomName = names[randomIndex];

        randomIndex = random.nextInt(breeds.length);
        String randomBreed = breeds[randomIndex];

        Animal animal = new Animal(1, randomName, type, randomBreed);
        AnimalDAO animalDAO = new AnimalDAOImpl();
        animalDAO.create(animal);

        logger.info("Animal created");
        return animal;
    }


    @Override
    public void viewAllAnimals() {
        AnimalDAOImpl animalDAOImpl = new AnimalDAOImpl();
        List<Animal> animals = animalDAOImpl.getAll();
        for (Animal animal : animals) {
            logger.info("Animal ID: " + animal.getId());
            logger.info("Animal Name: " + animal.getName());
            logger.info("Animal Type: " + animal.getType());
            logger.info("Animal Breed: " + animal.getBreed());
        }
    }

    @Override
    public boolean slaughterAnimalById(long id) {
        AnimalDAO animalDAO = new AnimalDAOImpl();
        Animal animal = animalDAO.getById(id);
        if (animal != null) {
            return animalDAO.delete(animal);
        }
        return false;
    }
}
