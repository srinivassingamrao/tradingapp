package com.jpmc.test.trading.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.jpmc.test.trading.exception.TradingException;

public class TradingControllerTest {
	
	private TradingController tController;

	@Before
	public void setUp() throws Exception {
		tController = new TradingController();	
	}

	@Test
	public void testPlaceTransaction() {
		boolean status;
		try {
			status = tController.placeTransaction("foo", "B", "SGP", "1 Sep 2018", 200, 100.25);
			assertEquals(status, true);
			status = tController.placeTransaction("foo", "B", "SGP", "3 Sep 2018", 100, 100.25);
			assertEquals(status, true);
			status = tController.placeTransaction("bar", "S", "AED", "7 Sep 2018", 200, 100.25);
			assertEquals(status, true);
			status = tController.placeTransaction("bar", "S", "SAR", "8 Sep 2018", 300, 150.55);
			assertEquals(status, true);
			
		} catch (TradingException e) {

		}
		
	}

	@Test
	public void testGetTransactionSettlementDate() {
		String settlementDate;
		try {
			settlementDate = tController.getTransactionSettlementDate("foo", "B", "SGP", "1 Sep 2018", 200, 100.25);
			assertEquals(settlementDate, "03 Sep 2018");
			assertNotEquals(settlementDate, "02 Sep 2018");
			
			settlementDate = tController.getTransactionSettlementDate("foo", "B", "SGP", "3 Sep 2018", 100, 100.25);
			assertEquals(settlementDate, "03 Sep 2018");
			assertNotEquals(settlementDate, "02 Sep 2018");
			
			settlementDate = tController.getTransactionSettlementDate("bar", "S", "AED", "7 Sep 2018", 200, 100.25);
			assertEquals(settlementDate, "09 Sep 2018");
			assertNotEquals(settlementDate, "08 Sep 2018");
			settlementDate = tController.getTransactionSettlementDate("bar", "S", "SAR", "8 Sep 2018", 300, 150.55);
			assertEquals(settlementDate, "09 Sep 2018");
			assertNotEquals(settlementDate, "08 Sep 2018");
		} catch (Exception e) {

		}
	}
	
	@Test 
	public void testInvalidEntityType() {
		try {
			tController.placeTransaction("foo", "C", "SGP", "1 Sep 2018", 200, 100.25);
		} catch (Exception e) {
			assertTrue(e instanceof TradingException);
			assertSame("Invalid entity type", e.getMessage());
		}
	}
	
	@Test 
	public void testInvaliEmptydEntityType() {
		try {
			tController.placeTransaction("foo", null, "SGP", "1 Sep 2018", 200, 100.25);
		} catch (Exception e) {
			assertTrue(e instanceof TradingException);
			assertSame("Entity type cannot be null", e.getMessage());
		}
	}
	
	@Test 
	public void testInvalidCurrencyType() {
		try {
			tController.placeTransaction("foo", "B", "SPP", "1 Sep 2018", 200, 100.25);
		} catch (Exception e) {
			assertTrue(e instanceof TradingException);
			assertSame("Invalid currency type", e.getMessage());
		}
		
	}
		
	@Test
	public void testEmptyCurrencyType() {
		try {
			tController.placeTransaction("foo", "B", null, "1 Sep 2018", 200,
					100.25);
		} catch (Exception e) {
			assertTrue(e instanceof TradingException);
			assertSame("Currency cannot be null", e.getMessage());
		}
	}
	
	@Test 
	public void testInvalidInstructionDate() {
		try {
			tController.placeTransaction("foo", "B", "SAR", "109018", 200, 100.25);
		} catch (Exception e) {
			assertTrue(e instanceof TradingException);
			assertSame("Given instruction date is not a valid date", e.getMessage());
		}
		
	}

}
