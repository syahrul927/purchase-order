package com.srtfk.purchase_order.dao.repository;

import java.util.List;
import java.util.Optional;

import com.srtfk.purchase_order.dao.model.PurchaseOrderHeader;
import com.srtfk.purchase_order.dao.model.User;

public interface PurchaseOrderHeaderRepository {

	boolean existBy(Integer id);

	List<PurchaseOrderHeader> findAll();

	Optional<PurchaseOrderHeader> findById(Integer id);

	PurchaseOrderHeader save(PurchaseOrderHeader purchaseOrderHeader, User user);

	void deleteById(Integer id, User user);
}
