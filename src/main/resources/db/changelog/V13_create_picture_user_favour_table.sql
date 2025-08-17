CREATE TABLE picture_user_favour
(
    id             BIGINT PRIMARY KEY NOT NULL,
    picture_id     BIGINT             NOT NULL,
    user_favor_id BIGINT             NOT NULL,
    CONSTRAINT uq_puf UNIQUE (picture_id, user_favor_id),
    CONSTRAINT fk_puf_picture FOREIGN KEY (picture_id) REFERENCES pictures (id) ON DELETE CASCADE,
    CONSTRAINT fk_puf_user_favour FOREIGN KEY (user_favor_id) REFERENCES user_favour (id) ON DELETE CASCADE
);
