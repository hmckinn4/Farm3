package com.solvd.farm;

import com.solvd.farm.DAO.jdbcimpl.AnimalDAOImpl;
import com.solvd.farm.DAO.jdbcimpl.CropDAOImpl;
import com.solvd.farm.binary.Animal;
import com.solvd.farm.binary.Crop;
import com.solvd.farm.binary.Farm;
import com.solvd.farm.service.jdbcservicesimpl.AnimalServiceImpl;
import com.solvd.farm.service.jdbcservicesimpl.CropServiceImpl;
import com.solvd.farm.service.jdbcservicesimpl.FarmServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
//
//        LOGGER.info("Starting the FARM program");
//
//        Farm farm = new Farm();
//        FarmServiceImpl farmCreator = new FarmServiceImpl();
//        boolean result1 = farmCreator.makeFarm(farm);
//        if (result1) {
//            LOGGER.info("Welcome to " + farm.getName() + " Farm");
//        } else {
//            LOGGER.info("Farm creation failed");
//        }


        LOGGER.info("Starting the FARM program");

        FarmServiceImpl farmCreator = new FarmServiceImpl();
        Farm farm = farmCreator.makeFarm();

        if (farm != null) {
            LOGGER.info("Welcome to " + farm.getName() + " Farm");
        } else {
            LOGGER.info("Farm creation failed");
        }


        Scanner scanner = new Scanner(System.in);
        CropDAOImpl cropDAOImpl = new CropDAOImpl();
        CropServiceImpl cropService = new CropServiceImpl();
        AnimalDAOImpl animalDAOImpl = new AnimalDAOImpl();
        AnimalServiceImpl animalService = new AnimalServiceImpl();
        int option;

        while (true) {
            System.out.println();
            LOGGER.info("Enter 1 to view/manage animals, 2 to view/manage crops, 3 to exit");
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    while (true) {
                        System.out.println();
                        LOGGER.info("Enter 1 to view all animals, 2 to create an animal, 3 to exit, 4 to update animal, 5 to delete animal.");
                        option = scanner.nextInt();
                        switch (option) {
                            case 1:
                                animalService.viewAllAnimals();
                                break;
                            case 2:
                                try {
                                    System.out.println("Enter the type of the animal you want to create:");
                                    System.out.println("1. Cow");
                                    System.out.println("2. Pig");
                                    System.out.println("3. Chicken");
                                    int animalTypeOption = scanner.nextInt();
                                    String animalType = "";
                                    switch (animalTypeOption) {
                                        case 1:
                                            animalType = "Cow";
                                            break;
                                        case 2:
                                            animalType = "Pig";
                                            break;
                                        case 3:
                                            animalType = "Chicken";
                                            break;
                                        default:
                                            LOGGER.info("Invalid option, try again.");
                                            break;
                                    }
                                    Animal animal = animalService.breedAnimal(animalType);
                                    System.out.println(animal);
                                    LOGGER.info("Animal Name: " + animal.getName());
                                    LOGGER.info("Animal Type: " + animal.getType());
                                    LOGGER.info("Animal Breed: " + animal.getBreed());
                                    boolean result = animalDAOImpl.create(animal);
                                    if (result) {
                                        LOGGER.info("Animal created successfully");
                                    } else {
                                        LOGGER.info("Animal creation failed");
                                    }
                                } catch (InputMismatchException e) {
                                    LOGGER.error("Invalid input, try again");
                                    scanner.nextLine();
                                }
                                break;
                            case 3:
                                break;
                            case 5:
                                System.out.println("Enter the ID of the animal to delete:");
                                long animalId = scanner.nextLong();
                                boolean deleted = animalService.slaughterAnimalById(animalId);
                                if (deleted) {
                                    LOGGER.info("Animal with ID " + animalId + " was successfully deleted");
                                } else {
                                    LOGGER.info("Animal with ID " + animalId + " was not found or could not be deleted");
                                }
                                break;
                            default:
                                LOGGER.info("Invalid option, try again.");
                                break;
                        }
                        if (option == 3) {
                            break;
                        }
                    }
                    break;
                case 2:
                    while (true) {
                        System.out.println();
                        LOGGER.info("Enter 1 to view all crops, 2 to grow a random crop, 3 to exit, 4 to harvest/delete crop.");
                        option = scanner.nextInt();
                        switch (option) {
                            case 1:
                                cropService.viewAllCrops();
                                break;
                            case 2:
                                Crop crop = cropService.growCrop();
                                System.out.println(crop);
                                LOGGER.info("Crop Name: " + crop.getName());
                                LOGGER.info("Crop Variety: " + crop.getVariety());
                                LOGGER.info("Crop Growth Stage: " + crop.getGrowingSeason());
                                boolean result = cropDAOImpl.create(crop);
                                if (result) {
                                    LOGGER.info("Crop created successfully");
                                } else {
                                    LOGGER.info("Crop creation failed");
                                }
                                break;
                            case 3:
                                return;
                            case 4:
                                System.out.println("Enter the ID of the crop to delete:");
                                long id = scanner.nextLong();
                                boolean deleted = cropService.harvestCropById(id);
                                if (deleted) {
                                    LOGGER.info("Crop with ID " + id + " was successfully deleted");
                                    // if deleted successfully create product
                                    // if delete = crop, make food
                                    // if delete = animal make meat
                                } else {
                                    LOGGER.info("Crop with ID " + id + " was not found or could not be deleted");
                                }
                                break;
                            default:
                                LOGGER.info("Invalid option, try again.");
                                break;
                        }
                    }
            }
        }
    }
}







