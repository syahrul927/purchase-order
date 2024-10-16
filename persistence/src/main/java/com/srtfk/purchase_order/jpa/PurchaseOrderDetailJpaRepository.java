package com.srtfk.purchase_order.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srtfk.purchase_order.dao.model.PurchaseOrderDetail;
import com.srtfk.purchase_order.dao.model.PurchaseOrderHeader;

public interface PurchaseOrderDetailJpaRepository extends JpaRepository<PurchaseOrderDetail, Integer> {

	boolean existsById(Integer id);

	List<PurchaseOrderDetail> findByPurchaseOrderHeaderId(PurchaseOrderHeader id);
}
