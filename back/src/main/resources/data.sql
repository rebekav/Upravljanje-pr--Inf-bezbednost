

INSERT INTO `klinika` (`naziv`,`adresa`,`opis`) VALUES
('Sveti vid','Vidojeviceva 18, Novi Sad','Ocna klinika...');

INSERT INTO `user` (`email`, `identifikator`, `ime`, `prezime` ,`pass`, `validiran`, `klinika_id`) VALUES
('admin@gmail.com','admin','admin','admin','$2a$10$oYu.Hb2m/9FVynFMBkUCge1H2AIVjaRfuKBAEA4b.p1AwcTZCC0E.',1,null),
('sestra@gmail.com','sestra','sestra','sestra','$2a$10$oYu.Hb2m/9FVynFMBkUCge1H2AIVjaRfuKBAEA4b.p1AwcTZCC0E.',1,1),
('pacijent@gmail.com','pacijent','pacijent','pacijent','$2a$10$oYu.Hb2m/9FVynFMBkUCge1H2AIVjaRfuKBAEA4b.p1AwcTZCC0E.',1,1),
('lekar@gmail.com','lekar','lekar','lekar','$2a$10$oYu.Hb2m/9FVynFMBkUCge1H2AIVjaRfuKBAEA4b.p1AwcTZCC0E.',1,1),
('pera@gmail.com','pera','Pera','Peric','$2a$10$oYu.Hb2m/9FVynFMBkUCge1H2AIVjaRfuKBAEA4b.p1AwcTZCC0E.',1,1);

INSERT INTO `role` (`naziv`) VALUES
('SUPER_ADMIN'),
('KLINIKA_ADMIN'),
('LEKAR'),
('MEDICINSKA_SESTRA'),
('PACIJENT');

INSERT INTO `role_has_user`(`user_id`, `role_id`) VALUES
(1,1),(2,4),(3,5),(4,3),(5,5);


INSERT INTO `usluga` (`naziv`,`cena`,`klinika_id`) VALUES
('Ocni pregled',500,1);


INSERT INTO `pregled` (`podaci_o_pregledu`,`vreme`,`trajanje`,`lekar`,`meidicnska_sestra`,`pacijent`,`usluga_id`,`popust`) VALUES
('Neki opis pregleda','2021-09-10 12:00:00.000000',20,4,2,3,1,0),
('Neki opis pregleda','2021-09-10 13:00:00.000000',25,4,2,5,1,10);

INSERT INTO `recept` (`overen`,`naziv`,`pregled_id`) VALUES
(false,'naziv recepta',1);


INSERT INTO `zdravstveni_karton` (`overen`,`terapija`,`pregled_id`) VALUES
(true,'terapija neka',1);