package com.AkashSharma.AkashAssignment3.Pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(schema = "midterm",name = "TOYS")
@Entity
public class ToyPojo {

    @Id
    @Column(name = "ID")
    private Long toyId;

    @Column(name = "NAME")
    private String toyName;

    @Column(name = "DESCRIPTION")
    private String toyDesc;

    @Column(name = "COST")
    private String toyPrize;

    @Column(name = "IS_CART_ACTIVE",nullable = false)
    private String isCartItemActive;

    public ToyPojo() { }
    public ToyPojo(String toyName, String toyDesc, String toyPrize) {
        this.toyName = toyName;
        this.toyDesc = toyDesc;
        this.toyPrize = toyPrize;
    }

    public Long getToyId() {
        return toyId;
    }

    public void setToyId(Long toyId) {
        this.toyId = toyId;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public String getToyDesc() {
        return toyDesc;
    }

    public void setToyDesc(String toyDesc) {
        this.toyDesc = toyDesc;
    }

    public String getToyPrize() {
        return toyPrize;
    }

    public void setToyPrize(String toyPrize) {
        this.toyPrize = toyPrize;
    }

    public String getIsCartItemActive() {
        return isCartItemActive;
    }

    public void setIsCartItemActive(String isCartItemActive) {
        this.isCartItemActive = isCartItemActive;
    }
}
