package com.nguyenanhtuyen.exception;

public class BankTransactionException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public BankTransactionException(String message) {
		super(message);
	}
}
