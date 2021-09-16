package com.churchmutual.restclient.services;

import com.churchmutual.restclient.dao.SiteRepository;
import com.churchmutual.restclient.entities.Site;
import com.churchmutual.restclient.json.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GeocoderService {
    private static final String KEY = "AIzaSyDw_d6dfxDEI7MAvqfGXEIsEMwjC1PWRno";

    private final WebClient client;

    private final SiteRepository repository;

    @Autowired
    public GeocoderService(WebClient.Builder builder, SiteRepository repository) {
        this.repository = repository;
        client = builder.baseUrl("https://maps.googleapis.com").build();
    }

    public List<Site> getAll() {
        return repository.findAll();
    }

    public Site addSite(String... address) {
        Site site = getLatLng(address);
        return repository.save(site);
    }

    public Optional<Site> findById(Integer id) {
        return repository.findById(id);//.orElseThrow(() -> new SiteNotFoundException(id));
    }

    public Site getLatLng(String... address) {
        String encoded = Arrays.stream(address)
                .map(component -> URLEncoder.encode(component, StandardCharsets.UTF_8))
                .collect(Collectors.joining(","));
        Response response = client.get()
                .uri(uriBuilder ->
                        uriBuilder.path("/maps/api/geocode/json")
                                .queryParam("address", encoded)
                                .queryParam("key", KEY)
                                .build())
                .retrieve()
                .bodyToMono(Response.class)
                .block(Duration.ofSeconds(2));
        return new Site(response.getFormattedAddress(),
                response.getLocation().getLat(),
                response.getLocation().getLng());
    }
}
