package com.nguyenanhtuyen.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nguyenanhtuyen.dao.BankAccountDAO;
import com.nguyenanhtuyen.model.BankAccountInfo;

@Controller
public class MainController {
	
	@Autowired
	private BankAccountDAO bankAccountDAO;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showBankAccounts(Model model) {
		List<BankAccountInfo> list = bankAccountDAO.listBankAccountInfo();
		model.addAttribute("accountInfos", list);
		return "accountsPage";
	}
}
