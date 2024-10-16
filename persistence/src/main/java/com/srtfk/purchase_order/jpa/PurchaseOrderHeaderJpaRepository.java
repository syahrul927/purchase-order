package com.srtfk.purchase_order.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srtfk.purchase_order.dao.model.PurchaseOrderHeader;

public interface PurchaseOrderHeaderJpaRepository extends JpaRepository<PurchaseOrderHeader, Integer> {

	boolean existsById(Integer id);
}
