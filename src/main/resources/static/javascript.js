const startButton = document.getElementById('start-btn')
const nextButton = document.getElementById('next-btn')

const questionContainerElement = document.getElementById("question-container")


let shuffleQuestions, currentQuestionIndex
const questionElement = document.getElementById('question')
const answerButtonsElement =  document.getElementById('answer-buttons')

startButton.addEventListener('click',startGame)
nextButton.addEventListener('click',() => {
    currentQuestionIndex ++;
    setNextQuestion();
})

function startGame(){
	alert("Comenzando juego");
    console.log('Iniciando');
    startButton.classList.add('hide');
    shuffleQuestions = questions.sort(() => Math.random() - .5);
    currentQuestionIndex = 0;
    questionContainerElement.classList.remove('hide');
    setNextQuestion(false);
}


function setNextQuestion(){
    resetState();
    showQuestion(shuffleQuestions[currentQuestionIndex])
}

function resetState(){
    clearStatusClass(document.body)
    nextButton.classList.add('hide');
    while(answerButtonsElement.firstChild){
        answerButtonsElement.removeChild(answerButtonsElement.firstChild)
    }
}

function showQuestion(question){
    questionElement.innerText = question.question;
    question.answers.forEach(answer => {
        const button = document.createElement('button')
        button.innerText = answer.text
        button.classList.add('btn')
        if(answer.correct){
            button.dataset.correct = answer.correct
        }
        button.addEventListener('click', selectAnswer)
        answerButtonsElement.appendChild(button)
    })
}

function selectAnswer(e){
    //alert("no entra"+e);
    const selectedButton = e.target
    const correct = selectedButton.dataset.correct
    setStatusClass(document.body, correct)
    Array.from(answerButtonsElement.children).forEach(button =>{
        setStatusClass(button, button.dataset.correct)
    })
    if(shuffleQuestions.length > currentQuestionIndex +1){
        nextButton.classList.remove('hide')
    } 
    else{
        startButton.innerText = "Restart";
        startButton.classList.remove('hide');
    }

}

function setStatusClass(element, correct){
    clearStatusClass(element)
    //alert(correct);
    if(correct){
        element.classList.add('correct')
    }
    else {
        element.classList.add('wrong')
    }
}

function clearStatusClass(element){
    element.classList.remove('correct')
    element.classList.remove('wrong')
}

const questions = [
    {
        question: 'What is 2 +2?',
        answers: [
            {text: '4', correct: true},
            {text: '22', cottect: false}
            
        ]
    },
    {
        question: 'Quién no es un Yonko?',
        answers: [
            {text: 'Shanks', correct: false},
            {text: 'Buggy', correct: true},
            {text: 'Big Mom', correct: false},
            {text: 'Shirohige', correct: false}
        ]
    }
]