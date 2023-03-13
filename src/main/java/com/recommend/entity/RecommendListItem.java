package com.recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendListItem {

    private int id;
    private int list_id;
    private int subject_id;
    private Timestamp add_time;
}
