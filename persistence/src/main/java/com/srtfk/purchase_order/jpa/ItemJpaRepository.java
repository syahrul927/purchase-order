package com.srtfk.purchase_order.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srtfk.purchase_order.dao.model.Item;

public interface ItemJpaRepository extends JpaRepository<Item, Integer> {
	boolean existsById(Integer id);
}
