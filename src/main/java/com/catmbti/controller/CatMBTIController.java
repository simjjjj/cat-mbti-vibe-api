package com.catmbti.controller;

import com.catmbti.dto.MBTIQuestion;
import com.catmbti.dto.TestResultRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class CatMBTIController {

    // 하드코딩된 질문 리스트
    private final List<MBTIQuestion> questions = Arrays.asList(
        new MBTIQuestion(1, "당신은 새로운 고양이를 보면?", 
            Arrays.asList("다가가 인사한다", "먼저 다가오기를 기다린다")),
        new MBTIQuestion(2, "혼자 있는 걸 좋아하는가?", 
            Arrays.asList("YES", "NO")),
        new MBTIQuestion(3, "낯선 환경에서는?", 
            Arrays.asList("호기심이 생긴다", "불안하고 경계한다")),
        new MBTIQuestion(4, "장난감을 보면?", 
            Arrays.asList("즉시 반응한다", "한참 관찰 후 움직인다")),
        new MBTIQuestion(5, "사람들과 함께 있을 때?", 
            Arrays.asList("활발하게 어울린다", "조용히 지켜본다")),
        new MBTIQuestion(6, "새로운 음식을 보면?", 
            Arrays.asList("바로 먹어본다", "냄새부터 맡아본다"))
    );

    @GetMapping("/test/questions")
    public List<MBTIQuestion> getQuestions() {
        return questions;
    }

    @PostMapping("/test/result")
    public String getTestResult(@RequestBody TestResultRequest request) {
        List<Integer> answers = request.getAnswers();
        
        // 점수 계산 (각 답변의 인덱스를 합산)
        int totalScore = answers.stream().mapToInt(Integer::intValue).sum();
        
        // MBTI 유형 결정
        return determineCatPersonality(totalScore);
    }

    private String determineCatPersonality(int score) {
        if (score <= 3) {
            return "INTJ – 도도한 고양이 😼\n" +
                   "당신은 독립적이고 신중한 성격의 고양이입니다. " +
                   "혼자만의 시간을 소중히 여기며, 깊이 생각한 후 행동하는 타입이에요.";
        } else if (score <= 5) {
            return "ISFP – 낯가리는 고양이 😸\n" +
                   "당신은 조용하고 온화한 성격의 고양이입니다. " +
                   "친숙한 환경에서는 애교가 많지만, 낯선 상황에서는 조심스러워해요.";
        } else if (score <= 7) {
            return "ENFP – 호기심 많은 고양이 🐱\n" +
                   "당신은 활발하고 모험을 좋아하는 고양이입니다. " +
                   "새로운 것에 대한 호기심이 많고, 에너지가 넘쳐나는 타입이에요.";
        } else {
            return "ESFJ – 다정한 고양이 😻\n" +
                   "당신은 사교적이고 따뜻한 성격의 고양이입니다. " +
                   "사람들과 함께 있는 것을 좋아하고, 관심받는 것을 즐기는 타입이에요.";
        }
    }
}