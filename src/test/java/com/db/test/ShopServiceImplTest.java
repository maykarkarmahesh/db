package com.db.test;

import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.db.exception.InvalidFieldException;
import com.db.impl.ShopServiceImpl;
import com.db.models.ShopWrapperBuilder;
import com.db.service.GeoCodeService;
import com.db.utils.Validation;
import com.db.wrappers.ShopWrapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.ValidationUtils;

/**
 * Created by mmaykarkar on 13/02/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ShopServiceImplTest {

        private ShopServiceImpl test;

        @Mock
        private GeoCodeService geoCodeService;

        @Before
        public void setUp() {
            test = new ShopServiceImpl();
        }

        @After
        public void tearDown() {
            this.test = null;
        }

        @Test(expected = InvalidFieldException.class)
        public void testAddShopWithNullShopName(){
            ShopWrapper shopWrapperBuilderWithNullValues = ShopWrapperBuilder.getShopWrapperBuilderWithNullValues();
            test.addShop(shopWrapperBuilderWithNullValues);
        }

        @Test(expected = InvalidFieldException.class)
        public void testAddShopWithNullShopNumber(){
            ShopWrapper shopWrapperBuilderWithNullValues = ShopWrapperBuilder.getShopWrapperBuilderWithNullValues();
            test.addShop(shopWrapperBuilderWithNullValues);
        }

        @Test(expected = InvalidFieldException.class)
        public void testAddShopWithInvalidShopNumber(){
            ShopWrapper shopWrapperBuilderWithIvalidPostCode = ShopWrapperBuilder.getShopWrapperBuilderWithIvalidPostCode();
            test.addShop(shopWrapperBuilderWithIvalidPostCode);
        }
    }
