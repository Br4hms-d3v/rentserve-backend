CREATE TABLE picture_user_materials
(
    id               BIGINT PRIMARY KEY NOT NULL,
    picture_id       BIGINT             NOT NULL,
    user_material_id BIGINT             NOT NULL,
    CONSTRAINT uq_pum UNIQUE (picture_id, user_material_id),
    CONSTRAINT fk_pum_picture FOREIGN KEY (picture_id) REFERENCES pictures (id) ON DELETE CASCADE,
    CONSTRAINT fk_pum_user_material FOREIGN KEY (user_material_id) REFERENCES user_materials (id) ON DELETE CASCADE
);
