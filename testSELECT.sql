use devTeam;

#INSERT INTO Staff_auth(staff_id, email, password) VALUES (1, "sashyn.v@gmail.com", 12345);
#INSERT INTO Customer_auth(name, email, password) VALUES ("Google", "google@google", 12345);
#SELECT S.id_person FROM Staff as S, Staff_auth as Sa WHERE S.id_person=Sa.staff_id and Sa.email="sashyn.v@gmail.com";
#SELECT Q.title FROM  Qualification as Q, Staff as S  WHERE S.qualification_id=Q.idQualification AND S.id_person="1";
#SELECT S.id_person FROM Staff as S, Staff_auth as Sa WHERE S.id_person=Sa.staff_id and Sa.email="sashyn.v@gmail.com";
SELECT S.id_person, S.name, S.surname, S.qualification_id, S.isFree FROM Staff as S, Staff_auth as Sa WHERE S.id_person = Sa.staff_id  and Sa.email="sashyn.v@gmail.com";