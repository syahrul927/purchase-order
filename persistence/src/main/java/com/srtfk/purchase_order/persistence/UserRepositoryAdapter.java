package com.srtfk.purchase_order.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.srtfk.purchase_order.dao.model.User;
import com.srtfk.purchase_order.dao.repository.UserRepository;
import com.srtfk.purchase_order.jpa.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

	private final UserJpaRepository userJpaRepository;

	@Override
	public List<User> findAll() {
		return userJpaRepository.findAll();
	}

	@Override
	public boolean existBy(String email, String phone) {
		return userJpaRepository.existsByEmailOrPhone(email, phone);
	}

	@Override
	public User save(User user) {
		user.setCreatedBy("DEFAULT_USER");
		user.setUpdatedBy("DEFAULT_USER");
		return userJpaRepository.save(user);
	}

	@Override
	public boolean existBy(Integer id) {
		return userJpaRepository.existsById(id);
	}

	@Override
	public void deleteById(Integer id) {
		userJpaRepository.deleteById(id);
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userJpaRepository.findById(id);
	}

}
