DROP TABLE IF EXISTS session CASCADE;
DROP TABLE IF EXISTS player;

CREATE TABLE session (
  session_id BIGSERIAL primary key,
  text varchar(255) NOT NULL
);

CREATE TABLE player (
  player_id BIGSERIAL primary key,
  team INTEGER,
  player_order INTEGER,
  name varchar(255) NOT NULL,
  session_id integer references session(session_id)
);
