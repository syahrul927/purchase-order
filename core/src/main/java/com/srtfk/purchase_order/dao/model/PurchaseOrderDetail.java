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
@Table(name = "po_d")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class PurchaseOrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "poh_id")
	private PurchaseOrderHeader purchaseOrderHeaderId;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item itemId;

	@Column(name = "item_qty")
	private Integer itemQty;

	@Column(name = "item_cost")
	private Integer itemCost;

	@Column(name = "item_price")
	private Integer itemPrice;

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

	public PurchaseOrderDetail(Integer id, Item itemId, Integer itemQty, Integer itemCost, Integer itemPrice) {
		this.id = id;
		this.itemId = itemId;
		this.itemQty = itemQty;
		this.itemCost = itemCost;
		this.itemPrice = itemPrice;
	}

	public PurchaseOrderDetail(Integer id, Integer itemQty, Integer itemCost, Integer itemPrice) {
		this.id = id;
		this.itemQty = itemQty;
		this.itemCost = itemCost;
		this.itemPrice = itemPrice;
	}

	public PurchaseOrderDetail(Integer itemQty, Integer itemCost, Integer itemPrice) {
		this.itemQty = itemQty;
		this.itemCost = itemCost;
		this.itemPrice = itemPrice;
	}

}
