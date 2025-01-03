package com.yanajiki.yuyudonuts.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DonutPackage {
    private Long id;
    private String title;
    private String description;
    private String image;
    private int quantity;

}
