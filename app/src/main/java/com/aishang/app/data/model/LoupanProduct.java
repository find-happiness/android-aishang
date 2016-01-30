package com.aishang.app.data.model;

/**
 * Created by song on 2016/1/30.
 */
public class LoupanProduct {

    JLoupanProductListResult.Loupan loupan;
    JLoupanProductListResult.Product product;

    public LoupanProduct(JLoupanProductListResult.Product product, JLoupanProductListResult.Loupan loupan) {
        this.product = product;
        this.loupan = loupan;
    }

    public JLoupanProductListResult.Loupan getLoupan() {
        return loupan;
    }

    public void setLoupan(JLoupanProductListResult.Loupan loupan) {
        this.loupan = loupan;
    }

    public JLoupanProductListResult.Product getProduct() {
        return product;
    }

    public void setProduct(JLoupanProductListResult.Product product) {
        this.product = product;
    }
}
