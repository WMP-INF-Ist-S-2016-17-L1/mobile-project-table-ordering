CREATE TABLE `restaurant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `api_id` VARCHAR(64) NOT NULL,
  `name` VARCHAR(256) NULL,
  `city` VARCHAR(256) NULL,
  `street` VARCHAR(256) NULL,
  `owner_id` INT NOT NULL,
  PRIMARY KEY (`id`, `owner_id`),
  UNIQUE INDEX `api_id_UNIQUE` (`api_id` ASC),
  INDEX `fk_restaurant_owner_idx` (`owner_id` ASC),
  CONSTRAINT `fk_restaurant_owner`
    FOREIGN KEY (`owner_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)