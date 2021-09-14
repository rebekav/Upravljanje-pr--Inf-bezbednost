

INSERT INTO `klinika` (`naziv`,`adresa`,`opis`) VALUES
('Sveti vid','Vidojeviceva 18, Novi Sad','Ocna klinika...');

INSERT INTO `user` (`email`, `identifikator`, `ime`, `prezime` ,`pass`, `validiran`) VALUES
('admin@gmail.com','admin','admin','admin','$2a$10$oYu.Hb2m/9FVynFMBkUCge1H2AIVjaRfuKBAEA4b.p1AwcTZCC0E.',1);

INSERT INTO `role` (`naziv`) VALUES
('SUPER_ADMIN'),
('KLINIKA_ADMIN'),
('LEKAR'),
('MEDICINSKA_SESTRA'),
('PACIJENT');

INSERT INTO `role_has_user`(`user_id`, `role_id`) VALUES
(1,1);