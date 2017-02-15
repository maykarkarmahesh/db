package com.db.entity;

/**
 * Created by mmaykarkar on 13/02/17.
 */
public class Shop {

    private String name;
    private Double longitude;
    private Double latitude;
    private  ShopAddress shopAddress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public ShopAddress getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(ShopAddress shopAddress) {
        this.shopAddress = shopAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shop shop = (Shop) o;

        if (name != null ? !name.equals(shop.name) : shop.name != null) return false;
        if (longitude != null ? !longitude.equals(shop.longitude) : shop.longitude != null) return false;
        if (latitude != null ? !latitude.equals(shop.latitude) : shop.latitude != null) return false;
        return shopAddress != null ? shopAddress.equals(shop.shopAddress) : shop.shopAddress == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (shopAddress != null ? shopAddress.hashCode() : 0);
        return result;
    }
}
