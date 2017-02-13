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
    private String latitude;

    @JsonProperty(value = "shopLatitude")
    private String langitude;

    @JsonProperty(value = "shopAddress")
    private ShopAddressWrapper shopAddress;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLangitude() {
        return langitude;
    }

    public void setLangitude(String langitude) {
        this.langitude = langitude;
    }

    public ShopAddressWrapper getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(ShopAddressWrapper shopAddress) {
        this.shopAddress = shopAddress;
    }
}
