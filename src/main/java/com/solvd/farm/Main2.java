package com.solvd.farm;
// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

import com.solvd.farm.binary.Crop;
import com.solvd.farm.service.CropService;
import com.solvd.farm.service.jdbcservicesimpl.CropServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main2 {

    private static final Logger logger = LogManager.getLogger(Main2.class);
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            CropService cropService = new CropServiceImpl();

            System.out.println("Enter crop name:");
            String name = scanner.nextLine();

            System.out.println("Enter crop type:");
            String type = scanner.nextLine();

            System.out.println("Enter crop growing season:");
            String growingSeason = scanner.nextLine();

            Crop crop = new Crop(1,name,type,growingSeason);

            Crop result = crop;
            logger.info(result);
        }
    }



