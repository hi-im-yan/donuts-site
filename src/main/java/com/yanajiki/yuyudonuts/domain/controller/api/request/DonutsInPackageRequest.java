package com.yanajiki.yuyudonuts.domain.controller.api.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DonutsInPackageRequest {

    private Long donutId;
    private int quantity;
}
