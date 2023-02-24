package com.solvd.farm.service.myBatis;

import com.solvd.farm.DAO.jdbcimpl.AnimalDAOImpl;
import com.solvd.farm.binary.Animal;
import com.solvd.farm.binary.Product;
import com.solvd.farm.service.AnimalService;
import com.solvd.farm.service.jdbcservicesimpl.ProductServiceImpl;
import com.solvd.farm.util.MyBatisDaoFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

public class AnimalServiceImpl implements AnimalService {

    private static final Logger LOGGER = LogManager.getLogger(AnimalServiceImpl.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();

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
        try(SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            AnimalDAOImpl animalDAOImpl = sqlSession.getMapper(AnimalDAOImpl.class);
            animalDAOImpl.create(animal);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("Animal created");
        return animal;
    }

    @Override
    public void viewAllAnimals() {
        try(SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            AnimalDAOImpl animalDAOImpl = sqlSession.getMapper(AnimalDAOImpl.class);
            List<Animal> animals = animalDAOImpl.getAll();
            for (Animal animal : animals) {
                LOGGER.info("Animal ID: " + animal.getId());
                LOGGER.info("Animal Name: " + animal.getName());
                LOGGER.info("Animal Type: " + animal.getType());
                LOGGER.info("Animal Breed: " + animal.getBreed());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean slaughterAnimalById(long id) {
        boolean deleted = false;
        try(SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            AnimalDAOImpl animalDAOImpl = sqlSession.getMapper(AnimalDAOImpl.class);
            Animal animal = animalDAOImpl.getById(id);
            if (animal != null) {
                deleted = animalDAOImpl.delete(animal);
                if (deleted) {
                    ProductServiceImpl productServiceImpl = new ProductServiceImpl();
                    Product product = productServiceImpl.createProduct(animal.getType());
                    System.out.println("---------------------------");
                    LOGGER.info("A product was created: " + product.getName());
                    System.out.println("---------------------------");
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return deleted;
    }

}
