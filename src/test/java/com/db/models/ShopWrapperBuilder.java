package com.db.models;

import com.db.wrappers.ShopAddressWrapper;
import com.db.wrappers.ShopWrapper;

/**
 * Created by mmaykarkar on 13/02/17.
 */
public class ShopWrapperBuilder {

    public  static ShopWrapper getShopWrapperBuilder(){
        ShopWrapper shopWrapper = new ShopWrapper();
        shopWrapper.setShopName("test");
        shopWrapper.setLangitude("34.23222");
        shopWrapper.setLatitude("54.32232");
        ShopAddressWrapper shopAddressBuilder = ShopAddressWrapperBuilder.getShopAddressBuilder();
        shopWrapper.setShopAddress(shopAddressBuilder);
        return shopWrapper;
    }

    public static ShopWrapper getShopWrapperBuilderWithNullValues(){
        ShopWrapper shopWrapper = new ShopWrapper();
        shopWrapper.setShopName("test");
        ShopAddressWrapper shopAddressBuilderWithNullNumber = ShopAddressWrapperBuilder.getShopAddressBuilderWithNullNumber();
        shopWrapper.setShopAddress(shopAddressBuilderWithNullNumber);
        return  shopWrapper;
    }

    public static ShopWrapper getShopWrapperBuilderWithIvalidPostCode(){
        ShopWrapper shopWrapper = new ShopWrapper();
        shopWrapper.setShopName("test");
        ShopAddressWrapper shopAddressBuilderWithInvalidPostCode = ShopAddressWrapperBuilder.getShopAddressBuilderWithInvalidPostCode();
        shopWrapper.setShopAddress(shopAddressBuilderWithInvalidPostCode);
        return  shopWrapper;
    }
}
