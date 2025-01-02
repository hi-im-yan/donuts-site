package com.yanajiki.yuyudonuts.domain;

import java.util.List;

public class DonutSelectionPageVariables {
    private final Long packageId;
    private final int packageAmount;
    private final String packageName;
    private final int amountOfDonutsToChoose;
    private final List<Donut> donutMenu;

    public DonutSelectionPageVariables(Long packageId, int packageAmount, String packageName, int amountOfDonutsToChoose, List<Donut> donutMenu) {
        this.packageId = packageId;
        this.packageAmount = packageAmount;
        this.packageName = packageName;
        this.amountOfDonutsToChoose = amountOfDonutsToChoose;
        this.donutMenu = donutMenu;
    }

    public Long getPackageId() {
        return packageId;
    }

    public int getPackageAmount() {
        return packageAmount;
    }

    public String getPackageName() {
        return packageName;
    }

    public int getAmountOfDonutsToChoose() {
        return amountOfDonutsToChoose;
    }

    public List<Donut> getDonutMenu() {
        return donutMenu;
    }
}
