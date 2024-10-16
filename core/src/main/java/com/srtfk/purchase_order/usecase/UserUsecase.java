package com.srtfk.purchase_order.usecase;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.srtfk.purchase_order.dao.model.User;
import com.srtfk.purchase_order.dao.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserUsecase {

	private final UserRepository userRepository;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUser(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User not found"));
	}

	public User createNewUser(User user) {
		// jika ingin ditambahkan validasi

		// if (userRepository.existBy(user.getEmail(), user.getPhone())) {
		// throw new IllegalArgumentException("Email or phone already exist");
		// }
		return userRepository.save(user);
	}

	public User updateUser(User user) {
		if (!userRepository.existBy(user.getId())) {
			throw new NoSuchElementException("User not found");
		}
		return userRepository.save(user);
	}

	public void deleteUser(Integer id) {
		if (!userRepository.existBy(id)) {
			throw new NoSuchElementException("User not found");
		}
		userRepository.deleteById(id);
	}
}
