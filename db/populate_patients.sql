INSERT INTO `Roles` (`Description`) VALUES ('patient'), ('doctor'), ('staff');

INSERT INTO `person` (`NameLast`, `NameFirst`, `Phone`, `username`, `password`
	, `street`, `City`, `Province`, `PostalCode`, `RoleID`) VALUES
	('Tables', 'Bobby', '519123456', 'btables', 'password', '123 Street Ave', 'Waterloo', 'ON', 'N2L3V9', 1),
	('Acula', 'Steve', '5199874754', 'sacula', 'vampire', '345 Blah Blvd', 'Waterloo', 'ON', 'N2L2T8', 2),
	('OGrady', 'Greg', '1234567890', 'gogrady', 'pass', '456 blah', 'Kitchener', 'ON', 'N2L2R8', 1);

INSERT INTO `doctor` (`PersonID`) VALUES (2);

INSERT INTO `patients` (`PersonID`, `DefaultDoc`, `HealthCard`, `SIN`, `CurrentHealth`) VALUES (3, 1, "27asdf7d8asd", "23421232", "Healthy");
