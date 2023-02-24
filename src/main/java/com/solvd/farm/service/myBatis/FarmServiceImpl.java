package com.solvd.farm.service.myBatis;

import com.solvd.farm.DAO.jdbcimpl.FarmDAOImpl;
import com.solvd.farm.binary.Farm;
import com.solvd.farm.service.FarmService;
import com.solvd.farm.util.MyBatisDaoFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FarmServiceImpl implements FarmService {

    private static final Logger LOGGER = LogManager.getLogger(FarmServiceImpl.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisDaoFactory.getSqlSessionFactory();

    @Override
    public Farm makeFarm() {
        Farm farm = new Farm(1, "Greenville Valley", "1 Texas Way", "585-990-5040");
        try(SqlSession sqlSession = SESSION_FACTORY.openSession()) {
            FarmDAOImpl farmDAOImpl = sqlSession.getMapper(FarmDAOImpl.class);
            farmDAOImpl.create(farm);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        return farm;
    }
}
