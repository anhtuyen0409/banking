package com.nguyenanhtuyen.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bank_account")
public class BankAccount {
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private Integer id;
	
	@Column(name = "full_name", length = 255, nullable = false)
	private String fullName;
	
	@Column(name = "balance", nullable = false)
	private double balance;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
