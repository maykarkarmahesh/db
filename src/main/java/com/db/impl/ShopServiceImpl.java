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
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.db.app.Application.SHOPS;

/**
 * Created by mmaykarkar on 12/02/17.
 */

@Service
public class ShopServiceImpl implements ShopService{

    @Autowired
    private GeoCodeService geoCodeService;

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
    public void getShopDetails(String latitude, String longitude) {

        // sanity check validation
        validatelatlng(latitude.trim(), longitude.trim());

        // latlng field value to be passed to GEOCODE API
        String latLng = new StringBuffer().append(latitude).append(Constants.COMMA_SEPARATOR).append(longitude).toString();

        // Google API handling for getting shop details based on customer's lat and lng
        Result result = geoCodeService.getGeoCodeResponse(Constants.LAT_LNG_QUERY_PARAM,latLng);

        // If status is other than OK then return false so as to indicate NO records/content found
        validateResult(result);
    }

    /**
     * Saves shop details to in memory list
     * @param results
     * @param shopWrapper
     */
    private void saveShopDetails(Results results, ShopWrapper shopWrapper) {
        Shop shop = new Shop();
        shop.setLatitude(results.getGeometry().getLocation().getLat());
        shop.setLongitude(results.getGeometry().getLocation().getLng());
        shop.setName(shopWrapper.getShopName());
        ShopAddress shopAddress = new ShopAddress();
        shopAddress.setNumber(shopWrapper.getShopAddress().getNumber());
        shopAddress.setPostCode(shopWrapper.getShopAddress().getPostCode());
        shop.setShopAddress(shopAddress);
        SHOPS.add(shop);
        System.out.println(SHOPS.size());
    }

    /**
     * Returns address value in specific format
     * @param shopWrapper
     * @return
     */
    public String getAddressValue(ShopWrapper shopWrapper) {
        StringBuffer address = new StringBuffer();
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

    /**
     * Performs sanity checking for incoming request parameters
     * @param latitude
     * @param longitude
     */
    private void validatelatlng(String latitude, String longitude) {
        if(StringUtils.isEmpty(latitude)){
            throw new InvalidFieldException("latitude");
        }
        if(StringUtils.isEmpty(longitude)){
            throw new InvalidFieldException("longitude");
        }
    }
}
