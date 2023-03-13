package com.recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoritesItem {

    private int id;
    private int favorites_id;
    private int subject_id;
    private Timestamp collected_time;

}
