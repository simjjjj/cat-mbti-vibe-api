package com.catmbti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MBTIQuestion {
    private int id;
    private String question;
    private List<String> options;
}