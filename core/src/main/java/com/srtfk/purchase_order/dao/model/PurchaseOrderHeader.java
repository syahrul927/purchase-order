package com.srtfk.purchase_order.dao.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "po_h")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PurchaseOrderHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "datetime")
	private LocalDateTime dateTime;

	@Column(name = "description", length = 500)
	private String description;

	@Column(name = "total_price")
	private Integer totalPrice;

	@Column(name = "total_cost")
	private Integer totalCost;

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

	@OneToMany(mappedBy = "purchaseOrderHeaderId", cascade = { CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.EAGER)
	private List<PurchaseOrderDetail> details = new ArrayList<>();

	public PurchaseOrderHeader(Integer id, LocalDateTime dateTime, String description, Integer totalPrice,
			Integer totalCost) {
		this.id = id;
		this.dateTime = dateTime;
		this.description = description;
		this.totalPrice = totalPrice;
		this.totalCost = totalCost;
	}

	public PurchaseOrderHeader(LocalDateTime dateTime, String description, Integer totalPrice, Integer totalCost) {
		this.dateTime = dateTime;
		this.description = description;
		this.totalPrice = totalPrice;
		this.totalCost = totalCost;
	}

}
