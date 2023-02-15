package com.solvd.farm.service.jdbcservicesimpl;

import com.solvd.farm.DAO.ProductDAO;
import com.solvd.farm.DAO.jdbcimpl.ProductDAOImpl;
import com.solvd.farm.binary.Product;
import com.solvd.farm.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);
    @Override
    public Product createProduct(String animalType) {
        Product product = null;
        if (animalType.equals("Cow")) {
            product = new Product(1, "Steak", "Cow", 50);
        } else if (animalType.equals("Pig")) {
            product = new Product(1, "Pork", "Pig", 40);
        } else if (animalType.equals("Chicken")) {
            product = new Product(1, "Breast", "Chicken", 30);
        }

        ProductDAO productDAO = new ProductDAOImpl();
        productDAO.create(product);
        logger.info(product + " created");
        return product;
    }
}

