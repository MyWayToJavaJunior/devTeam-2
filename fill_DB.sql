CREATE USER 'dt'@'localhost' IDENTIFIED BY 'dtDT123321';
GRANT ALL PRIVILEGES ON devTeam.* to 'dt'@'localhost' IDENTIFIED BY 'dtDT123321';
FLUSH PRIVILEGES;

use devTeam;

#DELETE FROM Qualification;
#TRUNCATE Qualification;
#TRUNCATE Staff;

INSERT INTO Qualification(title) VALUES ("Junior Java Developer");
INSERT INTO Qualification(title) VALUES ("Middle Java Developer");
INSERT INTO Qualification(title) VALUES ("Manager");

#select * from Qualification;


INSERT INTO  Staff(name, surname, qualification_id, isFree) VALUES ("Vitalii", "Sashyn", 1, 1);
INSERT INTO  Staff(name, surname, qualification_id, isFree) VALUES ("Alex", "Natalenko", 2, "true");
INSERT INTO  Staff(name, surname, qualification_id, isFree) VALUES ("Eugen", "Ostroukhov", 1, "true");
INSERT INTO  Staff(name, surname, qualification_id, isFree) VALUES ("Колисниченко", "Максим", 1, "false");
INSERT INTO  Staff(name, surname, qualification_id, isFree) VALUES ("Sergey", "Sikorskiy", 3, 1);
INSERT INTO  Staff(name, surname, qualification_id, isFree) VALUES ("Marchyk", "Anton", 3, 1);

INSERT INTO Staff_auth(staff_id, email, password) VALUES (6, "ss@gmail.com", 12345);
INSERT INTO Staff_auth(staff_id, email, password) VALUES (7, "mm@gmail.com", 12345);
INSERT INTO Staff_auth(staff_id, email, password) VALUES (1, "sashyn.v@gmail.com", 12345);
INSERT INTO Customer_auth(name, email, password) VALUES ("Google", "google@google", 12345);

###TEST create project, spec, insert man to project
INSERT INTO Specification (title, f_spec, customer_id) VALUES ("Title Test", "This is a long text about specification", 1);
INSERT INTO Project (title, specification_id, bill) VALUES ("Test Project", 1, 1000);
INSERT INTO Project_staff(Project_idProject,  Staff_id_person) VALUES (1, 1);