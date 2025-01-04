package com.yanajiki.yuyudonuts.domain.controller.api.response;

import com.yanajiki.yuyudonuts.domain.model.Donut;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PackageValue {

    private final Long id;
    private final String packageName;
    private final int quantity;
    private final Double pricePerPackage;
    private final Double totalPrice;

    @Setter
    private List<DonutsInPackageResponse> donuts;

    public PackageValue(Long id, String packageName, int quantity, Double pricePerPackage) {
        this.id = id;
        this.packageName = packageName;
        this.quantity = quantity;
        this.pricePerPackage = pricePerPackage;
        this.totalPrice = pricePerPackage * quantity;
    }

}
