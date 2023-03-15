package com.solvd.farm.testautomation.api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.solvd.farm.util.CryptoUtil;
import com.zebrunner.carina.utils.R;

public class GetWeatherByCityMethod extends AbstractApiMethodV2 {
    public GetWeatherByCityMethod(String cityName) {
        super("api.properties".getClass());
        replaceUrlPlaceholder("city_name", cityName);
        replaceUrlPlaceholder("api_key", CryptoUtil.decrypt(R.CONFIG.get("openweathermap_api_key")));
    }
}
