package com.solvd.farm;
// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

import com.solvd.farm.DAO.jdbcimpl.CropDAOImpl;
import com.solvd.farm.DAO.jdbcimpl.FarmDAOImpl;
import com.solvd.farm.binary.Crop;
import com.solvd.farm.binary.Farm;
import com.solvd.farm.service.jdbcservicesimpl.CropServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class Main2 {

    private static final Logger LOGGER = LogManager.getLogger(Main2.class);

    public static void main(String[] args) {

        LOGGER.info("Starting the FARM program");

        FarmDAOImpl farmDAOImpl = new FarmDAOImpl();
        Farm farm = new Farm(1, "Greenville Valley", "1 Texas Way", "585-990-5040");

        boolean result1 = farmDAOImpl.create(farm);
        if (result1) {
            LOGGER.info("Welcome to " + farm.getName() + " Farm");
        } else {
            LOGGER.info("Farm creation failed");
        }

        Scanner scanner = new Scanner(System.in);
        CropDAOImpl cropDAOImpl = new CropDAOImpl();
        while (true) {
            System.out.println();
            LOGGER.info("Enter 1 to view all crops, 2 to grow a random crop, or 3 to exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    List<Crop> crops = cropDAOImpl.getAll();
                    for (Crop crop : crops) {
                        LOGGER.info("Crop ID: " + crop.getId());
                        LOGGER.info("Crop Name: " + crop.getName());
                        LOGGER.info("Crop Variety: " + crop.getVariety());
                        LOGGER.info("Crop Growing Season: " + crop.getGrowingSeason());
                    }
                    break;
                case 2:
                    CropServiceImpl cropService = new CropServiceImpl();
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
                    break;
                default:
                    LOGGER.info("Invalid option, try again.");
                    break;
            }

            if (option == 3) {

                boolean result2 = farmDAOImpl.delete(farm);
                if (result2) {
                    LOGGER.info("Farm deleted, Exiting the FARM program");
                } else {
                    LOGGER.info("Farm delete failed");
                }
                break;
            }
        }
    }
}




