package edu.escuelaing.arsw.springboot.app.endpoints;

import java.io.IOException;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
@ServerEndpoint("/qsService")
public class QSEndPoint {

	private int puntaje = 0;
	private int puntaje2 = 0; //Mejorar esto porque se altera muy fácil

	private static final Logger logger = Logger.getLogger(QSEndPoint.class.getName());
	/* Queue for all open WebSocket sessions */
	public static Queue<Session> queue = new ConcurrentLinkedQueue<>();
	
	static ArrayList<String> respuestas = new ArrayList<>();
	
	
	Session ownSession = null;

	/* Call this method to send a message to all clients */
	public void send(String msg,String msg2) {
		System.out.println("ESTA ENTRANDO EN EL SEND DEL SERVIDOR: " + msg);
		if (queue.size() > 1) {
			System.out.println("El size de la lista es mayor a 1");
		}
		try {
			/* Send updates to all open WebSocket sessions */
			System.out.println("longitud de sesiones: " + queue.size());
			for (Session session : queue) {
				//if (!session.equals(this.ownSession)) {
					session.getBasicRemote().sendText(msg+"-"+msg2);
				//}
				logger.log(Level.INFO, "Sent: {0}", msg);
			}
		} catch (IOException e) {
			logger.log(Level.INFO, e.toString());
		}
	}

	@OnMessage
	public void processPoint(String message, Session session) {
		
		try {
			JSONObject mensaje = new JSONObject(message);
			System.out.println("Esta enviando este mensaje desde el server: " + mensaje);
			if(mensaje.get("y").toString().equals("EMPAREJADO")) {
				System.out.println("SI ENTRA PORQUE EMPAREJO BIEN");
				this.send("0","0"); 
			}
			Integer valor = Integer.parseInt(mensaje.get("x").toString());
			puntaje += valor;		
			logger.log(Level.INFO, "Point received:" + message + ". From session: " + session);
			respuestas.add(puntaje+"");
			if(respuestas.size()>1) {
				System.out.println("Checa las respuestas: "+respuestas.toString());
				puntaje2+=valor;
				this.send(respuestas.get(0),puntaje2+"");
				respuestas.clear();
			}
			
		} catch (JSONException e) {
			logger.log(Level.INFO, "Error on JSON:" + message + ". From session: " + session);
			e.printStackTrace();
		}

		
	}

	@OnOpen
	public void openConnection(Session session) {
		/* Register this connection in the queue */
		System.out.println("Entrando en openConection: " + session);
		queue.add(session);
		ownSession = session;
		logger.log(Level.INFO, "Connection opened.");
		try {
			session.getBasicRemote().sendText("Connection established.");
			System.out.println("La lista de sesiones: " + queue);
		} catch (IOException ex) {
			logger.log(Level.SEVERE, null, ex);
		}
	}

	@OnClose
	public void closedConnection(Session session) {
		/* Remove this connection from the queue */
		queue.remove(session);
		logger.log(Level.INFO, "Connection closed.");
		System.out.println("Conexiones activas de momento: "+queue.size());
	}

	@OnError
	public void error(Session session, Throwable t) {
		/* Remove this connection from the queue */
		queue.remove(session);
		logger.log(Level.INFO, t.toString());
		logger.log(Level.INFO, "Connection error.");
	}
}