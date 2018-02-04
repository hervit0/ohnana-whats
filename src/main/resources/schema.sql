DROP TABLE IF EXISTS session CASCADE;
CREATE TABLE session (
  session_id UUID PRIMARY KEY,
  token VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS topic CASCADE;
CREATE TABLE topic (
  topic_id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS card CASCADE;
CREATE TABLE card (
  card_id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  topic_id UUID REFERENCES topic(topic_id)
);

DROP TABLE IF EXISTS game CASCADE;
CREATE TABLE game (
    game_id UUID PRIMARY KEY,
    session_id UUID REFERENCES session (session_id)
);

DROP TABLE IF EXISTS game_cards CASCADE;
CREATE TABLE game_cards (
    game_id UUID NOT NULL REFERENCES game (game_id),
    card_id UUID NOT NULL REFERENCES card (card_id),
    PRIMARY KEY (game_id, card_id)
);

INSERT INTO session (session_id, token) VALUES
    ('18003be5-092d-4f9a-827d-67295d5a9e83', 'default_token');
INSERT INTO card (card_id, name) VALUES
    ('BB023C20-527B-4174-A54A-F27A73EB94D2', 'default_card');
INSERT INTO game (game_id, session_id) VALUES
    ('4F7929A9-7D73-4914-A631-365A5B4A900F', '18003be5-092d-4f9a-827d-67295d5a9e83');
INSERT INTO game_cards (game_id, card_id) VALUES
    ('4F7929A9-7D73-4914-A631-365A5B4A900F', 'BB023C20-527B-4174-A54A-F27A73EB94D2');

DROP TABLE IF EXISTS player;
-- CREATE TABLE player (
--  player_id BIGSERIAL primary key,
--  team INTEGER,
--  player_order INTEGER,
--  name varchar(255) NOT NULL,
--  session_id integer references session(session_id)
-- );
