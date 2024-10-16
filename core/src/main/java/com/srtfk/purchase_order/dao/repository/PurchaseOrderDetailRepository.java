package com.srtfk.purchase_order.dao.repository;

import java.util.List;
import java.util.Optional;

import com.srtfk.purchase_order.dao.model.PurchaseOrderDetail;
import com.srtfk.purchase_order.dao.model.User;

public interface PurchaseOrderDetailRepository {

	boolean existBy(Integer id);

	List<PurchaseOrderDetail> findAll();

	List<PurchaseOrderDetail> findByHeaderId(Integer id);

	Optional<PurchaseOrderDetail> findById(Integer id);

	PurchaseOrderDetail save(PurchaseOrderDetail purchaseOrderDetail, User user);

	List<PurchaseOrderDetail> save(List<PurchaseOrderDetail> purchaseOrderDetail, User user);

	void deleteById(Integer id, User user);
}
