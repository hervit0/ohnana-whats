package ohnana.persistence.seeder;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import ohnana.model.Card;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Parser {
    private static final String RESOURCE = "static/cards.json";

    public List<Card> getCards() {
        JSONParser parser = new JSONParser();
        List<Card> cardList = new ArrayList<>();

        try {
            String json = Resources.toString(Resources.getResource(RESOURCE), Charsets.UTF_8);
            JSONArray cardsList = (JSONArray) parser.parse(json);

            cardList = (List<Card>) cardsList.parallelStream()
                    .map(Parser::parseCard)
                    .collect(Collectors.toList());
        } catch (IOException | ParseException exception) {
            exception.printStackTrace();
        }

        return cardList;
    }

    private static Object parseCard(Object cardObject) {
        JSONObject card = (JSONObject) cardObject;

        return Card.builder()
                .id(UUID.randomUUID())
                .name((String) card.get("name"))
                .build();
    }
}
