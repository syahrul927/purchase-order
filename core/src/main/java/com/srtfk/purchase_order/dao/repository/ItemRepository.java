package com.srtfk.purchase_order.dao.repository;

import java.util.List;
import java.util.Optional;

import com.srtfk.purchase_order.dao.model.Item;
import com.srtfk.purchase_order.dao.model.User;

public interface ItemRepository {

	boolean existBy(Integer id);

	List<Item> findAll();

	Optional<Item> findById(Integer id);

	Item save(Item item, User user);

	void deleteById(Integer id, User user);
}
