function BBServiceURL() {
    return 'ws://localhost:8080/qsService';
}

class WSBBChannel {
    constructor(URL, callback) {
        this.URL = URL;
        console.log("Este es el url del auxiliarchanel: ",URL);
        this.wsocket = new WebSocket(URL);
        this.wsocket.onopen = (evt) => this.onOpen(evt);
        this.wsocket.onmessage = (evt) => this.onMessage(evt);
        this.wsocket.onerror = (evt) => this.onError(evt);
        this.receivef = callback;        
    }


    onOpen(evt) {
        console.log("In onOpen", evt);        
        //this.send("0", "EMPAREJADO");
    }
    onMessage(evt) {
        console.log("In onMessage", evt);
        // Este if permite que el primer mensaje del servidor no se tenga en
		// cuenta.
        // El primer mensaje solo confirma que se estableció la conexión.
        // De ahí en adelante intercambiaremos solo puntos(x,y) con el servidor
        
        if(evt.data != "Connection established.") {
        	console.log("Esta entrando en diferente de conection established");
        	console.log("Server: ",evt.data);
        	location.replace("play/1");
        	//let mensaje = evt.data.split("-");        	
            this.receivef(evt.data);
            
            
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

let comunicationWS;

function startGame(){
	comunicationWS =
        new WSBBChannel(BBServiceURL(),
                (msg) => {
            console.log("En websocket: ", msg);
        });
	console.log("Esto es el wschanelAUXILIAR: ",comunicationWS);
    console.log('Iniciando');
    //startButton.classList.add('hide');
    //shuffleQuestions = questions.sort(() => Math.random() - .5);
    //currentQuestionIndex = 0;
    //questionContainerElement.classList.remove('hide');
    //setNextQuestion(false);
}

window.onload = startGame;