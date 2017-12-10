package ohnana.factory;

import ohnana.model.Session;

import java.util.ArrayList;

public class SessionFactory {
    public static Session createDefault() {
      return Session.builder()
              .players(new ArrayList<>())
              .text("Random text")
              .build();
    }
}
