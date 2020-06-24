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
        button.addEventListener('click', selectAnswer,{once:true})
        answerButtonsElement.appendChild(button)
        
    })
}

function selectAnswer(e){
    //alert("no entra"+e);
	console.log("entrando en selectAnswer: "+e);
    const selectedButton = e.target
    //alter("e.target: "+e.target);
    console.log("entrando en selectAnswer: "+selectedButton);
    const correct = selectedButton.dataset.correct
    console.log("CORRECT: "+correct);
    setStatusClass(document.body, correct)
    console.log("Respuestas: "+answerButtonsElement.children);
    Array.from(answerButtonsElement.children).forEach(button =>{
    	console.log("buton: "+button);
        setStatusClass(button, button.dataset.correct)
    })
    while(answerButtonsElement.firstChild){
        answerButtonsElement.removeChild(answerButtonsElement.firstChild)
    }
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


