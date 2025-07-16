class CatMBTITest {
    constructor() {
        this.questions = [];
        this.answers = [];
        this.currentQuestionIndex = 0;
        
        this.initElements();
        this.bindEvents();
        this.loadQuestions();
    }
    
    initElements() {
        this.welcomeScreen = document.getElementById('welcome-screen');
        this.questionScreen = document.getElementById('question-screen');
        this.resultScreen = document.getElementById('result-screen');
        this.loading = document.getElementById('loading');
        
        this.startBtn = document.getElementById('start-btn');
        this.restartBtn = document.getElementById('restart-btn');
        
        this.progress = document.getElementById('progress');
        this.currentQuestionSpan = document.getElementById('current-question');
        this.totalQuestionsSpan = document.getElementById('total-questions');
        this.questionText = document.getElementById('question-text');
        this.optionsContainer = document.getElementById('options-container');
        
        this.resultEmoji = document.getElementById('result-emoji');
        this.resultTitle = document.getElementById('result-title');
        this.resultDescription = document.getElementById('result-description');
    }
    
    bindEvents() {
        this.startBtn.addEventListener('click', () => this.startTest());
        this.restartBtn.addEventListener('click', () => this.restartTest());
    }
    
    async loadQuestions() {
        try {
            const response = await fetch('/test/questions');
            this.questions = await response.json();
            this.totalQuestionsSpan.textContent = this.questions.length;
        } catch (error) {
            console.error('질문을 불러오는데 실패했습니다:', error);
            alert('질문을 불러오는데 실패했습니다. 페이지를 새로고침해주세요.');
        }
    }
    
    startTest() {
        this.answers = [];
        this.currentQuestionIndex = 0;
        this.showScreen('question');
        this.displayQuestion();
    }
    
    showScreen(screenName) {
        document.querySelectorAll('.screen').forEach(screen => {
            screen.classList.remove('active');
        });
        
        switch(screenName) {
            case 'welcome':
                this.welcomeScreen.classList.add('active');
                break;
            case 'question':
                this.questionScreen.classList.add('active');
                break;
            case 'result':
                this.resultScreen.classList.add('active');
                break;
        }
    }
    
    displayQuestion() {
        const question = this.questions[this.currentQuestionIndex];
        
        // 진행률 업데이트
        const progressPercent = ((this.currentQuestionIndex + 1) / this.questions.length) * 100;
        this.progress.style.width = `${progressPercent}%`;
        
        // 질문 번호 업데이트
        this.currentQuestionSpan.textContent = this.currentQuestionIndex + 1;
        
        // 질문 텍스트 업데이트
        this.questionText.textContent = question.question;
        
        // 옵션 생성
        this.optionsContainer.innerHTML = '';
        question.options.forEach((option, index) => {
            const optionElement = document.createElement('div');
            optionElement.className = 'option';
            optionElement.textContent = option;
            optionElement.addEventListener('click', () => this.selectOption(index));
            this.optionsContainer.appendChild(optionElement);
        });
    }
    
    selectOption(optionIndex) {
        // 선택된 옵션 하이라이트
        document.querySelectorAll('.option').forEach((option, index) => {
            option.classList.toggle('selected', index === optionIndex);
        });
        
        // 답변 저장
        this.answers[this.currentQuestionIndex] = optionIndex;
        
        // 잠시 후 다음 질문으로
        setTimeout(() => {
            this.nextQuestion();
        }, 500);
    }
    
    nextQuestion() {
        this.currentQuestionIndex++;
        
        if (this.currentQuestionIndex < this.questions.length) {
            this.displayQuestion();
        } else {
            this.submitAnswers();
        }
    }
    
    async submitAnswers() {
        this.showLoading(true);
        
        try {
            const response = await fetch('/test/result', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ answers: this.answers })
            });
            
            const result = await response.text();
            this.displayResult(result);
        } catch (error) {
            console.error('결과를 가져오는데 실패했습니다:', error);
            alert('결과를 가져오는데 실패했습니다. 다시 시도해주세요.');
        } finally {
            this.showLoading(false);
        }
    }
    
    displayResult(result) {
        // 결과 파싱
        const lines = result.split('\n');
        const title = lines[0];
        const description = lines.slice(1).join('\n').trim();
        
        // 이모지 추출
        let emoji = '🐱';
        if (title.includes('도도한')) emoji = '😼';
        else if (title.includes('낯가리는')) emoji = '😸';
        else if (title.includes('호기심')) emoji = '🐱';
        else if (title.includes('다정한')) emoji = '😻';
        
        this.resultEmoji.textContent = emoji;
        this.resultTitle.textContent = title;
        this.resultDescription.textContent = description;
        
        this.showScreen('result');
    }
    
    showLoading(show) {
        this.loading.classList.toggle('hidden', !show);
    }
    
    restartTest() {
        this.showScreen('welcome');
    }
}

// 페이지 로드 시 테스트 초기화
document.addEventListener('DOMContentLoaded', () => {
    new CatMBTITest();
});