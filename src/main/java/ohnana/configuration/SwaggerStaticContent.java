package ohnana.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "swagger")
public class SwaggerStaticContent {
//    Player model
    public static final String PLAYER_ID = "id of the player";
    public static final String PLAYER_NAME = "name of the player";
    public static final String PLAYER_ORDER = "order of the player";
    public static final String PLAYER_TEAM = "team of the player";

//    Session model
    public static final String SESSION_ID = "id of the session";
    public static final String SESSION_GAMES = "games belonging to the session";

//    Game model
    public static final String GAME_ID = "id of the game";
    public static final String GAME_SESSION = "session where the game belongs to";

//    Card model
    public static final String CARD_ID = "id of the card";
    public static final String CARD_NAME = "content of the card";
}
