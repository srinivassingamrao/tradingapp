package com.jpmc.test.trading.util;

import java.util.Comparator;

import com.jpmc.test.trading.domain.Transaction;

public class TradingComparator implements Comparator<Transaction> {

	@Override
	public int compare(Transaction t1, Transaction t2) {
		return Double.compare(t2.getTotalAmount(), t1.getTotalAmount());
	}

}
