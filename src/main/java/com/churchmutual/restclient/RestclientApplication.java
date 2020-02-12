package com.churchmutual.restclient;

import com.churchmutual.restclient.dao.SiteRepository;
import com.churchmutual.restclient.entities.Site;
import com.churchmutual.restclient.services.GeocoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@SpringBootApplication
public class RestclientApplication implements CommandLineRunner {
    @Autowired
    private GeocoderService service;

    public static void main(String[] args) {
        SpringApplication.run(RestclientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        service.addSite("Merrill", "WI");
        service.addSite("Green Bay", "WI");
        service.addSite("Wausau", "WI");
    }
}
