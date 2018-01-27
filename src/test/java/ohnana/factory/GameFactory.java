package ohnana.factory;

import ohnana.model.Game;

import java.util.ArrayList;

public class GameFactory {
    public static Game createDefault() {
      return Game.builder().build();
    }
}
