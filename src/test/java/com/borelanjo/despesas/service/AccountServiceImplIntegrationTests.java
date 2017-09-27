package com.borelanjo.despesas.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.borelanjo.despesas.domain.Account;
import com.borelanjo.despesas.domain.TransactionHistory;
import com.borelanjo.despesas.enumeration.TransactionType;
import com.borelanjo.despesas.service.AccountRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplIntegrationTests {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TransactionHistoryRepository transactionHistoryRepository;
	
	@Autowired
	AccountServiceImpl serviceImpl = new AccountServiceImpl(accountRepository, transactionHistoryRepository);
	
	@Test
	public void createAccount() {
		
		List<Account> accounts = this.accountRepository.findAll();
		Integer accountNumber = 456789 + accounts.size();

		Account account = this.serviceImpl.createAccount(accountNumber, 55.54);
		assertThat(account.getBalance()).isEqualTo(55.54);
	}
	
	@Test
	public void addTransaction() {
		
		Integer accountNumber = 123457;

		TransactionHistory transactionHistory = this.serviceImpl.addTransaction(accountNumber, TransactionType.DECREASE, 5.0);
		assertThat(transactionHistory.getDescription()).isEqualTo("Despesa: R$ 5.0");
	}
	
	@Test
	public void transfer() {
		Integer sourceAccountNumber = 456798;
		Integer destinationAccountNumber = 456799;

		TransactionHistory transactionHistory = this.serviceImpl.transfer(sourceAccountNumber, destinationAccountNumber, 5.0);
		assertThat(transactionHistory.getDescription()).isEqualTo("Despesa: Transferido R$ 5.0 para a conta 456799");
	}
	
	@Test
	public void showHistory() {
		
		Integer sourceAccountNumber = 123456;

		List<TransactionHistory> transactionHistories = this.serviceImpl.showHistory(sourceAccountNumber);
		assertThat(transactionHistories.size()).isEqualTo(10);
	}
	



}