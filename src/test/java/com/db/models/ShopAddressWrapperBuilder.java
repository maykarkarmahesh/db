package com.db.models;

import com.db.wrappers.ShopAddressWrapper;

/**
 * Created by mmaykarkar on 13/02/17.
 */
public class ShopAddressWrapperBuilder {

    public static ShopAddressWrapper getShopAddressBuilder(){
        ShopAddressWrapper shopAddressWrapper = new ShopAddressWrapper();
        shopAddressWrapper.setNumber("11B");
        shopAddressWrapper.setPostCode(411014);
        return  shopAddressWrapper;
    }

    public static ShopAddressWrapper getShopAddressBuilderWithNullNumber(){
        ShopAddressWrapper shopAddressWrapper = new ShopAddressWrapper();
        shopAddressWrapper.setNumber(" ");
        shopAddressWrapper.setPostCode(411014);
        return  shopAddressWrapper;
    }

    public static ShopAddressWrapper getShopAddressBuilderWithInvalidPostCode(){
        ShopAddressWrapper shopAddressWrapper = new ShopAddressWrapper();
        shopAddressWrapper.setNumber("32A");
        shopAddressWrapper.setPostCode(-3);
        return  shopAddressWrapper;
    }

}
