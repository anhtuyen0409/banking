package com.nguyenanhtuyen.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nguyenanhtuyen.entities.BankAccount;
import com.nguyenanhtuyen.exception.BankTransactionException;
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
	
	//MANDATORY: Bat buoc phai co Transaction da duoc tao truoc do
	@Transactional(propagation = Propagation.MANDATORY)
	public void addMount(int id, double amount) throws BankTransactionException {
		BankAccount account = this.findById(id);
		if(account == null) {
			throw new BankTransactionException("Account not found " + id);
		}
		double newBalance = account.getBalance() + amount;
		if(account.getBalance() + amount < 0) {
			throw new BankTransactionException("The money in the account " + id + " is not enough");
		}
		account.setBalance(newBalance);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BankTransactionException.class)
	public void sendMoney(Integer fromAccountId, Integer toAccountId, double amount) throws BankTransactionException{
		addMount(toAccountId, amount);
		addMount(fromAccountId, -amount);
	}
}
