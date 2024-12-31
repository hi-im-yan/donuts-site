package com.yanajiki.yuyudonuts.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

public class DonutPackage {
    private String title;
    private String description;
    private String image;
    private int quantity;

    public DonutPackage(String title, String description, String image, int quantity) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public int getQuantity() {
        return quantity;
    }
}
