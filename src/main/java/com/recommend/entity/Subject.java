package com.recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject {

    private int subject_id;
    private String subject_name;
    private float score;
    private Integer year;
    private String director;
    private String scripter;
    private String actor;
    private String type;
    private String country;
    private String language;
    private String release_time;
    private String duration;
    private String premiere;
    private String episodes;
    private String duration_of_single_episode;
    private String profile;
    private Boolean is_movie;
    private Boolean is_tv;
    private Boolean is_animation;
}
