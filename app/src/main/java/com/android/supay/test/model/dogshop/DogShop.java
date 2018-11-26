package com.android.supay.test.model.dogshop;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class DogShop extends RealmObject {

    public String name;

    public String address;

    public String image;

    @PrimaryKey
    public String dogShopId;

    public DogShop() {
    }

    public DogShop(String name, String address, String image, String dogShopId) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.dogShopId = dogShopId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDogShopId() {
        return dogShopId;
    }

    public void setDogShopId(String dogShopId) {
        this.dogShopId = dogShopId;
    }

    @Override
    public String toString() {
        return "DogShop{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                ", dogShopId='" + dogShopId + '\'' +
                '}';
    }
}
