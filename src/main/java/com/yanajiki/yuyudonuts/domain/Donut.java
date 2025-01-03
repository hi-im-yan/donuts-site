package com.yanajiki.yuyudonuts.domain;

public class Donut {
    private final Long id;
    private final String name;
    private final String uniqueName;
    private final String description;
    private final String imageUrl;
    private final boolean isStuffed;
    private final Long packageId;

    public Donut(Long id, String name, String uniqueName, String description, String imageUrl, boolean isStuffed, Long packageId) {
        this.id = id;
        this.name = name;
        this.uniqueName = uniqueName;
        this.description = description;
        this.imageUrl = imageUrl;
        this.isStuffed = isStuffed;
        this.packageId = packageId;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Long getId() {
        return id;
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

    public Long getPackageId() {
        return packageId;
    }
}

