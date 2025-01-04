package com.yanajiki.yuyudonuts.domain.controller.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class CartValueResponse {

    private final List<PackageValue> cartPackageValues;
    private final Double totalCartValue;

    public CartValueResponse(List<PackageValue> cartPackageValues) {
        this.cartPackageValues = cartPackageValues;
        this.totalCartValue = cartPackageValues.stream().mapToDouble(PackageValue::getTotalPrice).sum();
    }

}