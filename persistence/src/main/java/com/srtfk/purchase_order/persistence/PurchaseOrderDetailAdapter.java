package com.srtfk.purchase_order.persistence;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.srtfk.purchase_order.dao.model.PurchaseOrderDetail;
import com.srtfk.purchase_order.dao.model.PurchaseOrderHeader;
import com.srtfk.purchase_order.dao.model.User;
import com.srtfk.purchase_order.dao.repository.PurchaseOrderDetailRepository;
import com.srtfk.purchase_order.jpa.PurchaseOrderDetailJpaRepository;
import com.srtfk.purchase_order.jpa.PurchaseOrderHeaderJpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PurchaseOrderDetailAdapter implements PurchaseOrderDetailRepository {

	private final PurchaseOrderDetailJpaRepository purchaseOrderDetailJpaRepository;
	private final PurchaseOrderHeaderJpaRepository purchaseOrderHeaderJpaRepository;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean existBy(Integer id) {
		return purchaseOrderDetailJpaRepository.existsById(id);
	}

	@Override
	public List<PurchaseOrderDetail> findAll() {
		return purchaseOrderDetailJpaRepository.findAll();
	}

	@Override
	public Optional<PurchaseOrderDetail> findById(Integer id) {
		return purchaseOrderDetailJpaRepository.findById(id);
	}

	@Override
	public PurchaseOrderDetail save(PurchaseOrderDetail PurchaseOrderDetail, User user) {
		PurchaseOrderDetail.setCreatedBy(user);
		PurchaseOrderDetail.setUpdatedBy(user);
		return purchaseOrderDetailJpaRepository.save(PurchaseOrderDetail);
	}

	@Transactional
	@Override
	public void deleteById(Integer id, User user) {
		try {
			PurchaseOrderDetail detail = entityManager.find(PurchaseOrderDetail.class, id);
			if (detail != null) {
				PurchaseOrderHeader parent = detail.getPurchaseOrderHeaderId();
				parent.getDetails().remove(detail);
				entityManager.remove(detail);
			}
		} catch (Exception e) {
			// Log the exception
			throw new RuntimeException("Error deleting PurchaseOrderDetail", e);
		}
		// purchaseOrderDetailJpaRepository.deleteById(id);
	}

	@Override
	public List<PurchaseOrderDetail> save(List<PurchaseOrderDetail> purchaseOrderDetail, User user) {
		purchaseOrderDetail.forEach(detail -> {
			detail.setCreatedBy(user);
			detail.setUpdatedBy(user);
		});
		return purchaseOrderDetailJpaRepository.saveAll(purchaseOrderDetail);
	}

	@Override
	public List<PurchaseOrderDetail> findByHeaderId(Integer id) {
		PurchaseOrderHeader purchaseOrderHeader = purchaseOrderHeaderJpaRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Purchase order header not found"));
		return purchaseOrderDetailJpaRepository.findByPurchaseOrderHeaderId(purchaseOrderHeader);
	}

}
