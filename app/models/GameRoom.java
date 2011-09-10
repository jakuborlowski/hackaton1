package models;

import java.util.*;

import play.libs.*;
import play.libs.F.*;

public class GameRoom {
    
    final ArchivedEventStream<GameRoom.Event> gameEvents = new ArchivedEventStream<GameRoom.Event>(100);
    
    /**
     * For WebSocket, when a user join the room we return a continuous event stream
     * of ChatEvent
     */
    public EventStream<GameRoom.Event> join(String user) {
        gameEvents.publish(new Join(user));
        return gameEvents.eventStream();
    }
    
    /**
     * A user leave the room
     */
    public void leave(String user) {
        gameEvents.publish(new Leave(user));
    }
    
    /**
     * A user say something on the room
     */
    public void say(String user, String text) {
        if(text == null || text.trim().equals("")) {
            return;
        }
        gameEvents.publish(new Message(user, text));
    }
    
    // ~~~~~~~~~ Chat room events

    public static abstract class Event {
        
        final public String type;
        final public Long timestamp;
        
        public Event(String type) {
            this.type = type;
            this.timestamp = System.currentTimeMillis();
        }
        
    }
    
    public static class Join extends Event {
        
        final public String user;
        
        public Join(String user) {
            super("join");
            this.user = user;
        }
        
    }
    
    public static class Leave extends Event {
        
        final public String user;
        
        public Leave(String user) {
            super("leave");
            this.user = user;
        }
        
    }
    
    public static class Message extends Event {
        
        final public String user;
        final public String text;
        
        public Message(String user, String text) {
            super("message");
            this.user = user;
            this.text = text;
        }
        
    }
    
    // ~~~~~~~~~ Chat room factory

    static GameRoom instance = null;
    public static GameRoom get() {
        if(instance == null) {
            instance = new GameRoom();
        }
        return instance;
    }
    
}

