package com.jpmc.test.trading.dao;

import java.util.List;
import java.util.Map;

import com.jpmc.test.trading.domain.Transaction;
import com.jpmc.test.trading.exception.TradingException;

public interface TradingDao {
	
	public Map<String, Double> getAgreedFxForAllCurrencies() throws TradingException;

	public void placeTransaction(Transaction transaction);
	
	public List<Transaction> getTransactionsList();
	
	public String getTransactionSettlementDate(String entityName,
			String entityType, String currency, String instructionDate,
			int units, double pricePerUnit) throws TradingException ;
	
}
