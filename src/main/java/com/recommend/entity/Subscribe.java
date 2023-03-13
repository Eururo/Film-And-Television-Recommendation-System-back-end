package com.recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscribe {
    private int user_id;
    private int list_id;
    private Timestamp subscribe_time;
}
