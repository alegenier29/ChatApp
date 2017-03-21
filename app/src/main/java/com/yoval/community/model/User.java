package com.yoval.community.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by Alejandro on 2017-02-16.
 */
@IgnoreExtraProperties
public class User {

    public String userId; //mail??
    public String username;
    public Integer radius;
    public LatLng location;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userId, String username, Integer radius, LatLng location) {
        this.userId = userId;
        this.username = username;
        this.radius = radius;
        this.location = location;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng position) {
        this.location = position;
    }
}
