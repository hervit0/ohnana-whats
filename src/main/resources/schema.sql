DROP TABLE IF EXISTS game_cards CASCADE;

DROP TABLE IF EXISTS topic CASCADE;
DROP TABLE IF EXISTS card CASCADE;
DROP TABLE IF EXISTS session CASCADE;
DROP TABLE IF EXISTS game CASCADE;
DROP TABLE IF EXISTS player;

CREATE TABLE topic (
  topic_id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL
);

CREATE TABLE card (
  card_id UUID PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  topic_id UUID references topic(topic_id)
);

CREATE TABLE session (
  session_id UUID PRIMARY KEY,
  token VARCHAR(255) NOT NULL
);

INSERT INTO session VALUES('18003be5-092d-4f9a-827d-67295d5a9e83', 'dat token mate');

CREATE TABLE game (
    game_id UUID PRIMARY KEY,
    session_id UUID REFERENCES session (session_id)
);

CREATE TABLE game_cards (
    game_id UUID REFERENCES game (game_id),
    card_id UUID REFERENCES card (card_id)
);

-- CREATE TABLE player (
--  player_id BIGSERIAL primary key,
--  team INTEGER,
--  player_order INTEGER,
--  name varchar(255) NOT NULL,
--  session_id integer references session(session_id)
-- );
