package org.sid.models;

public class ProductItem {
        private String quantity;
        private String name;

    public ProductItem(String quantity, String name) {
        this.quantity = quantity;
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

}
