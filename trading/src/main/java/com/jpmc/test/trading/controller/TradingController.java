package com.jpmc.test.trading.controller;

import com.jpmc.test.trading.exception.TradingException;
import com.jpmc.test.trading.service.TradingService;
import com.jpmc.test.trading.service.impl.TradingServiceImpl;

public class TradingController {
	
	private TradingService tradingService;

	/**
	 * 
	 * @param entityName
	 * @param entityType
	 * @param currency
	 * @param instructionDate
	 * @param units
	 * @param pricePerUnit
	 * @return
	 * @throws TradingException
	 */
	public boolean placeTransaction(String entityName, String entityType,
			String currency, String instructionDate, int units,
			double pricePerUnit) throws TradingException {
		return getTradingService().validateAndPlaceTransaction(entityName, entityType, currency, instructionDate, units, pricePerUnit);
	}
	
	/**
	 * 
	 * @param entityName
	 * @param entityType
	 * @param currency
	 * @param instructionDate
	 * @param units
	 * @param pricePerUnit
	 * @return
	 * @throws TradingException
	 */
	public String getTransactionSettlementDate(String entityName, String entityType,
			String currency, String instructionDate, int units,
			double pricePerUnit) throws TradingException {
		return getTradingService().getTransactionSettlementDate(entityName, entityType, currency, instructionDate, units, pricePerUnit);
	}

	/**
	 * 
	 * @throws TradingException
	 */
	public void getDailyTransactionDetails () throws TradingException {
		getTradingService().getDailyTransactionDetails();
	}
	
	/**
	 * @return the tradingService
	 */
	public TradingService getTradingService() {
		if(tradingService == null) {
			tradingService = new TradingServiceImpl();
		}
		return tradingService;
	}

	
}
