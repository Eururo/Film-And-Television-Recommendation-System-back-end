package com.recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSubject {

    private int user_id;
    private int subject_id;
    private Boolean is_collected;
    private Boolean is_watching;
    private Boolean is_watched;
    private Boolean is_scored;
    private Boolean is_like;
    private float score;
    private Timestamp watch_time;
    private String recommend_reason;
    private String link;
    private Timestamp collected_time;
}
