package com.recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendList {

    private int list_id;
    private int user_id;
    private String list_name;
    private Timestamp create_time;

}
