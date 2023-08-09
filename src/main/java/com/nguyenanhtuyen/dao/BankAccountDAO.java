package com.nguyenanhtuyen.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nguyenanhtuyen.entities.BankAccount;
import com.nguyenanhtuyen.model.BankAccountInfo;

@Repository
public class BankAccountDAO {

	@Autowired
	private EntityManager entityManager;
	
	public BankAccountDAO() {
		
	}
	
	public BankAccount findById(int id) {
		return this.entityManager.find(BankAccount.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<BankAccountInfo> listBankAccountInfo() {
		String sql = "select new " + BankAccountInfo.class.getName() + "(e.id,e.fullName,e.balance) "
				+ " from " + BankAccount.class.getName() + " e ";
		Query query = entityManager.createQuery(sql, BankAccountInfo.class);
		return query.getResultList();
	}
}
