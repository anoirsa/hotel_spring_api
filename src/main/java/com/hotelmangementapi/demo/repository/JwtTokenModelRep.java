package com.hotelmangementapi.demo.repository;

import com.hotelmangementapi.demo.security.jwt.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface JwtTokenModelRep extends CrudRepository<JwtToken, Long> {

    boolean existsJwtTokenModelByRefreshTokenId(String refreshTokenId);







}
