package com.example.march2022spring.models.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NameSizeErrorDTO {
    private int statusCode;
    private String msg;
    private String fieldName;

}
