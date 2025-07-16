# 🐱 고양이 MBTI 성격 테스트 API

귀여운 고양이 테마의 MBTI 성격 테스트 웹 애플리케이션입니다.

## 🌟 주요 기능

- **6개의 고양이 성격 질문**: 간단하고 재미있는 질문들
- **4가지 고양이 성격 유형**: INTJ, ISFP, ENFP, ESFJ 스타일
- **반응형 웹 디자인**: 모바일/데스크톱 모두 지원
- **실시간 진행률 표시**: 테스트 진행 상황을 시각적으로 확인
- **애니메이션 효과**: 부드러운 UI/UX

## 🚀 기술 스택

- **Backend**: Spring Boot 3.5.3, Java 21
- **Frontend**: HTML5, CSS3, JavaScript (ES6+)
- **Build Tool**: Gradle
- **Database**: 없음 (하드코딩된 데이터)

## 📋 API 엔드포인트

### GET /test/questions
질문 목록을 JSON 배열로 반환합니다.

```json
[
  {
    "id": 1,
    "question": "당신은 새로운 고양이를 보면?",
    "options": ["다가가 인사한다", "먼저 다가오기를 기다린다"]
  }
]
```

### POST /test/result
사용자 답변을 분석하여 고양이 성격을 반환합니다.

**Request Body:**
```json
{
  "answers": [1, 2, 1, 2, 1, 0]
}
```

**Response:**
```
ENFP – 호기심 많은 고양이 🐱
당신은 활발하고 모험을 좋아하는 고양이입니다. 새로운 것에 대한 호기심이 많고, 에너지가 넘쳐나는 타입이에요.
```

## 🐾 고양이 성격 유형

1. **INTJ – 도도한 고양이** 😼 (0-3점)
   - 독립적이고 신중한 성격
   
2. **ISFP – 낯가리는 고양이** 😸 (4-5점)
   - 조용하고 온화한 성격
   
3. **ENFP – 호기심 많은 고양이** 🐱 (6-7점)
   - 활발하고 모험을 좋아하는 성격
   
4. **ESFJ – 다정한 고양이** 😻 (8점 이상)
   - 사교적이고 따뜻한 성격

## 🛠 로컬 실행 방법

1. **저장소 클론**
   ```bash
   git clone https://github.com/simjjjj/cat-mbti-vibe-api.git
   cd cat-mbti-vibe-api
   ```

2. **애플리케이션 실행**
   ```bash
   ./gradlew bootRun
   ```

3. **브라우저에서 접속**
   ```
   http://localhost:8085
   ```

## 📱 사용 방법

1. 메인 페이지에서 "테스트 시작하기" 클릭
2. 6개의 질문에 순서대로 답변
3. 진행률 바로 현재 진행 상황 확인
4. 결과 확인 후 "다시 테스트하기"로 재시작 가능

## 🎨 디자인 특징

- **그라데이션 배경**: 보라색 계열의 아름다운 그라데이션
- **카드 기반 UI**: 깔끔하고 모던한 카드 디자인
- **부드러운 애니메이션**: 고양이 이모지 바운스, 버튼 호버 효과
- **반응형 레이아웃**: 다양한 화면 크기에 최적화

## 🚀 배포

이 프로젝트는 다음 플랫폼에 배포할 수 있습니다:
- Heroku
- Railway
- Render
- AWS Elastic Beanstalk
- Google Cloud Platform

---

Made with ❤️ and 🐱 by [simjjjj](https://github.com/simjjjj)