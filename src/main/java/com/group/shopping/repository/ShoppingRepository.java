package com.group.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group.shopping.domain.ToBuyObject;

@Repository
public interface ShoppingRepository extends JpaRepository<ToBuyObject, Long> {

}