//
//        Scanner scanner = new Scanner(System.in);
//        CropDAOImpl cropDAOImpl = new CropDAOImpl();
//        CropServiceImpl cropService = new CropServiceImpl();
//        int option;
//
//        AnimalDAOImpl animalDAOImpl = new AnimalDAOImpl();
//        AnimalServiceImpl animalService = new AnimalServiceImpl();
//
//        while (true) {
//            System.out.println();
//            LOGGER.info("Enter 1 to view all animals, 2 to create an animal, 3 to exit, 4 to update animal, 5 to delete animal.");
//            option = scanner.nextInt();
//            switch (option) {
//                case 1:
//                    animalService.viewAllAnimals();
//                    break;
//                case 2:
//                    System.out.println("Enter the type of the animal you want to create:");
//                    String type = scanner.nextLine();
//                    Animal animal = animalService.breedAnimal(type);
//                    System.out.println(animal);
//                    LOGGER.info("Animal Name: " + animal.getName());
//                    LOGGER.info("Animal Type: " + animal.getType());
//                    LOGGER.info("Animal Breed: " + animal.getBreed());
//                    boolean result = animalDAOImpl.create(animal);
//                    if (result) {
//                        LOGGER.info("Animal created successfully");
//                    } else {
//                        LOGGER.info("Animal creation failed");
//                    }
//                    break;
//                case 3:
//                    return;
//                case 5:
//                    System.out.println("Enter the ID of the animal to delete:");
//                    long animalId = scanner.nextLong();
//                    boolean deleted = animalService.slaughterAnimalById(animalId);
//                    if (deleted) {
//                        LOGGER.info("Animal with ID " + animalId + " was successfully deleted");
//                    } else {
//                        LOGGER.info("Animal with ID " + animalId + " was not found or could not be deleted");
//                    }
//                    break;
//                default:
//                    LOGGER.info("Invalid option, try again.");
//                    break;
//            }
//        }
//
//        while (true) {
//            System.out.println();
//            LOGGER.info("Enter 1 to view all crops, 2 to grow a random crop, 3 to exit, 4 to harvest/delete crop.");
//            option = scanner.nextInt();
//            switch (option) {
//                case 1:
//                    cropService.viewAllCrops();
//                    break;
//                case 2:
//                    Crop crop = cropService.growCrop();
//                    System.out.println(crop);
//                    LOGGER.info("Crop Name: " + crop.getName());
//                    LOGGER.info("Crop Variety: " + crop.getVariety());
//                    LOGGER.info("Crop Growth Stage: " + crop.getGrowingSeason());
//                    boolean result = cropDAOImpl.create(crop);
//                    if (result) {
//                        LOGGER.info("Crop created successfully");
//                    } else {
//                        LOGGER.info("Crop creation failed");
//                    }
//                    break;
//                case 3:
//                    return;
//                case 4:
//                    System.out.println("Enter the ID of the crop to delete:");
//                    long id = scanner.nextLong();
//                    boolean deleted = cropService.harvestCropById(id);
//                    if (deleted) {
//                        LOGGER.info("Crop with ID " + id + " was successfully deleted");
//                        // if deleted successfully create product
//                        // if delete = crop, make food
//                        // if delete = animal make meat
//                    } else {
//                        LOGGER.info("Crop with ID " + id + " was not found or could not be deleted");
//                    }
//                    break;
//                default:
//                    LOGGER.info("Invalid option, try again.");
//                    break;
//            }
//        }
//    }
//}
