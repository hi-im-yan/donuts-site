package com.yanajiki.yuyudonuts.domain.controller.api.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@ToString
public class PackageRequest {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private static final int DONUTS_PER_PACKAGE = 4;

    private Long packageId;
    private int packageAmount;
    private String packageName;
    private List<DonutsInPackageRequest> donuts;

    public boolean isCorrectDonutCount() {
        Integer donutCount = donuts.stream().map(DonutsInPackageRequest::getQuantity).reduce(Integer::sum).orElse(0);

        return donutCount.equals(packageAmount * DONUTS_PER_PACKAGE);
    }
}
