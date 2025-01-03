package com.yanajiki.yuyudonuts.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class DonutSelectionPageVariables {
    private final Long packageId;
    private final int packageAmount;
    private final String packageName;
    private final int amountOfDonutsToChoose;
    private final List<Donut> donutMenu;

}
