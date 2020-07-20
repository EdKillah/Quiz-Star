function BBServiceURL() {
    return 'ws://localhost:8080/qsService';
}

class WSBBChannel {
    constructor(URL, callback) {
        this.URL = URL;
        console.log("Este es el url del wsbbchanel: ",URL);
        this.wsocket = new WebSocket(URL);
        this.wsocket.onopen = (evt) => this.onOpen(evt);
        this.wsocket.onmessage = (evt) => this.onMessage(evt);
        this.wsocket.onerror = (evt) => this.onError(evt);
        this.receivef = callback;
        this.xIsNext = null;
    }


    onOpen(evt) {
        console.log("In onOpen", evt);
    }
    onMessage(evt) {
        console.log("In onMessage", evt);
        // Este if permite que el primer mensaje del servidor no se tenga en cuenta.
        // El primer mensaje solo confirma que se estableció la conexión.
        // De ahí en adelante intercambiaremos solo puntos(x,y) con el servidor
        
        if(evt.data != "Connection established.") {
        	console.log("Esta entrando en diferente de conection established");
        	if(evt.data == "xx"){
        		console.log("Entra en X inicial");
        		this.xIsNext = "xx";
        	}
        	else if(evt.data == "oo"){
        		console.log("Entra en O inicial");
        		this.xIsNext = "oo";
        	}
        	else{
        		console.log("El valor de ficha: ",this.xIsNext);
            	this.receivef(evt.data);
            }	
        }
    }
    
    onError(evt) {
        console.error("In onError", evt);
    }
    
    send(x, y) {
    	console.log("Entrando en send:");
    	console.log("x: ",x,"y: ",y);
        let msg = '{ "x": ' + (x) + ', "y": ' + (y) +', "ficha": '+ (null) +"}";
        console.log("sending: ", msg);
        this.wsocket.send(msg);
    }


}


const startButton = document.getElementById('start-btn')
const nextButton = document.getElementById('next-btn')

const questionContainerElement = document.getElementById("question-container")


let shuffleQuestions, currentQuestionIndex
const questionElement = document.getElementById('question')
const answerButtonsElement =  document.getElementById('answer-buttons')
const questionImage = document.getElementById("img-question")

startButton.addEventListener('click',startGame)
nextButton.addEventListener('click',() => {
    currentQuestionIndex ++;
    setNextQuestion();
})

let comunicationWS;

function startGame(){
	alert("Comenzando juego");
	comunicationWS =
        new WSBBChannel(BBServiceURL(),
                (msg) => {
            console.log("En websocket: ", msg);
        });
	console.log("Esto es el wschanel: ",comunicationWS);
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
    console.log("La imagen: ",question.imagen);
    if(question.imagen != null){
    	questionImage.classList.remove('hide');
    	questionImage.src = "/quizz_star/" + question.imagen
    }
    else{
    	questionImage.classList.add('hide');	
    }
    let contadorr = 0;
    //El foreach de acontinuacion es para mostrar las 4 opciones.
    question.answers.forEach(answer => {
    	//console.log("Showquestion: ",contadorr);
    	contadorr++;
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
    
    const selectedButton = e.target
    
    const correct = selectedButton.dataset.correct
    console.log("pregunta actual: ",currentQuestionIndex);
    if(correct){
    	console.log("En condicion DE SI: ",correct);
    	
    	comunicationWS.send(20,currentQuestionIndex);
    }
    console.log("Que es correct?: ",correct);
    //console.log("selectedButton: ",selectedButton);
    
    setStatusClass(document.body, correct)
    
    Array.from(answerButtonsElement.children).forEach(button =>{
    	
        setStatusClass(button, button.dataset.correct)
    })
    // El siguiente forEach me elimina la opción de darle click a otro boton una
	// vez se presiono sobre uno
    Array.from(answerButtonsElement.children).forEach(button =>{
    	 button.removeEventListener('click', selectAnswer);
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
    
    if(correct){
    	//console.log("Entro en el correct de statusClass",element,"correc: ",correct);
        element.classList.add('correct')
    }
    else {
    	//console.log("En cambio entro en wrong: ",element,"correc: ",correct);
        element.classList.add('wrong')
    }
}

function clearStatusClass(element){
    element.classList.remove('correct')
    element.classList.remove('wrong')
}


