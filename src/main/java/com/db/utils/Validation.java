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

    public void  validateShopRequest(ShopWrapper shopWrapper){

        if(StringUtils.isEmpty(shopWrapper.getShopName())){
            throw new InvalidFieldException("shopName");
        }

        ShopAddressWrapper shopAddress = shopWrapper.getShopAddress();
        if(Objects.isNull(shopAddress)){
            throw new InvalidFieldException("shopAddress");
        }

    }
}
