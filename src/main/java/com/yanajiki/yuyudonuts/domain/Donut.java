package com.yanajiki.yuyudonuts.domain;

public class Donut {
    private String name;
    private String uniqueName;
    private String description;
    private String imageUrl;
    private boolean isStuffed;

    public Donut(String name, String uniqueName, String description, String imageUrl, boolean isStuffed) {
        this.name = name;
        this.uniqueName = uniqueName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isStuffed = isStuffed;
    }

    public String getName() {
        return name;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean getIsStuffed() {
        return isStuffed;
    }
}

