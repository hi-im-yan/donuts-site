package com.yanajiki.yuyudonuts.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class Donut {
    private final Long id;
    private final String name;
    private final String uniqueName;
    private final String description;
    private final String imageUrl;
    private final boolean isStuffed;
    private final Long packageId;

}

