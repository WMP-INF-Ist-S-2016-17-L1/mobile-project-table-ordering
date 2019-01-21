CREATE TABLE `notification` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `received_user_id` INT(11) NOT NULL,
  `title` VARCHAR(256) NOT NULL,
  `message` VARCHAR(768) NOT NULL,
  `date_and_time` DATETIME NOT NULL,
  `reservation_request_id` INT(11) NOT NULL,
  `displayed` BIT(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_message_user1_idx` (`received_user_id` ASC),
  INDEX `fk_notification_reservation_request1_idx` (`reservation_request_id` ASC),
  CONSTRAINT `fk_event_message_user1`
  FOREIGN KEY (`received_user_id`)
  REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_notification_reservation_request1`
  FOREIGN KEY (`reservation_request_id`)
  REFERENCES `reservation_request` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
