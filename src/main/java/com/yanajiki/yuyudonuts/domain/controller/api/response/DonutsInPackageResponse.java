package com.yanajiki.yuyudonuts.domain.controller.api.response;

import com.yanajiki.yuyudonuts.domain.model.Donut;
import lombok.Getter;

import java.util.List;

@Getter
public class DonutsInPackageResponse {
    private final Long id;
    private final String title;
    private final String description;
    private final String image;
    private final int quantity;

    public DonutsInPackageResponse(Donut donut, int quantity) {
        this.id = donut.getId();
        this.title = donut.getName();
        this.description = donut.getDescription();
        this.image = donut.getImageUrl();
        this.quantity = quantity;
    }

    public static DonutsInPackageResponse fromDonutsAndQuantity(Donut donut, int quantity) {
        return new DonutsInPackageResponse(donut, quantity);
    }
}
