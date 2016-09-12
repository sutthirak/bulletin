CREATE TABLE `topic`
    (`id` BIGINT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `description` TEXT NOT NULL,
    `writer_title` INT NOT NULL,
    `writer_email` VARCHAR(255) NOT NULL,
    `writer_name` VARCHAR(255) NOT NULL,
    `writer_password` VARCHAR(255) NOT NULL,
    `created_date` DATE NOT NULL,
    `number_of_view` BIGINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`id`));

CREATE TABLE `comment`
    (`id` BIGINT NOT NULL AUTO_INCREMENT,
    `topic_id` BIGINT NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `detail` TEXT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (topic_id) REFERENCES topic(id)
    ON DELETE CASCADE);

CREATE UNIQUE INDEX TOPIC_INDEX ON topic (id DESC);
CREATE UNIQUE INDEX COMMENT_INDEX ON comment (id);
