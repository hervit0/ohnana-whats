package ohnana.factory;

import ohnana.model.Session;

import java.util.UUID;

public class SessionFactory {
    public static Session createDefault() {
      return Session.builder()
              .id(UUID.fromString("18003be5-092d-4f9a-827d-67295d5a9e83"))
              .build();
    }
}
