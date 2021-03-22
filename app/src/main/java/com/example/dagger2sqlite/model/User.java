package com.example.dagger2sqlite.model;

import com.squareup.moshi.Json;

public class User {
    private long id;
    @Json(name = "UserName")
    private String username;
    @Json(name = "Description")
    private String description;
    @Json(name = "Password")
    private String password;
    @Json(name = "Hash")
    private String hash;
    @Json(name = "SalesPersonCode")
    private String salesPersonCode;
    @Json(name = "LineDiscount")
    private boolean lineDiscount;
    @Json(name = "InvoiceDiscount")
    private boolean invoiceDiscount;
    @Json(name = "NotActive")
    private boolean notActive;
    @Json(name = "Profile")
    private int profile;
    @Json(name = "Web")
    private boolean web;
    @Json(name = "CreationDate")
    private String creationDate;
    @Json(name = "Delete")
    private boolean deleteStatus;
    @Json(name = "Edit")
    private boolean editStatus;
    @Json(name = "SalesOrder")
    private boolean salesOrder;
    @Json(name = "SalesInvoice")
    private boolean salesInvoice;
    @Json(name = "Customer")
    private boolean customer;
    @Json(name = "Inventory")
    private boolean inventory;
    @Json(name = "Email")
    private String email;

    public User(){}

    public User(
            String username,
            String description,
            String password,
            String hash,
            String salesPersonCode,
            boolean lineDiscount,
            boolean invoiceDiscount,
            boolean notActive,
            int profile,
            boolean web,
            String creationDate,
            boolean deleteStatus,
            boolean editStatus,
            boolean salesOrder,
            boolean salesInvoice,
            boolean customer,
            boolean inventory,
            String email) {
        this.username = username;
        this.description = description;
        this.password = password;
        this.hash = hash;
        this.salesPersonCode = salesPersonCode;
        this.lineDiscount = lineDiscount;
        this.invoiceDiscount = invoiceDiscount;
        this.notActive = notActive;
        this.profile = profile;
        this.web = web;
        this.creationDate = creationDate;
        this.deleteStatus = deleteStatus;
        this.editStatus = editStatus;
        this.salesOrder = salesOrder;
        this.salesInvoice = salesInvoice;
        this.customer = customer;
        this.inventory = inventory;
        this.email = email;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getSalesPersonCode() {
        return salesPersonCode;
    }

    public void setSalesPersonCode(String salesPersonCode) {
        this.salesPersonCode = salesPersonCode;
    }

    public boolean isLineDiscount() {
        return lineDiscount;
    }

    public void setLineDiscount(boolean lineDiscount) {
        this.lineDiscount = lineDiscount;
    }

    public boolean isInvoiceDiscount() {
        return invoiceDiscount;
    }

    public void setInvoiceDiscount(boolean invoiceDiscount) {
        this.invoiceDiscount = invoiceDiscount;
    }

    public boolean isNotActive() {
        return notActive;
    }

    public void setNotActive(boolean notActive) {
        this.notActive = notActive;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public boolean isEditStatus() {
        return editStatus;
    }

    public void setEditStatus(boolean editStatus) {
        this.editStatus = editStatus;
    }

    public boolean isSalesOrder() {
        return salesOrder;
    }

    public void setSalesOrder(boolean salesOrder) {
        this.salesOrder = salesOrder;
    }

    public boolean isSalesInvoice() {
        return salesInvoice;
    }

    public void setSalesInvoice(boolean salesInvoice) {
        this.salesInvoice = salesInvoice;
    }

    public boolean isCustomer() {
        return customer;
    }

    public void setCustomer(boolean customer) {
        this.customer = customer;
    }

    public boolean isInventory() {
        return inventory;
    }

    public void setInventory(boolean inventory) {
        this.inventory = inventory;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
