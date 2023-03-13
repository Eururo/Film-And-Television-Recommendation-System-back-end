package com.recommend.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {

    private int user_id;
    private String user_name;
    private String portrait;
}
