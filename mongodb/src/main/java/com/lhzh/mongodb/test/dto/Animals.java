package com.lhzh.mongodb.test.dto;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document("animals")
public class Animals {


    private String id;

    private String name;

    private String color;

    private String hobby;

    private String weight;

    @Field("leg_num")
    private String legNum;

    private String percent;
}
