ALTER TABLE `reservation_request`
	CHANGE COLUMN `message` `client_message` VARCHAR(512) NULL,
	ADD COLUMN `restaurateur_message` VARCHAR(512) NULL;