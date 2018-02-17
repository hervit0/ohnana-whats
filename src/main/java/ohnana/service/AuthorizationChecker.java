package ohnana.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthorizationChecker {
    public static String secretBearer;

    @Value("${spring.authorization.bearer}")
    public void setSecretBearer(String bearer) {
        secretBearer = bearer;
    }

    public static Boolean validate(String bearer) {
        return !Objects.equals(secretBearer, bearer);
    }
}
