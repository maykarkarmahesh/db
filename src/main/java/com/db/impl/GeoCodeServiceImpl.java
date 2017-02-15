package com.db.impl;

import com.db.entity.Shop;
import com.db.service.GeoCodeService;
import com.db.utils.Constants;
import com.db.utils.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

/**
 * Created by mmaykarkar on 13/02/17.
 */
@Service
public class GeoCodeServiceImpl implements GeoCodeService {


    @Value("${geo.code.lat.lng.api.url}")
    private String geoCodeBaseURL;

    @Value("${geo.code.api.key}")
    private String geoCodeApiKey;
    /**
     * Calls and validate GEOCODE API response
     * @param queryParam
     * @param queryParamValue
     * @return
     */
    @Override
    public Result getGeoCodeResponse(String queryParam, String queryParamValue) {
        Result result = null;
        Invocation.Builder requestBuilder = buildAndInvokeGeoCodeAPI(queryParam, queryParamValue);
        Response response = requestBuilder.get();

        if(response.getStatus() == HttpStatus.OK.value()) {
            // convert response to POJO using GSON
            Gson gson = new Gson();
            result = gson.fromJson(response.readEntity(String.class).toString(), new TypeToken<Result>() {
            }.getType());

        }
        return result;
    }

    @Override
    public double distanceTo(Shop shop, double customerLatitude, double customerLongitude) {
        double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
        double lat1 = Math.toRadians(shop.getLatitude());
        double lon1 = Math.toRadians(shop.getLongitude());

        double lat2 = Math.toRadians(customerLatitude);
        double lon2 = Math.toRadians(customerLongitude);

        // great circle distance in radians, using law of cosines formula
        double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

        // each degree on a great circle of Earth is 60 nautical miles
        double nauticalMiles = 60 * Math.toDegrees(angle);
        double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
        return statuteMiles;
    }

    /**
     * Invokes GEOCODE API with ClientBuilder
     * @param queryParam
     * @param queryParamValue
     * @return
     */
    private Invocation.Builder buildAndInvokeGeoCodeAPI(String queryParam, String queryParamValue) {
        Invocation.Builder request = ClientBuilder.newClient()
                .target(geoCodeBaseURL).
                        queryParam(queryParam,queryParamValue).
                        queryParam(Constants.KEY_QUERY_PARAM,geoCodeApiKey)
                .request(Constants.HEADER_VALUE);
        return request;

    }
}
