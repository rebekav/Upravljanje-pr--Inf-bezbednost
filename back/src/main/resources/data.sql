

INSERT INTO `klinika` (`naziv`,`adresa`,`opis`) VALUES
('Ortopetska ordinacija','Neka Adresa 18, Novi Sad','Opis ortopetske ordinacije...');

INSERT INTO `user` (`email`, `identifikator`, `ime`, `prezime` ,`pass`, `validiran`, `klinika_id`, `expire`) VALUES
('todorovicveljko1@gmail.com','admin','admin','admin','$2a$10$oYu.Hb2m/9FVynFMBkUCge1H2AIVjaRfuKBAEA4b.p1AwcTZCC0E.',1,null, '2021-09-1'),
('sestra@gmail.com','sestra','Kristina','Pajovic','$2a$10$oYu.Hb2m/9FVynFMBkUCge1H2AIVjaRfuKBAEA4b.p1AwcTZCC0E.',1,1, null),
('pacijent@gmail.com','pacijent','Sima','Simic','$2a$10$oYu.Hb2m/9FVynFMBkUCge1H2AIVjaRfuKBAEA4b.p1AwcTZCC0E.',1,1, null),
('lekar@gmail.com','lekar','Aleksa','Aleksic','$2a$10$oYu.Hb2m/9FVynFMBkUCge1H2AIVjaRfuKBAEA4b.p1AwcTZCC0E.',1,1, null),
('pera@gmail.com','pera','Pera','Peric','$2a$10$oYu.Hb2m/9FVynFMBkUCge1H2AIVjaRfuKBAEA4b.p1AwcTZCC0E.',1,1, null),
('ana@gmail.com','ana','Ana','Jovanovic','$2a$10$oYu.Hb2m/9FVynFMBkUCge1H2AIVjaRfuKBAEA4b.p1AwcTZCC0E.',1,1, null);

INSERT INTO `role` (`naziv`) VALUES
('SUPER_ADMIN'),
('KLINIKA_ADMIN'),
('LEKAR'),
('MEDICINSKA_SESTRA'),
('PACIJENT');

INSERT INTO `role_has_user`(`user_id`, `role_id`) VALUES
(1,1),(2,4),(3,5),(4,3),(5,5),(6,5);


INSERT INTO `usluga` (`naziv`,`cena`,`klinika_id`) VALUES
('Stavljanje gipsa',2500,1), ('Rengenski snimak',1000,1);


INSERT INTO `pregled` (`podaci_o_pregledu`,`vreme`,`trajanje`,`lekar`,`meidicnska_sestra`,`pacijent`,`usluga_id`,`popust`) VALUES
('Neki opis pregleda','2021-09-10 12:00:00.000000',20,4,2,3,1,0),
('','2021-09-27 13:00:00.000000',25,4,2,5,1,1000),
('','2021-09-28 9:00:00.000000',10,4,2,6,2,0),
('','2021-09-29 17:00:00.000000',10,4,2,3,2,0);

INSERT INTO `recept` (`overen`,`naziv`,`napomena`,`pregled_id`) VALUES
(true,'naziv recepta','Neki lek 2x1',1);


INSERT INTO `zdravstveni_karton` (`pregled_id`) VALUES
(1);