package com.hexschool.keybr.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class RaceRecordDto implements Serializable {

    private String nickName;

    private String fbLink;

    private String motivate;

    private String keybrLink;

    private String[] grade = new String[21];

    public RaceRecordDto() {
        Arrays.fill(grade, "0.0");
    }
}
