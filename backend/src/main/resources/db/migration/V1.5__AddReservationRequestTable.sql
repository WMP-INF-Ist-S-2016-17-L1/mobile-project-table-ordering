CREATE TABLE `reservation_request` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `number_of_persons` INT(11) NOT NULL DEFAULT '0',
  `reservation_date_time` DATETIME NOT NULL,
  `message` VARCHAR(512) NULL DEFAULT NULL,
  `status` VARCHAR(255) NULL DEFAULT NULL,
  `restaurant_id` INT(11) NOT NULL,
  `client_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_reservation_request_restaurant1_idx` (`restaurant_id` ASC),
  INDEX `fk_reservation_request_user1_idx` (`client_id` ASC),
  CONSTRAINT `fk_reservation_request_restaurant1`
  FOREIGN KEY (`restaurant_id`)
  REFERENCES `restaurant` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_reservation_request_user1`
  FOREIGN KEY (`client_id`)
  REFERENCES `user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
