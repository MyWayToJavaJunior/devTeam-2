use devTeam;


#show tables;

#select * from Staff;
TRUNCATE Qualification;
TRUNCATE Staff;

INSERT INTO Qualification(title) VALUES ("Junior Java Developer");
INSERT INTO Qualification(title) VALUES ("Middle Java Developer");
INSERT INTO Qualification(title) VALUES ("Manager");

select * from Qualification;


INSERT INTO  Staff(name, surname, qualification_id, isFree) VALUES ("Vitalii", "Sashyn", 1, "true");
INSERT INTO  Staff(name, surname, qualification_id, isFree) VALUES ("Alex", "Natalenko", 2, "true");
INSERT INTO  Staff(name, surname, qualification_id, isFree) VALUES ("Eugen", "Ostroukhov", 1, "true");
INSERT INTO  Staff(name, surname, qualification_id, isFree) VALUES ("Колисниченко", "Максим", 1, "false");

INSERT INTO Staff_auth(staff_id, email, password) VALUES (1, "sashyn.v@gmail.com", 12345);
INSERT INTO Customer_auth(name, email, password) VALUES ("Google", "google@google", 12345);


select * from Staff;
