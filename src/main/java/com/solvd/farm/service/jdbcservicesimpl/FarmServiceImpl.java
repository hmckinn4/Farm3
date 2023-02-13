package com.solvd.farm.service.jdbcservicesimpl;

import com.solvd.farm.binary.Farm;
import com.solvd.farm.service.FarmService;

public class FarmServiceImpl implements FarmService{

    @Override
    public Farm makeFarm() {
        Farm farm = new Farm(1, "Greenville Valley", "1 Texas Way", "585-990-5040");
        return farm;
    }
}
