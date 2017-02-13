package com.db.service;

import com.db.wrappers.ShopWrapper;

/**
 * Created by mmaykarkar on 12/02/17.
 */
public interface ShopService {

    public void addShop(ShopWrapper shopWrapper);

    public void getShopDetails(String latitude, String longitude);
}
