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

}
