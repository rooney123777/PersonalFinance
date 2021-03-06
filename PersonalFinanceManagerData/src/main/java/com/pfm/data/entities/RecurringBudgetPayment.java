/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pfm.data.entities;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @author mihail
 */
public class RecurringBudgetPayment {

    private UUID id;
    private int recurringType;
    private int periods;
    private int missPerPeriods;
    private boolean active;
    private Date startDate;
    private double amount;
    private UUID userId;
    private UUID paymentCategoryId;
    private String description;
    private String title;
    private boolean finished;
    private Date finishDate;
    private Date finishedDate;
    private double finalAmount;
    private double initialAmount;
    private Integer coveredPeriods;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

     public Integer getCoveredPeriods() {
        return coveredPeriods;
    }

    public void setCoveredPeriods(Integer coveredPeriods) {
        this.coveredPeriods = coveredPeriods;
    }
    
    public int getRecurringType() {
        return recurringType;
    }

    public void setRecurringType(int recurringType) {
        this.recurringType = recurringType;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public int getMissPerPeriods() {
        return missPerPeriods;
    }

    public void setMissPerPeriods(int missPerPeriods) {
        this.missPerPeriods = missPerPeriods;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getPaymentCategoryId() {
        return paymentCategoryId;
    }

    public void setPaymentCategoryId(UUID paymentCategoryId) {
        this.paymentCategoryId = paymentCategoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public Date getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Date finishedDate) {
        this.finishedDate = finishedDate;
    }
}
