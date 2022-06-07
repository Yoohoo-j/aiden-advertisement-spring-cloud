package com.aiden.ad.dao;

import com.aiden.ad.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    AdUser findByUsername(String username);

}
