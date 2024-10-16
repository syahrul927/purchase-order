package com.srtfk.purchase_order.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.srtfk.purchase_order.dao.model.PurchaseOrderHeader;
import com.srtfk.purchase_order.dao.model.User;
import com.srtfk.purchase_order.dao.repository.PurchaseOrderHeaderRepository;
import com.srtfk.purchase_order.jpa.PurchaseOrderHeaderJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PurchaseOrderHeaderAdapter implements PurchaseOrderHeaderRepository {

	private final PurchaseOrderHeaderJpaRepository purchaseOrderHeaderJpaRepository;

	@Override
	public boolean existBy(Integer id) {
		return purchaseOrderHeaderJpaRepository.existsById(id);
	}

	@Override
	public List<PurchaseOrderHeader> findAll() {
		return purchaseOrderHeaderJpaRepository.findAll();
	}

	@Override
	public Optional<PurchaseOrderHeader> findById(Integer id) {
		return purchaseOrderHeaderJpaRepository.findById(id);
	}

	@Override
	public PurchaseOrderHeader save(PurchaseOrderHeader purchaseOrderHeader, User user) {
		purchaseOrderHeader.setCreatedBy(user);
		purchaseOrderHeader.setUpdatedBy(user);
		return purchaseOrderHeaderJpaRepository.save(purchaseOrderHeader);
	}

	@Override
	public void deleteById(Integer id, User user) {
		purchaseOrderHeaderJpaRepository.deleteById(id);
	}

}
