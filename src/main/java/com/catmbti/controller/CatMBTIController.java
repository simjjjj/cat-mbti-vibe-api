package com.catmbti.controller;

import com.catmbti.dto.MBTIQuestion;
import com.catmbti.dto.TestResultRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class CatMBTIController {

    // 하드코딩된 질문 리스트 (12개 질문으로 확장)
    private final List<MBTIQuestion> questions = Arrays.asList(
        // E vs I (외향성 vs 내향성)
        new MBTIQuestion(1, "새로운 고양이를 만났을 때 당신은?", 
            Arrays.asList("먼저 다가가서 인사한다", "상대방이 다가올 때까지 기다린다")),
        new MBTIQuestion(2, "파티에 갔을 때 당신은?", 
            Arrays.asList("여러 고양이들과 활발하게 어울린다", "친한 몇 마리와만 조용히 이야기한다")),
        new MBTIQuestion(3, "에너지를 충전하는 방법은?", 
            Arrays.asList("다른 고양이들과 함께 놀기", "혼자만의 시간 갖기")),
        
        // S vs N (감각 vs 직관)
        new MBTIQuestion(4, "새로운 장난감을 받았을 때?", 
            Arrays.asList("즉시 만져보고 어떻게 작동하는지 확인한다", "이걸로 뭘 할 수 있을지 상상해본다")),
        new MBTIQuestion(5, "정보를 받아들일 때?", 
            Arrays.asList("구체적이고 실용적인 정보를 선호한다", "가능성과 의미를 찾는 것을 선호한다")),
        new MBTIQuestion(6, "문제를 해결할 때?", 
            Arrays.asList("경험과 사실에 기반해서 해결한다", "새로운 아이디어와 직감으로 해결한다")),
        
        // T vs F (사고 vs 감정)
        new MBTIQuestion(7, "다른 고양이와 갈등이 생겼을 때?", 
            Arrays.asList("논리적으로 따져서 해결한다", "서로의 감정을 고려해서 해결한다")),
        new MBTIQuestion(8, "결정을 내릴 때 중요한 것은?", 
            Arrays.asList("객관적이고 합리적인 분석", "관련된 모든 이의 감정과 가치")),
        new MBTIQuestion(9, "비판을 받았을 때?", 
            Arrays.asList("내용이 맞는지 객관적으로 판단한다", "상대방의 의도와 내 감정을 먼저 생각한다")),
        
        // J vs P (판단 vs 인식)
        new MBTIQuestion(10, "하루 일과는?", 
            Arrays.asList("미리 계획을 세우고 그대로 실행한다", "그때그때 상황에 맞춰 유연하게 한다")),
        new MBTIQuestion(11, "여행을 갈 때?", 
            Arrays.asList("상세한 일정을 미리 짜놓는다", "대략적인 계획만 세우고 즉흥적으로 한다")),
        new MBTIQuestion(12, "마감이 있는 일을 할 때?", 
            Arrays.asList("미리미리 차근차근 준비한다", "마감 직전에 집중해서 몰아서 한다"))
    );

    @GetMapping("/test/questions")
    public List<MBTIQuestion> getQuestions() {
        return questions;
    }

    @PostMapping("/test/result")
    public String getTestResult(@RequestBody TestResultRequest request) {
        List<Integer> answers = request.getAnswers();
        
        // MBTI 4개 차원별 점수 계산
        String mbtiType = calculateMBTIType(answers);
        
        // 고양이 성격 결정
        return determineCatPersonality(mbtiType);
    }

    private String calculateMBTIType(List<Integer> answers) {
        // E vs I (질문 1-3): 0=E, 1=I
        int eScore = 0;
        for (int i = 0; i < 3; i++) {
            if (answers.get(i) == 0) eScore++;
        }
        char ei = eScore >= 2 ? 'E' : 'I';
        
        // S vs N (질문 4-6): 0=S, 1=N  
        int sScore = 0;
        for (int i = 3; i < 6; i++) {
            if (answers.get(i) == 0) sScore++;
        }
        char sn = sScore >= 2 ? 'S' : 'N';
        
        // T vs F (질문 7-9): 0=T, 1=F
        int tScore = 0;
        for (int i = 6; i < 9; i++) {
            if (answers.get(i) == 0) tScore++;
        }
        char tf = tScore >= 2 ? 'T' : 'F';
        
        // J vs P (질문 10-12): 0=J, 1=P
        int jScore = 0;
        for (int i = 9; i < 12; i++) {
            if (answers.get(i) == 0) jScore++;
        }
        char jp = jScore >= 2 ? 'J' : 'P';
        
        return "" + ei + sn + tf + jp;
    }

    private String determineCatPersonality(String mbtiType) {
        switch (mbtiType) {
            case "INTJ":
                return "INTJ – 전략가 고양이 😼\n" +
                       "당신의 고양이는 독립적이고 창의적인 성격입니다. 혼자만의 시간을 소중히 여기며, " +
                       "깊이 생각한 후 행동하는 신중한 타입이에요.";
            
            case "INTP":
                return "INTP – 논리학자 고양이 🤔\n" +
                       "당신의 고양이는 호기심 많고 분석적인 성격입니다. 새로운 아이디어를 탐구하고 " +
                       "논리적으로 사고하는 것을 좋아해요.";
            
            case "ENTJ":
                return "ENTJ – 지휘관 고양이 👑\n" +
                       "당신의 고양이는 타고난 리더십을 가진 성격입니다. 목표 지향적이고 " +
                       "다른 고양이들을 이끄는 것을 좋아해요.";
            
            case "ENTP":
                return "ENTP – 변론가 고양이 💡\n" +
                       "당신의 고양이는 창의적이고 활발한 성격입니다. 새로운 가능성을 탐구하고 " +
                       "토론하는 것을 즐겨해요.";
            
            case "INFJ":
                return "INFJ – 옹호자 고양이 🌙\n" +
                       "당신의 고양이는 신비롭고 이상주의적인 성격입니다. 깊은 통찰력을 가지고 있으며 " +
                       "다른 고양이들을 도우려 해요.";
            
            case "INFP":
                return "INFP – 중재자 고양이 🌸\n" +
                       "당신의 고양이는 온화하고 창의적인 성격입니다. 자신만의 가치관을 중요시하며 " +
                       "평화로운 환경을 선호해요.";
            
            case "ENFJ":
                return "ENFJ – 주인공 고양이 ✨\n" +
                       "당신의 고양이는 카리스마 있고 영감을 주는 성격입니다. 다른 고양이들의 성장을 " +
                       "도우며 조화로운 관계를 만들어가요.";
            
            case "ENFP":
                return "ENFP – 활동가 고양이 🌈\n" +
                       "당신의 고양이는 열정적이고 창의적인 성격입니다. 새로운 것에 대한 호기심이 많고 " +
                       "에너지가 넘쳐나는 타입이에요.";
            
            case "ISTJ":
                return "ISTJ – 물류관리자 고양이 📋\n" +
                       "당신의 고양이는 신뢰할 수 있고 체계적인 성격입니다. 규칙을 잘 지키며 " +
                       "책임감이 강한 성실한 타입이에요.";
            
            case "ISFJ":
                return "ISFJ – 수호자 고양이 🛡️\n" +
                       "당신의 고양이는 따뜻하고 배려심 많은 성격입니다. 다른 고양이들을 보살피며 " +
                       "조용히 도움을 주는 타입이에요.";
            
            case "ESTJ":
                return "ESTJ – 경영자 고양이 💼\n" +
                       "당신의 고양이는 실용적이고 조직적인 성격입니다. 질서를 중요시하며 " +
                       "효율적으로 일을 처리하는 타입이에요.";
            
            case "ESFJ":
                return "ESFJ – 집정관 고양이 💕\n" +
                       "당신의 고양이는 사교적이고 협력적인 성격입니다. 다른 고양이들과의 조화를 중시하며 " +
                       "따뜻한 관계를 만들어가요.";
            
            case "ISTP":
                return "ISTP – 만능재주꾼 고양이 🔧\n" +
                       "당신의 고양이는 실용적이고 적응력이 뛰어난 성격입니다. 손재주가 좋고 " +
                       "문제 해결 능력이 뛰어나요.";
            
            case "ISFP":
                return "ISFP – 모험가 고양이 🎨\n" +
                       "당신의 고양이는 온화하고 예술적 감각이 뛰어난 성격입니다. 자유로운 영혼을 가지고 있으며 " +
                       "아름다운 것들을 추구해요.";
            
            case "ESTP":
                return "ESTP – 사업가 고양이 🎯\n" +
                       "당신의 고양이는 활동적이고 현실적인 성격입니다. 순간을 즐기며 " +
                       "모험을 좋아하는 에너지 넘치는 타입이에요.";
            
            case "ESFP":
                return "ESFP – 연예인 고양이 🎭\n" +
                       "당신의 고양이는 활발하고 사교적인 성격입니다. 관심받는 것을 좋아하며 " +
                       "다른 고양이들에게 즐거움을 주는 타입이에요.";
            
            default:
                return "ENFP – 활동가 고양이 🌈\n" +
                       "당신의 고양이는 열정적이고 창의적인 성격입니다!";
        }
    }
}