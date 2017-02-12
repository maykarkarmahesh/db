package com.db.wrappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Created by mmaykarkar on 12/02/17.
 */
@Data
public class ShopAddressWrapper {

    @JsonProperty(value = "number")
    private int number;

    @JsonProperty(value = "postCode")
    private int postCode;

}
