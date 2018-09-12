package com.jpmc.test.trading.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.jpmc.test.trading.exception.TradingException;

public class TradingDaoImplTest {
	private TradingDaoImpl tradingDaoImpl;

	@Before
	public void setUp() throws Exception {
		tradingDaoImpl = new TradingDaoImpl();
	}

	@Test
	public void testGetAgreedFxForAllCurrencies() throws TradingException {
		Map<String, Double> agreedFxMap = tradingDaoImpl
				.getAgreedFxForAllCurrencies();
		
		assertNotEquals(agreedFxMap.size(), 1);
		
		assertEquals(agreedFxMap.size(), 3);
		
		assertNotEquals(agreedFxMap.size(), 2);

	}
}
