package com.wissen.justhire.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wissen.justhire.model.SystemAttribute;

public interface SystemAttributeRepository extends JpaRepository<SystemAttribute, Integer> {

}
