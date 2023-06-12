package com.example.nintendogame.daos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Long ntdId;
    private String ntdName;
    private Double price;
    private int quantity;

}