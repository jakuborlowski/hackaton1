package models;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Lists;

public class GameRooms {

    static Map<String, GameRoom> pool = new HashMap<String, GameRoom>();

    public static String generateUUID() {
        try {
            SecureRandom prng = SecureRandom.getInstance("SHA1PRNG");
            // generate a random number
            String randomNum = new Integer(prng.nextInt()).toString();

            // get its digest
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] result = sha.digest(randomNum.getBytes());

            return hexEncode(result);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }

    }

    public static GameRoom get(String roomId) {
        return pool.get(roomId);
    }

    /**
     * The byte[] returned by MessageDigest does not have a nice textual
     * representation, so some form of encoding is usually performed.
     * 
     * This implementation follows the example of David Flanagan's book
     * "Java In A Nutshell", and converts a byte array into a String of hex
     * characters.
     * 
     * Another popular alternative is to use a "Base64" encoding.
     */
    static private String hexEncode(byte[] aInput) {
        StringBuilder result = new StringBuilder();
        char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f' };
        for (int idx = 0; idx < aInput.length; ++idx) {
            byte b = aInput[idx];
            result.append(digits[(b & 0xf0) >> 4]);
            result.append(digits[b & 0x0f]);
        }
        return result.toString();
    }

    public static GameRoom getOrCreate(String roomId) {
        GameRoom instance = pool.get(roomId);
        if (instance == null) {
            instance = new GameRoom(roomId);
            pool.put(roomId, instance);
        }
        return instance;
    }

    public static GameRoom startNewGame(User owner) {
        return getOrCreate(generateUUID());
    }

    public static List<GameRoom> getOpenRooms() {
        return Lists.newArrayList(pool.values());
    }

}
