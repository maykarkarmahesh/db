package com.db.impl;

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
