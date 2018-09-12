package com.jpmc.test.trading.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jpmc.test.trading.dao.TradingDao;
import com.jpmc.test.trading.dao.impl.TradingDaoImpl;
import com.jpmc.test.trading.domain.Transaction;
import com.jpmc.test.trading.exception.TradingException;
import com.jpmc.test.trading.service.TradingService;
import com.jpmc.test.trading.util.TradingComparator;
import com.jpmc.test.trading.util.TradingConstants;
import com.jpmc.test.trading.util.TradingUtil;

public class TradingServiceImpl implements TradingService {
	
	private TradingDao tradingDao;

	public TradingServiceImpl() {
		super();
	}
	
	@Override
	public boolean validateAndPlaceTransaction(String entityName,
			String entityType, String currency, String instructionDate,
			int units, double pricePerUnit) throws TradingException {
		boolean flag = true;
		try {
			
			validateTransactionDetails(entityType, currency, instructionDate);
			Transaction transaction = new Transaction(entityName, entityType, currency, instructionDate, units, pricePerUnit);
			transaction.setSettlementDate(TradingUtil.getSettlementDate(currency, instructionDate));
			double agreedFx = getTradingDao().getAgreedFxForAllCurrencies().get(currency);
			transaction.setTotalAmount(pricePerUnit * units * agreedFx);
			getTradingDao().placeTransaction(transaction);
			
		} catch (TradingException te) {
			flag = false;
			throw te;
		} catch (Exception exception){
			flag = false;
			throw new TradingException("Exception encountered while placing transaction", exception);
		}
		return flag;
	}
	
	@Override
	public String getTransactionSettlementDate(String entityName,
			String entityType, String currency, String instructionDate,
			int units, double pricePerUnit) throws TradingException {
		String settlementDate = null;
		try {
			settlementDate = getTradingDao().getTransactionSettlementDate(entityName, entityType, currency, instructionDate, units, pricePerUnit);
			if(settlementDate == null) {
				throw new TradingException("Given transaction does not exists");
			}
		} catch (TradingException te) {
			throw te;
		}
		System.out.println(settlementDate);
		return settlementDate;
	}

	@Override
	public void getDailyTransactionDetails() throws TradingException {
		Map<String, List<Transaction>> dailyTxnMap = new HashMap<>();
		
		try {
			List<Transaction> transactionList = getTradingDao().getTransactionsList();
			for (Transaction txn : transactionList) {
				if (dailyTxnMap.get(txn.getSettlementDate()) == null) {
					List<Transaction> txnList = new ArrayList<>();
					txnList.add(txn);
					dailyTxnMap.put(txn.getSettlementDate(), txnList);
				} else {
					List<Transaction> txnList = dailyTxnMap.get(txn.getSettlementDate());
					txnList.add(txn);
					dailyTxnMap.put(txn.getSettlementDate(), txnList);
				}
			}
			
			for(Map.Entry<String, List<Transaction>> entry : dailyTxnMap.entrySet()) {
				List<Transaction> txnList = entry.getValue();
				List<Transaction> incomingTxn = new ArrayList<>();
				List<Transaction> outgoingTxn = new ArrayList<>();
				for (Transaction txn : txnList) {
					if(txn.getEntityType().equalsIgnoreCase(TradingConstants.ENTITY_TYPE_SELL)) {
						incomingTxn.add(txn);
					} else {
						outgoingTxn.add(txn);
					}
				}
				System.out.println(System.lineSeparator());
				System.out.println("Date : " + entry.getKey());
				if(!incomingTxn.isEmpty()) {
					Collections.sort(incomingTxn, new TradingComparator());
					System.out.println("Incoming Amount : " + TradingUtil.getDailyAmount(incomingTxn));
					System.out.println("Incoming Top Ranking : " + incomingTxn.get(0).getEntity());
				}
				if(!outgoingTxn.isEmpty()){
					Collections.sort(outgoingTxn, new TradingComparator());
					System.out.println("Outgoing Amount : " + TradingUtil.getDailyAmount(outgoingTxn));
					System.out.println("Outgoing Top Ranking : " + outgoingTxn.get(0).getEntity());
				}
				
			}
		} catch (TradingException te) {
			throw te;
		} catch (Exception exception) {
			throw new TradingException("Exception encountered while getting transaction details", exception);
		}
	}

	public boolean validateTransactionDetails (String entityType,
			String currency, String instructionDate) throws TradingException {
		boolean flag = true;
		try {
			if(entityType != null){
				if(entityType.equalsIgnoreCase(TradingConstants.ENTITY_TYPE_BUY) || 
						entityType.equalsIgnoreCase(TradingConstants.ENTITY_TYPE_SELL)){
				} else {
					throw new TradingException("Invalid entity type");
				}
			}else{
				throw new TradingException("Entity type cannot be null");
			}
			
			if(currency != null){
				if(currency.equalsIgnoreCase(TradingConstants.CURRENCY_TYPE_AED) || 
						currency.equalsIgnoreCase(TradingConstants.CURRENCY_TYPE_SGP) ||
								currency.equalsIgnoreCase(TradingConstants.CURRENCY_TYPE_SAR)){
				} else {
					throw new TradingException("Invalid currency type");
					
				}
			}else{
				throw new TradingException("Currency cannot be null");
			}
			
			TradingUtil.validateInstructionDate(instructionDate);
			
		} catch (TradingException te) {
			flag = false;
			throw te;
		} catch (Exception exception) {
			flag = false;
			throw new TradingException("Exception encountered while validating transaction details", exception);
		}
	return flag;
	}

	
	/**
	 * @return the tradingDao
	 */
	public TradingDao getTradingDao() {
		if(tradingDao == null) {
			tradingDao = new TradingDaoImpl();
		}
		return tradingDao;
	}

}
