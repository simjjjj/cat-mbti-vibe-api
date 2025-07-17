package com.catmbti.controller;

import com.catmbti.dto.MBTIQuestion;
import com.catmbti.dto.TestResultRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class CatMBTIController {

    // 하드코딩된 질문 리스트 (12개 질문으로 확장) - 고양이 행동 중심
    private final List<MBTIQuestion> questions = Arrays.asList(
        // E vs I (외향성 vs 내향성) - 고양이의 사회성
        new MBTIQuestion(1, "당신의 고양이가 새로운 사람을 만났을 때?", 
            Arrays.asList("적극적으로 다가가서 관심을 보인다", "멀리서 지켜보거나 숨는다")),
        new MBTIQuestion(2, "집에 손님이 왔을 때 고양이는?", 
            Arrays.asList("손님 주변을 맴돌며 관심을 끈다", "조용한 곳으로 피해서 숨는다")),
        new MBTIQuestion(3, "고양이가 에너지를 회복하는 방법은?", 
            Arrays.asList("사람들과 함께 놀고 상호작용한다", "혼자만의 공간에서 조용히 쉰다")),
        
        // S vs N (감각 vs 직관) - 고양이의 탐색 방식
        new MBTIQuestion(4, "새로운 장난감을 줬을 때 고양이는?", 
            Arrays.asList("즉시 냄새 맡고 만져보며 탐색한다", "멀리서 관찰한 후 천천히 접근한다")),
        new MBTIQuestion(5, "고양이가 먹이를 대하는 방식은?", 
            Arrays.asList("눈앞의 음식에 집중하며 바로 먹는다", "주변을 살피고 상황을 파악한 후 먹는다")),
        new MBTIQuestion(6, "새로운 환경에서 고양이는?", 
            Arrays.asList("구석구석 직접 탐험하며 확인한다", "높은 곳에서 전체적으로 관찰한다")),
        
        // T vs F (사고 vs 감정) - 고양이의 반응 방식
        new MBTIQuestion(7, "다른 고양이와 마주쳤을 때?", 
            Arrays.asList("크기와 힘을 비교하며 우위를 정한다", "상대방의 기분을 살피며 조심스럽게 접근한다")),
        new MBTIQuestion(8, "주인이 슬퍼할 때 고양이는?", 
            Arrays.asList("평소와 다름없이 자신의 일을 한다", "주인 곁에 다가가 위로하려 한다")),
        new MBTIQuestion(9, "고양이가 혼날 때 반응은?", 
            Arrays.asList("당당하게 쳐다보거나 무시한다", "풀죽은 표정으로 미안해한다")),
        
        // J vs P (판단 vs 인식) - 고양이의 생활 패턴
        new MBTIQuestion(10, "고양이의 하루 일과는?", 
            Arrays.asList("정해진 시간에 밥 먹고 자는 규칙적인 생활", "기분에 따라 자유롭게 행동한다")),
        new MBTIQuestion(11, "고양이의 잠자리는?", 
            Arrays.asList("항상 같은 자리에서 잔다", "매일 다른 곳에서 잔다")),
        new MBTIQuestion(12, "고양이가 놀 때는?", 
            Arrays.asList("정해진 장난감으로 일정하게 논다", "그때그때 흥미로운 것을 찾아 논다"))
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