package com.srtfk.purchase_order.dao.repository;

import java.util.List;
import java.util.Optional;

import com.srtfk.purchase_order.dao.model.User;

public interface UserRepository {

	boolean existBy(String email, String phone);

	boolean existBy(Integer id);

	List<User> findAll();

	Optional<User> findById(Integer id);

	User save(User user);

	void deleteById(Integer id);

}
