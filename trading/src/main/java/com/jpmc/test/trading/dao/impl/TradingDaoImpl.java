package com.jpmc.test.trading.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmc.test.trading.dao.TradingDao;
import com.jpmc.test.trading.domain.Transaction;
import com.jpmc.test.trading.exception.TradingException;
import com.jpmc.test.trading.util.TradingConstants;

public class TradingDaoImpl implements TradingDao {
	
	private List<Transaction> transactionsList = new ArrayList<>();

	@Override
	public Map<String, Double> getAgreedFxForAllCurrencies()
			throws TradingException {
		Map<String, Double> agreedFxMap = new HashMap<>();
		try {
			agreedFxMap.put(TradingConstants.CURRENCY_TYPE_AED, 0.22);
			agreedFxMap.put(TradingConstants.CURRENCY_TYPE_SGP, 0.50);
			agreedFxMap.put(TradingConstants.CURRENCY_TYPE_SAR, 0.40);
		} catch (Exception exception) {
			throw new TradingException("Exception encountered while getting AgreedFx for all currencies", exception);
		}
		return agreedFxMap;
	}

	@Override
	public void placeTransaction(Transaction transaction) {
		transactionsList.add(transaction);
	}

	@Override
	public List<Transaction> getTransactionsList() {
		return transactionsList;
	}

	@Override
	public String getTransactionSettlementDate(String entityName,
			String entityType, String currency, String instructionDate,
			int units, double pricePerUnit) throws TradingException {
		String settlementDate = null;
		try {
			for(Transaction txn : transactionsList){
				if(entityName.equalsIgnoreCase(txn.getEntity()) && 
						entityType.equalsIgnoreCase(txn.getEntityType()) &&
						currency.equalsIgnoreCase(txn.getCurrency()) && 
						instructionDate.equalsIgnoreCase(txn.getInstructionDate()) &&
						units == txn.getUnits() && 
						pricePerUnit == txn.getPricePerUnit()) {
					settlementDate = txn.getSettlementDate();
				}
			}
		} catch (Exception exception) {
			throw new TradingException("Exception encountered while getting settlement date for given transaction", exception);
		}
		return settlementDate;
	}
	
	

	
}
