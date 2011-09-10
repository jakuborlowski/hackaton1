package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.Required;
import play.libs.*;
import play.libs.F.*;
import play.mvc.Http.*;

import static play.libs.F.*;
import static play.libs.F.Matcher.*;
import static play.mvc.Http.WebSocketEvent.*;

import java.util.*;

import models.*;

public class Game extends Controller {
    
	public static User user;
	
	@Before
	public static void setup()
	{
		if (!session.contains("uid")) {
			UserActions.login();
		}
		
		user = User.find("byEmail", session.get("uid")).first();
		if (user == null) {
			UserActions.login();
		}
		
		renderArgs.put("user", user);
	}
	
    public static void play(@Required String roomId) {
        render(roomId);
    }
	
	public static void list() {		
		render();
	}

    public static class GameSocket extends WebSocketController {
        
        public static void join(String user, String roomId) {
            
            GameRoom room = GameRoom.get(roomId);
            
            // Socket connected, join the game room
            EventStream<GameRoom.Event> roomMessagesStream = room.join(user);
         
            // Loop while the socket is open
            while(inbound.isOpen()) {
                
                // Wait for an event (either something coming on the inbound socket channel, or GameRoom messages)
                Either<WebSocketEvent,GameRoom.Event> e = await(Promise.waitEither(
                    inbound.nextEvent(), 
                    roomMessagesStream.nextEvent()
                ));
                
                // Case: User typed 'quit'
                for(String userMessage: TextFrame.and(Equals("quit")).match(e._1)) {
                    room.leave(user);
                    outbound.send("quit:ok");
                    disconnect();
                }
                
                // Case: TextEvent received on the socket
                for(String userMessage: TextFrame.match(e._1)) {
                    room.say(user, userMessage);
                }
                
                // Case: Someone joined the room
                for(GameRoom.Join joined: ClassOf(GameRoom.Join.class).match(e._2)) {
                    outbound.send("join:%s", joined.user);
                }
                
                // Case: New message on the chat room
                for(GameRoom.Message message: ClassOf(GameRoom.Message.class).match(e._2)) {
                    outbound.send("message:%s:%s", message.user, message.text);
                }
                
                // Case: Someone left the room
                for(GameRoom.Leave left: ClassOf(GameRoom.Leave.class).match(e._2)) {
                    outbound.send("leave:%s", left.user);
                }
                
                // Case: The socket has been closed
                for(WebSocketClose closed: SocketClosed.match(e._1)) {
                    room.leave(user);
                    disconnect();
                }
                
            }
            
        }
        
    }
    
}

