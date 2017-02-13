package com.db.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by mmaykarkar on 12/02/17.
 */
@Data
public class ShopWrapper {

    @JsonProperty(value = "shopName")
    private String shopName;

    @JsonProperty(value = "shopLongitude")
    private int latitude;

    @JsonProperty(value = "shopLatitude")
    private int langitude;

    @JsonProperty(value = "shopAddress")
    private ShopAddressWrapper shopAddress;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLangitude() {
        return langitude;
    }

    public void setLangitude(int langitude) {
        this.langitude = langitude;
    }

    public ShopAddressWrapper getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(ShopAddressWrapper shopAddress) {
        this.shopAddress = shopAddress;
    }
}
