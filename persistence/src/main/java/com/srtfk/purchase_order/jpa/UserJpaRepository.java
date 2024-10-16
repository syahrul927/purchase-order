package com.srtfk.purchase_order.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srtfk.purchase_order.dao.model.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

	boolean existsByEmailOrPhone(String email, String phone);

	boolean existsById(Integer id);

}
