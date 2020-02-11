package com.churchmutual.restclient.dao;

import com.churchmutual.restclient.entities.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site,Integer> {
}
