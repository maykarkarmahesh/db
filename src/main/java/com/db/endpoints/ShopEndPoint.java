package com.db.endpoints;

import com.db.service.ShopService;
import com.db.wrappers.ShopWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * Created by mmaykarkar on 12/02/17.
 */

@RestController
@RequestMapping(value = "/shop")
public class ShopEndPoint {

    @Autowired
    private ShopService shopService;

    /**
     * Service to add shop with necessary details
     * @param shopWrapper
     * @return 201 if created successfully OR respective error message
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addShop(@RequestBody ShopWrapper shopWrapper) {
        shopService.addShop(shopWrapper);
        return new ResponseEntity(HttpStatus.CREATED);

    }

    /**
     * Service to return shop details based on Customer's lnglat
     * @param latitude
     * @param longitude
     * @return
     */
    @RequestMapping(value = "/lat/{latitude}/lng/{longitude}", method = RequestMethod.GET)
    public ResponseEntity getShopDetails(@PathVariable String latitude, @PathVariable String longitude) {
        shopService.getShopDetails(latitude, longitude);
        return null;
    }

}
