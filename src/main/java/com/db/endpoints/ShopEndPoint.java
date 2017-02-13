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

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addShop(@RequestBody ShopWrapper shopWrapper){
        if(shopService.addShop(shopWrapper)) {
            return new ResponseEntity(HttpStatus.CREATED);
        }else{
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

}
