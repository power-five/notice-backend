CREATE TABLE IF NOT EXISTS member
(
    id        BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email     VARCHAR(255) NOT NULL UNIQUE,
    nickname  VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    role      VARCHAR(255) NOT NULL,
    image_url TEXT         NOT NULL
);

CREATE TABLE IF NOT EXISTS notice
(
    id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    created_at  TIMESTAMP    NOT NULL,
    writer_id   BIGINT       NOT NULL,
    FOREIGN KEY (writer_id) REFERENCES member (id)
);

CREATE TABLE IF NOT EXISTS image
(
    id        BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    notice_id BIGINT NOT NULL,
    image_url TEXT   NOT NULL,
    FOREIGN KEY (notice_id) REFERENCES notice (id) ON DELETE CASCADE ON UPDATE CASCADE
);
