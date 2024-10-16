package com.srtfk.purchase_order.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.srtfk.purchase_order.dao.model.Item;
import com.srtfk.purchase_order.dao.model.User;
import com.srtfk.purchase_order.dao.repository.ItemRepository;
import com.srtfk.purchase_order.jpa.ItemJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryAdapter implements ItemRepository {
	private final ItemJpaRepository itemJpaRepository;

	@Override
	public boolean existBy(Integer id) {
		return itemJpaRepository.existsById(id);
	}

	@Override
	public List<Item> findAll() {
		return itemJpaRepository.findAll();
	}

	@Override
	public Optional<Item> findById(Integer id) {
		return itemJpaRepository.findById(id);
	}

	@Override
	public Item save(Item item, User user) {
		item.setUpdatedBy(user);
		item.setCreatedBy(user);
		return itemJpaRepository.save(item);
	}

	@Override
	public void deleteById(Integer id, User user) {
		itemJpaRepository.deleteById(id);
	}

}
