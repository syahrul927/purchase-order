package com.srtfk.purchase_order.dao.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Item")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name", length = 500)
	private String name;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "price")
	private Integer price;

	@Column(name = "cost")
	private Integer cost;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	@ManyToOne
	@JoinColumn(name = "updated_by")
	private User updatedBy;

	@CreatedDate
	@Column(name = "created_datetime", updatable = false)
	private LocalDateTime createdAt;

	@LastModifiedDate
	@Column(name = "updated_datetime")
	private LocalDateTime updatedAt;

	public Item(String name, String description, Integer price, Integer cost) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.cost = cost;
	}

	public Item(Integer id, String name, String description, Integer price, Integer cost) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.cost = cost;
	}

}
