package com.db.service;

import com.db.entity.Shop;
import com.db.utils.Result;

/**
 * Created by mmaykarkar on 13/02/17.
 */
public interface GeoCodeService {

    public Result getGeoCodeResponse(String queryParam, String queryParamValue);

    public double distanceTo(Shop shop, double customerLatitude, double customerLongitude);
}
