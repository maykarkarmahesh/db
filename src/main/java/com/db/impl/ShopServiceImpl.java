package com.db.impl;

import com.db.entity.Shop;
import com.db.entity.ShopAddress;
import com.db.exception.NoRecordsFoundException;
import com.db.service.ShopService;
import com.db.utils.Constants;
import com.db.utils.Result;
import com.db.utils.Results;
import com.db.utils.Validation;
import com.db.wrappers.ShopWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;


import static com.db.app.Application.SHOPS;

/**
 * Created by mmaykarkar on 12/02/17.
 */

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    private Validation validationUtil;

    @Override
    public boolean addShop(ShopWrapper shopWrapper) {

        // sanity checking validation
        validationUtil.validateShopRequest(shopWrapper);

        // Google API handling for getting longitude and latitude
        Result result = getGeoCodeResponse(shopWrapper);

        // If status is other than OK then return false so as to indicate NO records/content found
        if (!result.getStatus().equals(Constants.GEO_CODE_API_STATUS)) {
            throw  new NoRecordsFoundException();
        }

        // processing response from Google GEOCODE API
        for (Results results : result.getResults()) {
            saveShopDetails(results, shopWrapper);
            break;
        }

        return true;
    }

    private void saveShopDetails(Results results, ShopWrapper shopWrapper) {
        Shop shop = new Shop();
        shop.setLatitude(results.getGeometry().getLocation().getLat());
        shop.setLongitude(results.getGeometry().getLocation().getLng());
        shop.setName(shopWrapper.getShopName());
        ShopAddress shopAddress = new ShopAddress();
        shopAddress.setNumber(shopWrapper.getShopAddress().getNumber());
        shopAddress.setPostCode(shopWrapper.getShopAddress().getPostCode());
        shop.setShopAddress(shopAddress);
        if(!SHOPS.contains(shop))
        SHOPS.add(shop);
        System.out.println(SHOPS.size());
    }

    private Result getGeoCodeResponse(ShopWrapper shopWrapper) {
        Result result = null;
        Invocation.Builder requestBuilder = buildAndInvokeGeoCodeAPI(shopWrapper);
        Response response = requestBuilder.get();

        if(response.getStatus() == 200) {
            // convert response to POJO using GSON
             Gson gson = new Gson();
             result = gson.fromJson(response.readEntity(String.class).toString(), new TypeToken<Result>() {
            }.getType());

        }
        return result;
    }

    private Invocation.Builder buildAndInvokeGeoCodeAPI(ShopWrapper shopWrapper) {
        Invocation.Builder request = ClientBuilder.newClient()
                .target(Constants.GEOCODE_LAT_LNG_API).
                        queryParam(Constants.ADDRESS_QUERY_PARAM,shopWrapper.getShopName() + shopWrapper.getShopAddress().getPostCode()).
                        queryParam(Constants.KEY_QUERY_PARAM,Constants.GEOCODE_API_KEY)
                .request(Constants.HEADER_VALUE);
        return request;
    }
}
