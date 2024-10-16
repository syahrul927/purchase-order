package com.srtfk.purchase_order.usecase;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.srtfk.purchase_order.dao.model.PurchaseOrderDetail;
import com.srtfk.purchase_order.dao.model.Item;
import com.srtfk.purchase_order.dao.model.PurchaseOrderHeader;
import com.srtfk.purchase_order.dao.model.User;
import com.srtfk.purchase_order.dao.repository.ItemRepository;
import com.srtfk.purchase_order.dao.repository.PurchaseOrderDetailRepository;
import com.srtfk.purchase_order.dao.repository.PurchaseOrderHeaderRepository;
import com.srtfk.purchase_order.dao.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseOrderUsecase {
	private final PurchaseOrderHeaderRepository purchaseOrderHeaderRepository;
	private final PurchaseOrderDetailRepository purchaseOrderDetailRepository;
	private final ItemRepository itemRepository;
	private final UserRepository userRepository;

	public List<PurchaseOrderHeader> getPurchaseOrders() {
		return purchaseOrderHeaderRepository.findAll();
	}

	public PurchaseOrderHeader getPurchaseOrder(Integer id) {
		return purchaseOrderHeaderRepository
				.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Purchase order not found"));
	}

	public PurchaseOrderDetail getPurchaseOrderDetail(Integer id) {
		return purchaseOrderDetailRepository
				.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Purchase order detail not found"));
	}

	public List<PurchaseOrderDetail> getPurchaseOrderDetailsByHeader(Integer id) {
		return purchaseOrderDetailRepository.findByHeaderId(id);
	}

	public PurchaseOrderHeader createPurchaseOrder(PurchaseOrderHeader purchaseOrderHeader,
			Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
		return purchaseOrderHeaderRepository.save(purchaseOrderHeader, user);
	}

	public List<PurchaseOrderDetail> createPurchaseOrderDetail(
			List<Pair<PurchaseOrderDetail, Integer>> purchaseOrderDetail,
			PurchaseOrderHeader purchaseOrderHeader,
			Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
		List<PurchaseOrderDetail> fixPurchaseOrderDetails = purchaseOrderDetail.stream().map(pair -> {
			Integer itemId = pair.getSecond();
			Optional<Item> item = itemRepository.findById(itemId);
			if (!item.isPresent()) {
				throw new NoSuchElementException("Item id " + itemId + " not found");
			}
			PurchaseOrderDetail detail = pair.getFirst();
			detail.setItemId(item.get());
			detail.setPurchaseOrderHeaderId(purchaseOrderHeader);
			return detail;
		}).toList();
		return purchaseOrderDetailRepository.save(fixPurchaseOrderDetails, user);
	}

	public PurchaseOrderHeader updatePurchaseOrder(PurchaseOrderHeader purchaseOrderHeader, Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
		if (!purchaseOrderHeaderRepository.existBy(purchaseOrderHeader.getId())) {
			throw new NoSuchElementException("Purchase order not found");
		}
		return purchaseOrderHeaderRepository.save(purchaseOrderHeader, user);
	}

	public PurchaseOrderDetail updatePurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail, Integer itemId,
			Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
		Item item = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchElementException("Item not found"));
		Optional<PurchaseOrderDetail> oldPurchaseOrderDetail = purchaseOrderDetailRepository
				.findById(purchaseOrderDetail.getId());
		if (!oldPurchaseOrderDetail.isPresent()) {
			throw new NoSuchElementException("Purchase order detail not found");
		}

		purchaseOrderDetail.setPurchaseOrderHeaderId(oldPurchaseOrderDetail.get().getPurchaseOrderHeaderId());
		purchaseOrderDetail.setItemId(item);
		return purchaseOrderDetailRepository.save(purchaseOrderDetail, user);

	}

	public void deletePurchaseOrder(Integer id, Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
		if (!purchaseOrderHeaderRepository.existBy(id)) {
			throw new NoSuchElementException("Purchase order not found");
		}
		purchaseOrderHeaderRepository.deleteById(id, user);
	}

	public PurchaseOrderDetail createPurchaseOrderDetail(PurchaseOrderDetail purchaseOrderDetail,
			Integer purchaseOrderHeaderId, Integer itemId,
			Integer userId) {
		PurchaseOrderHeader purchaseOrderHeader = purchaseOrderHeaderRepository.findById(purchaseOrderHeaderId)
				.orElseThrow(() -> new NoSuchElementException("Purchase order not found"));
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
		Item item = itemRepository.findById(itemId).orElseThrow(() -> new NoSuchElementException("Item not found"));
		purchaseOrderDetail.setItemId(item);
		purchaseOrderDetail.setPurchaseOrderHeaderId(purchaseOrderHeader);
		return purchaseOrderDetailRepository.save(purchaseOrderDetail, user);
	}

	public void deletePurchaseOrderDetail(Integer id, Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
		if (!purchaseOrderDetailRepository.existBy(id)) {
			throw new NoSuchElementException("Purchase order detail not found");
		}
		purchaseOrderDetailRepository.deleteById(id, user);
	}
}
