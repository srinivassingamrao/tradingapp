package com.jpmc.test.trading.service;

import com.jpmc.test.trading.exception.TradingException;

public interface TradingService {
	
	public boolean validateAndPlaceTransaction(String entityName, String entityType,
			String currency, String instructionDate, int units,
			double pricePerUnit) throws TradingException;
	
	public String getTransactionSettlementDate(String entityName, String entityType,
			String currency, String instructionDate, int units,
			double pricePerUnit) throws TradingException;
	
	public void getDailyTransactionDetails () throws TradingException;
}
