package com.jpmc.test.trading.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.jpmc.test.trading.domain.Transaction;
import com.jpmc.test.trading.exception.TradingException;

public class TradingUtil {

	
	public static String getSettlementDate (String currencyType, String instructionDate) throws TradingException {
		String settlementDate = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat(TradingConstants.DATE_FORMAT);
			Date instructDate = dateFormat.parse(instructionDate);
			
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(instructDate);
			
			if(currencyType.equalsIgnoreCase(TradingConstants.CURRENCY_TYPE_SGP)){
				if(gc.get(Calendar.DAY_OF_WEEK) == 1){
					gc.set(Calendar.DAY_OF_YEAR, gc.get(Calendar.DAY_OF_YEAR)+1);
				}
				if(gc.get(Calendar.DAY_OF_WEEK) == 7){
					gc.set(Calendar.DAY_OF_YEAR, gc.get(Calendar.DAY_OF_YEAR)+2);
				}
			} else {
				if(gc.get(Calendar.DAY_OF_WEEK) == 6){
					gc.set(Calendar.DAY_OF_YEAR, gc.get(Calendar.DAY_OF_YEAR)+2);
				}
				if(gc.get(Calendar.DAY_OF_WEEK) == 7){
					gc.set(Calendar.DAY_OF_YEAR, gc.get(Calendar.DAY_OF_YEAR)+1);
				}
			}
			
			settlementDate = dateFormat.format(gc.getTime());
			
		} catch (ParseException parseException) {
			throw new TradingException("Given instruction date is not a valid date", parseException);
		} catch (Exception exception) {
			throw new TradingException("Exception encountered while getting settlement date", exception);
		}
		return settlementDate;
	}
	
	public static boolean validateInstructionDate (String instructionDate) throws TradingException {
		boolean flag = true;
		try {
			DateFormat dateFormat = new SimpleDateFormat(TradingConstants.DATE_FORMAT);
			dateFormat.parse(instructionDate);
			
		} catch (ParseException parseException) {
			flag = false;
			throw new TradingException("Given instruction date is not a valid date", parseException);
		} catch (Exception exception) {
			flag = false;
			throw new TradingException("Exception encountered while validating instruction date", exception);
		}
		return flag;
	}
	
	public static double getDailyAmount(List<Transaction> transactionList)
			throws TradingException {
		double totalAmount = 0;
		try {
			for (Transaction txn : transactionList) {
				totalAmount = totalAmount + txn.getTotalAmount();
			}
		} catch (Exception exception) {
			throw new TradingException("Exception encountered while getting Incoming amount for the day", exception);
		}
		return totalAmount;
	}
}
