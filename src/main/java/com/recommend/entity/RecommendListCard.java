package com.recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecommendListCard {

    private int subject_id;
    private String subject_name;
    private float score;
    private String recommend_reason;
}
