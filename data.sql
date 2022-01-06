CREATE TABLE IF NOT EXISTS bot (
    id uuid NOT NULL,
    name VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS message (
    id uuid NOT NULL,
    conversation_id uuid NOT NULL,
    date_conversation DATE NOT NULL,
    message_to uuid NOT NULL,
    message_from uuid NOT NULL,
    text VARCHAR NOT NULL,

    PRIMARY KEY (id)
);
