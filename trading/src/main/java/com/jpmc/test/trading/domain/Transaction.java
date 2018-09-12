package com.jpmc.test.trading.domain;


public class Transaction {

	private String entity;
	private String entityType;
	private String currency;
	private String instructionDate;
	private String settlementDate;
	private int units;
	private double pricePerUnit;
	private double totalAmount;
	
	/**
	 * @return the entity
	 */
	public String getEntity() {
		return entity;
	}
	/**
	 * @param entity the entity to set
	 */
	public void setEntity(String entity) {
		this.entity = entity;
	}
	/**
	 * @return the entityType
	 */
	public String getEntityType() {
		return entityType;
	}
	/**
	 * @param entityType the entityType to set
	 */
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the settlementDate
	 */
	
	/**
	 * @return the units
	 */
	public int getUnits() {
		return units;
	}
	/**
	 * @return the instructionDate
	 */
	public String getInstructionDate() {
		return instructionDate;
	}
	/**
	 * @param instructionDate the instructionDate to set
	 */
	public void setInstructionDate(String instructionDate) {
		this.instructionDate = instructionDate;
	}
	/**
	 * @return the settlementDate
	 */
	public String getSettlementDate() {
		return settlementDate;
	}
	/**
	 * @param settlementDate the settlementDate to set
	 */
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	/**
	 * @param units the units to set
	 */
	public void setUnits(int units) {
		this.units = units;
	}
	/**
	 * @return the pricePerUnit
	 */
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	/**
	 * @param pricePerUnit the pricePerUnit to set
	 */
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}
	
	
	/**
	 * @return the totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	
	
	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Transaction(String entity, String entityType, String currency,
			String instructionDate, int units,double pricePerUnit) {
		super();
		this.entity = entity;
		this.entityType = entityType;
		this.currency = currency;
		this.instructionDate = instructionDate;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
	}
	
	
	
	
}
