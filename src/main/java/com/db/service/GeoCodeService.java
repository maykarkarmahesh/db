package com.db.service;

import com.db.utils.Constants;
import com.db.utils.Result;

/**
 * Created by mmaykarkar on 13/02/17.
 */
public interface GeoCodeService {

    public Result getGeoCodeResponse(String queryParam, String queryParamValue);
}
