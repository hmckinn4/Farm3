package com.solvd.farm.service.myBatis;

import com.solvd.farm.DAO.jdbcimpl.ProductDAOImpl;
import com.solvd.farm.binary.Product;
import com.solvd.farm.service.ProductService;
import com.solvd.farm.util.MyBatisDaoFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = LogManager.getLogger(ProductServiceImpl.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();

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
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            ProductDAOImpl productDAOImpl = sqlSession.getMapper(ProductDAOImpl.class);
            productDAOImpl.create(product);
            sqlSession.commit();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(product + " created");
        return product;
    }
}
