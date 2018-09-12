package com.jpmc.test.trading.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.jpmc.test.trading.exception.TradingException;
import com.jpmc.test.trading.service.TradingService;

public class TradingServiceImplTest {
	
	private TradingService tradingService;

	@Before
	public void setUp() throws Exception {
		tradingService = new TradingServiceImpl();
	}

	@Test
	public void testValidateAndPlaceTransaction() {
		boolean status;
		try {
			status = tradingService.validateAndPlaceTransaction("foo", "B", "SGP", "1 Sep 2018", 200, 100.25);
			assertEquals(status, true);
			status = tradingService.validateAndPlaceTransaction("bar", "S", "AED", "7 Sep 2018", 200, 100.25);
			assertEquals(status, true);
			status = tradingService.validateAndPlaceTransaction("bar", "S", "SAR", "8 Sep 2018", 300, 150.55);
		} catch (Exception e) {
		
		}
	}

	@Test
	public void testGetTransactionSettlementDate() {
		String settlementDate;
		try {
			settlementDate = tradingService.getTransactionSettlementDate("foo", "B", "SGP", "1 Sep 2018", 200, 100.25);
			assertEquals(settlementDate, "03 Sep 2018");
			assertNotEquals(settlementDate, "02 Sep 2018");
			
			settlementDate = tradingService.getTransactionSettlementDate("foo", "B", "SGP", "3 Sep 2018", 100, 100.25);
			assertEquals(settlementDate, "03 Sep 2018");
			assertNotEquals(settlementDate, "02 Sep 2018");
			
			settlementDate = tradingService.getTransactionSettlementDate("foo", "B", "SGP", "2 Sep 2018", 150, 100.25);
			assertEquals(settlementDate, "03 Sep 2018");
			assertNotEquals(settlementDate, "02 Sep 2018");
			
			settlementDate = tradingService.getTransactionSettlementDate("bar", "S", "AED", "7 Sep 2018", 200, 100.25);
			assertEquals(settlementDate, "09 Sep 2018");
			assertNotEquals(settlementDate, "08 Sep 2018");
			
			settlementDate = tradingService.getTransactionSettlementDate("bar", "S", "AED", "8 Sep 2018", 205, 100.25);
			assertEquals(settlementDate, "09 Sep 2018");
			assertNotEquals(settlementDate, "08 Sep 2018");
			
			settlementDate = tradingService.getTransactionSettlementDate("bar", "S", "AED", "10 Sep 2018", 200, 100.25);
			assertEquals(settlementDate, "10 Sep 2018");
			assertNotEquals(settlementDate, "12 Sep 2018");
			
			settlementDate = tradingService.getTransactionSettlementDate("bar", "S", "SAR", "8 Sep 2018", 300, 150.55);
			assertEquals(settlementDate, "09 Sep 2018");
			assertNotEquals(settlementDate, "08 Sep 2018");
			
			settlementDate = tradingService.getTransactionSettlementDate("bar", "S", "SAR", "16 Sep 2018", 300, 150.55);
			assertEquals(settlementDate, "16 Sep 2018");
			assertNotEquals(settlementDate, "17 Sep 2018");
		} catch (Exception e) {

		}
	}
	
	@Test 
	public void testInvalidEntityType() {
		try {
			tradingService.validateAndPlaceTransaction("foo", "C", "SGP", "1 Sep 2018", 200, 100.25);
		} catch (Exception e) {
			assertTrue(e instanceof TradingException);
			assertSame("Invalid entity type", e.getMessage());
		}
	}
	
	@Test 
	public void testInvaliEmptydEntityType() {
		try {
			tradingService.validateAndPlaceTransaction("foo", null, "SGP", "1 Sep 2018", 200, 100.25);
		} catch (Exception e) {
			assertTrue(e instanceof TradingException);
			assertSame("Entity type cannot be null", e.getMessage());
		}
	}
	
	@Test 
	public void testInvalidCurrencyType() {
		try {
			tradingService.validateAndPlaceTransaction("foo", "B", "SPP", "1 Sep 2018", 200, 100.25);
		} catch (Exception e) {
			assertTrue(e instanceof TradingException);
			assertSame("Invalid currency type", e.getMessage());
		}
		
	}
		
	@Test
	public void testEmptyCurrencyType() {
		try {
			tradingService.validateAndPlaceTransaction("foo", "B", null, "1 Sep 2018", 200,	100.25);
		} catch (Exception e) {
			assertTrue(e instanceof TradingException);
			assertSame("Currency cannot be null", e.getMessage());
		}
	}
	
	@Test 
	public void testInvalidInstructionDate() {
		try {
			tradingService.validateAndPlaceTransaction("foo", "B", "SAR", "109018", 200, 100.25);
		} catch (Exception e) {
			assertTrue(e instanceof TradingException);
			assertSame("Given instruction date is not a valid date", e.getMessage());
		}
		
	}
	
	@Test
	public void testTransactionDoesNotExists() {
		try {
			tradingService.getTransactionSettlementDate("bar", "S", "SAR", "8 Sep 2000", 300, 150.55);
		} catch (Exception e) {
			assertTrue(e instanceof TradingException);
			assertSame("Given transaction does not exists", e.getMessage());
		}
	}


}
