package com.catmbti.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TestResultRequest {
    private List<Integer> answers;
}