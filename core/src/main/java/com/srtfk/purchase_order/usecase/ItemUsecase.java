package com.srtfk.purchase_order.usecase;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.srtfk.purchase_order.dao.model.Item;
import com.srtfk.purchase_order.dao.model.User;
import com.srtfk.purchase_order.dao.repository.ItemRepository;
import com.srtfk.purchase_order.dao.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemUsecase {
	private final ItemRepository itemRepository;
	private final UserRepository userRepository;

	public List<Item> getItems() {
		return itemRepository.findAll();
	}

	public Item getItem(Integer id) {
		return itemRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Item not found"));
	}

	public Item createNewItem(Item item, Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
		return itemRepository.save(item, user);
	}

	public Item updateItem(Item item, Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
		if (!itemRepository.existBy(item.getId())) {
			throw new NoSuchElementException("Item not found");
		}
		return itemRepository.save(item, user);
	}

	public void deleteItem(Integer id, Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("User not found"));
		if (!itemRepository.existBy(id)) {
			throw new NoSuchElementException("Item not found");
		}
		itemRepository.deleteById(id, user);
	}

}
