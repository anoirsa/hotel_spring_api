package com.hotelmangementapi.demo.repository;

import com.hotelmangementapi.demo.model.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VisitorRepJpa extends JpaRepository<Visitor, Long> {

    Optional<Visitor> findVisitorByFirstNameAndLastName(String firstName , String lastName);

    boolean existsVisitorByFirstNameAndLastName(String firstName , String lastName);

}
