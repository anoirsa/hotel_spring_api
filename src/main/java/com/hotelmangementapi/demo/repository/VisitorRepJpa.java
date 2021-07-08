package com.hotelmangementapi.demo.repository;

import com.hotelmangementapi.demo.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VisitorRepJpa extends JpaRepository<Visitor, Long> {

}
