package com.churchmutual.restclient.services;


public class SiteNotFoundException extends RuntimeException {
    public SiteNotFoundException(Integer id) {
        super("Site not found with id " + id);
    }
}
