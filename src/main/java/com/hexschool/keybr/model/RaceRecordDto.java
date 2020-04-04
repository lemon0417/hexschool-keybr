package com.hexschool.keybr.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class RaceRecordDto implements Serializable {

    private String nickName = "";

    private String fbLink = "";

    private String motivate = "";

    private String keybrLink = "";

    private Double[] grade = new Double[21];

    private Double[] range = new Double[2];

    private Double progress = 0.0;

    private boolean persevere = false;

    public RaceRecordDto() {
        Arrays.fill(grade, 0.0);
        Arrays.fill(range, 0.0);
    }
}
