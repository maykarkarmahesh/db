package com.db.service;

import com.db.wrappers.ShopAddressWrapper;
import com.db.wrappers.ShopWrapper;

import java.io.IOException;

/**
 * Created by mmaykarkar on 12/02/17.
 */
public interface ShopService {

    public void addShop(ShopWrapper shopWrapper) throws IOException;
}
