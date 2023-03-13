package com.recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Favorites {

    private int favorites_id;
    private int user_id;
    private String favorites_name;
    private Timestamp create_time;

}
