package com.db.endpoints;

import com.db.wrappers.ShopWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by mmaykarkar on 12/02/17.
 */

@RestController
@RequestMapping(value = "/shop")
public class ShopEndPoint {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addShop(@RequestBody ShopWrapper shopWrapper){

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
