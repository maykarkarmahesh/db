package com.db.impl;

import com.db.exception.InvalidFieldException;
import com.db.exception.InvalidFieldExceptionHandler;
import com.db.service.ShopService;
import com.db.utils.Constants;
import com.db.utils.Result;
import com.db.utils.Results;
import com.db.wrappers.ShopWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mmaykarkar on 12/02/17.
 */

@Service
public class ShopServiceImpl implements ShopService{

    @Override
    public void addShop(ShopWrapper shopWrapper) throws IOException {

        // TO DO : Exception handling for input request

        String geoCodeURL = Constants.GEOCODE_LAT_LNG_API.concat(shopWrapper.getShopName() + shopWrapper.getShopAddress().getPostCode())+"&key="+Constants.GEOCODE_API_KEY;

        System.out.println("FINAL URL : "+ geoCodeURL);

        URL url = new URL(geoCodeURL);
        HttpURLConnection connection =
                (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(Constants.METHOD_TYPE);
        connection.setRequestProperty(Constants.HEADER_KEY, Constants.HEADER_VALUE);

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            Gson gson = new Gson();
            Result result = gson.fromJson(response.toString(), new TypeToken<Result>(){}.getType());

            System.out.println("SIZE : "+ result.getResults().length);



    }
}}
