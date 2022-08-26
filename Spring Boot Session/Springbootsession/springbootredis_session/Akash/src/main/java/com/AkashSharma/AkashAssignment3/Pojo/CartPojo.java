package com.AkashSharma.AkashAssignment3.Pojo;

import java.io.Serializable;

public class CartPojo implements Serializable {
    private Long CartId;
    private Long ToyId;
    private String ToyName;
    private String ToyDesc;
    private String ToyPrize;

    public CartPojo(ToyPojo toy) {
        this.ToyId = toy.getToyId();
        this.ToyName = toy.getToyName();
        this.ToyDesc = toy.getToyDesc();
        this.ToyPrize = toy.getToyPrize();
    }
    public CartPojo() { }

    public Long getCartId() {
        return CartId;
    }

    public void setCartId(Long cartId) {
        CartId = cartId;
    }

    public Long getToyId() {
        return ToyId;
    }

    public void setToyId(Long toyId) {
        ToyId = toyId;
    }

    public String getToyName() {
        return ToyName;
    }

    public void setToyName(String toyName) {
        ToyName = toyName;
    }

    public String getToyDesc() {
        return ToyDesc;
    }

    public void setToyDesc(String toyDesc) {
        ToyDesc = toyDesc;
    }

    public String getToyPrize() {
        return ToyPrize;
    }

    public void setToyPrize(String toyPrize) {
        ToyPrize = toyPrize;
    }
}
