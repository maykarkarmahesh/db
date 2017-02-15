package com.db.impl;

import com.db.entity.Shop;
import com.db.entity.ShopAddress;
import com.db.exception.InvalidFieldException;
import com.db.exception.NoRecordsFoundException;
import com.db.service.GeoCodeService;
import com.db.service.ShopService;
import com.db.utils.Constants;
import com.db.utils.Result;
import com.db.utils.Results;
import com.db.wrappers.ShopAddressWrapper;
import com.db.wrappers.ShopWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.db.app.Application.SHOPS;

/**
 * Created by mmaykarkar on 12/02/17.
 */

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    private GeoCodeService geoCodeService;

    @Value("${min.distance}")
    private double minDistance;

    /**
     * Mockito constructor
     */
    public ShopServiceImpl() {
    }

    /**
     * Business layer implementation for adding shop
     * @param shopWrapper
     */
    @Override
    public void addShop(ShopWrapper shopWrapper) {

        // sanity check validation
        validateShopRequest(shopWrapper);

        // address field value to be passed to GEOCODE API
        String addressValue = getAddressValue(shopWrapper);

        // Google API handling for getting longitude and latitude
        Result result = geoCodeService.getGeoCodeResponse(Constants.ADDRESS_QUERY_PARAM,addressValue);

        // If status is other than OK then return false so as to indicate NO records/content found
        validateResult(result);

        // processing response from Google GEOCODE API
        for (Results results : result.getResults()) {
            saveShopDetails(results, shopWrapper);
            break;
        }

    }

    /**
     * Business layer implementation for getting shop details
     * @param latitude
     * @param longitude
     */
    @Override
    public List<ShopWrapper> getShopDetails(double latitude, double longitude) {

        List<ShopWrapper> shopWrapperList = new ArrayList<ShopWrapper>();
        for (Shop shop : SHOPS) {
            double distance = distanceTo(shop, latitude, longitude);
            if (distance <= minDistance)
            {
                ShopWrapper shopWrapper = wrapEntity(shop);
                shopWrapperList.add(shopWrapper);
            }
        }

        return shopWrapperList;

    }

    /**
     * Saves shop details to in memory list
     * @param results
     * @param shopWrapper
     */
    private void saveShopDetails(Results results, ShopWrapper shopWrapper) {
        Shop shop = new Shop();
        shop.setLatitude(Double.parseDouble(results.getGeometry().getLocation().getLat()));
        shop.setLongitude(Double.parseDouble(results.getGeometry().getLocation().getLng()));
        shop.setName(shopWrapper.getShopName());
        ShopAddress shopAddress = new ShopAddress();
        shopAddress.setNumber(shopWrapper.getShopAddress().getNumber());
        shopAddress.setPostCode(shopWrapper.getShopAddress().getPostCode());
        shop.setShopAddress(shopAddress);

        if(!SHOPS.contains(shop)){
            SHOPS.add(shop);
        }

    }

    /**
     * Returns address value in specific format
     * @param shopWrapper
     * @return
     */
    public String getAddressValue(ShopWrapper shopWrapper) {
        StringBuilder address = new StringBuilder();
        return  address.append(shopWrapper.getShopAddress().getNumber().trim())
                       .append(shopWrapper.getShopName().trim())
                       .append(shopWrapper.getShopAddress().getPostCode()).toString();
    }

    /**
     * Validate with GEOCODE status
     * @param result
     */
    private void validateResult(Result result) {
        if (!result.getStatus().equals(Constants.GEO_CODE_API_STATUS)) {
            throw  new NoRecordsFoundException();
        }
    }

    /**
     * Performs sanity checking for incoming request parameters
     * @param shopWrapper
     */
    private void  validateShopRequest(ShopWrapper shopWrapper){

        if(StringUtils.isEmpty(shopWrapper.getShopName())){
            throw new InvalidFieldException("shopName");
        }

        ShopAddressWrapper shopAddress = shopWrapper.getShopAddress();
        if(Objects.isNull(shopAddress)){
            throw new InvalidFieldException("shopAddress");
        }

        if(StringUtils.isEmpty(shopAddress.getNumber())){
            throw new InvalidFieldException("number");
        }

        if(shopAddress.getPostCode() == null || shopAddress.getPostCode() <= 0){
            throw new InvalidFieldException("postCode");
        }

    }

    // Compute the distance in meters
    public double distanceTo(Shop shop, double customerLatitude, double customerLongitude)
    {

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



    public ShopWrapper wrapEntity(Shop shop){
        ShopWrapper shopWrapper = new ShopWrapper();
        shopWrapper.setShopName(shop.getName());
        shopWrapper.setLatitude(shop.getLatitude());
        shopWrapper.setLangitude(shop.getLongitude());
        ShopAddressWrapper shopAddressWrapper = new ShopAddressWrapper();
        shopAddressWrapper.setNumber(shop.getShopAddress().getNumber());
        shopAddressWrapper.setPostCode(shop.getShopAddress().getPostCode());
        shopWrapper.setShopAddress(shopAddressWrapper);
        return shopWrapper;
    }

}
