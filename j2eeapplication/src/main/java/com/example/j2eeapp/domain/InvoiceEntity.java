/*
* Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* WSO2 Inc. licenses this file to you under the Apache License,
* Version 2.0 (the "License"); you may not use this file except
* in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied. See the License for the
* specific language governing permissions and limitations
* under the License.
*
*/
package com.example.j2eeapp.domain;

import com.example.j2eeapp.commons.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
public class InvoiceEntity extends BaseEntity {

    private String createdDate;
    private String dueDate;

    @GeneratedValue(strategy=GenerationType.AUTO)
    private int invoiceNo;
    private String userFirstName;
    private String userLastName;
    private String userCompany;
    private String userEmail;
    private String address1, address2, address3;
    private String paymentMethod;
    private int successCount;
    private int throttleCount;

    private String subscriptionFee;
    private String successFee;
    private String throttleFee;
    private String totalFee;

    private String feePerSuccess;
    private String feePerThrottle;

    private String planName;

    public String getFeePerSuccess() {
        return feePerSuccess;
    }

    public void setFeePerSuccess(String feePerSuccess) {
        this.feePerSuccess = feePerSuccess;
    }

    public String getFeePerThrottle() {
        return feePerThrottle;
    }

    public void setFeePerThrottle(String feePerThrottle) {
        this.feePerThrottle = feePerThrottle;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(String userCompany) {
        this.userCompany = userCompany;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getThrottleCount() {
        return throttleCount;
    }

    public void setThrottleCount(int throttleCount) {
        this.throttleCount = throttleCount;
    }

    public String getSubscriptionFee() {
        return subscriptionFee;
    }

    public void setSubscriptionFee(String subscriptionFee) {
        this.subscriptionFee = subscriptionFee;
    }

    public String getSuccessFee() {
        return successFee;
    }

    public void setSuccessFee(String successFee) {
        this.successFee = successFee;
    }

    public String getThrottleFee() {
        return throttleFee;
    }

    public void setThrottleFee(String throttleFee) {
        this.throttleFee = throttleFee;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

}