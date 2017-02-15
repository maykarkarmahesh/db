package com.db.wrappers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by mmaykarkar on 12/02/17.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopWrapper {

    @JsonProperty(value = "shopName")
    private String shopName;

    @JsonProperty(value = "shopLongitude")
    private Double latitude;

    @JsonProperty(value = "shopLatitude")
    private Double longitude;

    @JsonProperty(value = "shopAddress")
    private ShopAddressWrapper shopAddress;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public ShopAddressWrapper getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(ShopAddressWrapper shopAddress) {
        this.shopAddress = shopAddress;
    }


}
