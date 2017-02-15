package com.db.service;

import com.db.wrappers.ShopWrapper;

import java.util.List;

/**
 * Created by mmaykarkar on 12/02/17.
 */
public interface ShopService {

    public void addShop(ShopWrapper shopWrapper);

    public List<ShopWrapper> getShopDetails(double latitude, double longitude);
}
