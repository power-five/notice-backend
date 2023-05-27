CREATE TABLE member
(
    id        BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email     VARCHAR(255) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    role      VARCHAR(255) NOT NULL,
    image_url TEXT         NOT NULL
);

CREATE TABLE notice
(
    id          BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(255) NOT NULL UNIQUE,
    description TEXT         NOT NULL,
    created_at  DATETIME     NOT NULL,
    writer_id   BIGINT       NOT NULL,
    FOREIGN KEY (writer_id) REFERENCES member (id)
);

CREATE TABLE image
(
    id        BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    notice_id BIGINT NOT NULL,
    image_url TEXT   NOT NULL,
    FOREIGN KEY (notice_id) REFERENCES notice (id) ON DELETE CASCADE ON UPDATE CASCADE
);
