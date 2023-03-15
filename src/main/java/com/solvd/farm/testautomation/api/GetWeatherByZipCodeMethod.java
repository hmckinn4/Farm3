package com.solvd.farm.testautomation.api;

import com.qaprosoft.carina.core.foundation.api.AbstractApiMethodV2;
import com.solvd.farm.util.CryptoUtil;
import com.zebrunner.carina.utils.R;

public class GetWeatherByZipCodeMethod extends AbstractApiMethodV2 {
    public GetWeatherByZipCodeMethod(String zipCode) {
        super("api.properties".getClass());
        replaceUrlPlaceholder("zip_code", zipCode);
        replaceUrlPlaceholder("api_key", CryptoUtil.decrypt(R.CONFIG.get("openweathermap_api_key")));
    }
}
