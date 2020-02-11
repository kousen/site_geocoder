package com.churchmutual.restclient.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Site {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;
    private double latitude;
    private double longitude;

    public Site() {}

    public Site(String address, double latitude, double longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Site(Integer id, String address, double latitude, double longitude) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Site site = (Site) o;
        return Double.compare(site.latitude, latitude) == 0 &&
                Double.compare(site.longitude, longitude) == 0 &&
                Objects.equals(id, site.id) &&
                Objects.equals(address, site.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, latitude, longitude);
    }

    @Override
    public String toString() {
        return "Site{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
