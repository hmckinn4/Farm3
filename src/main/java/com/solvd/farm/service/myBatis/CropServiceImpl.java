package com.solvd.farm.service.myBatis;

import com.solvd.farm.binary.Crop;
import com.solvd.farm.DAO.CropDAO;
import com.solvd.farm.service.CropService;
import com.solvd.farm.util.MyBatisDaoFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Random;

public class CropServiceImpl implements CropService {
    private static final Logger LOGGER = LogManager.getLogger(CropServiceImpl.class);
    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();

    @Override
    public Crop growCrop() {
        String[] names = {"Corn", "Wheat", "Rice", "Barley", "Soybeans", "Pumpkin", "Oats"};
        String[] types = {"Grain", "Oilseed", "Pulse"};
        String[] growthStages = {"Spring", "Summer", "Fall", "Winter"};

        Random random = new Random();
        int randomIndex = random.nextInt(names.length);
        String randomName = names[randomIndex];

        randomIndex = random.nextInt(types.length);
        String randomType = types[randomIndex];

        randomIndex = random.nextInt(growthStages.length);
        String randomGrowthStage = growthStages[randomIndex];

        Crop crop = new Crop(1, randomName, randomType, randomGrowthStage);
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            CropDAO cropDAO = sqlSession.getMapper(CropDAO.class);
            cropDAO.create(crop);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        LOGGER.info("Crop created");
        return crop;
    }

    @Override
    public void viewAllCrops() {
        try (SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            CropDAO cropDAO = sqlSession.getMapper(CropDAO.class);
            List<Crop> crops = cropDAO.getAll();
            for (Crop crop : crops) {
                LOGGER.info("Crop ID: " + crop.getId());
                LOGGER.info("Crop Name: " + crop.getName());
                LOGGER.info("Crop Variety: " + crop.getVariety());
                LOGGER.info("Crop Growing Season: " + crop.getGrowingSeason());
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean harvestCropById(long id) {
        boolean deleted = false;
        try(SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            CropDAO cropDAO = sqlSession.getMapper(CropDAO.class);
            Crop crop = cropDAO.getById(id);
            if (crop != null) {
                deleted = cropDAO.delete(crop);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return deleted;
    }
}
