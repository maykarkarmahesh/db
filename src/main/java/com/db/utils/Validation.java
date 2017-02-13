package com.db.utils;

import com.db.entity.ShopAddress;
import com.db.exception.InvalidFieldException;
import com.db.wrappers.ShopAddressWrapper;
import com.db.wrappers.ShopWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by mmaykarkar on 13/02/17.
 */
@Component
public class Validation {

    /*public void  validateShopRequest(ShopWrapper shopWrapper){

        if(StringUtils.isEmpty(shopWrapper.getShopName().trim())){
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


    public void validatelatlng(String latitude, String longitude) {
        if(StringUtils.isEmpty(latitude)){
            throw new InvalidFieldException("latitude");
        }
        if(StringUtils.isEmpty(longitude)){
            throw new InvalidFieldException("longitude");
        }
    }*/
}
