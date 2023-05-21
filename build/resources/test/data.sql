INSERT INTO public.type_id(id, type) VALUES (1, 'C.C');
INSERT INTO public.type_id(id, type) VALUES (2, 'T.I');
INSERT INTO public.type_id(id, type) VALUES (3, 'NIT');


INSERT INTO public.users(identification, cellphone, full_name, password, type_identification, email)
	VALUES ('123456789', '54676468', 'Nomble 1', '79657270d012e8057fd8e25a04a60b20a02867d22d4954aee0734e1e8617f7b5', 1, '@algo.com');

INSERT INTO public.users(identification, cellphone, full_name, password, type_identification, email)
	VALUES ('34646768', '345679098', 'Nomble 2', 'w1Unpo<code>t0d0', 2, '@new.com');

INSERT INTO public.users(identification, cellphone, full_name, password, type_identification, email)
	VALUES ('123', '123', 'Nomble 2', '79657270d012e8057fd8e25a04a60b20a02867d22d4954aee0734e1e8617f7b5', 2, 'p@new.com');

INSERT INTO public.rol_users(id, rol, id_rol_user)
    VALUES (100, 'Peaton', '123');

INSERT INTO public.report_users(id, date_report, description, latitud, longitud, url, identification)
	VALUES (20, '2023-05-14T10:30', 'Description 1', 80.0, 45.2, 'url', '123456789');

INSERT INTO public.report_users(id, date_report, description, latitud, longitud, url, identification)
	VALUES (50, '2023-05-30T10:30', 'Description 2', 100.0, 90.0, 'url', '123456789');


INSERT INTO public.contacts(id, description, latitud, longitud, name_contact, number_phone, identification, distancia)
    VALUES(30, 'Descripción', 12.2345, 54.234, 'Name', '123456789', '123456789', 200.0);

INSERT INTO public.contacts(id, description, latitud, longitud, name_contact, number_phone, identification, distancia)
    VALUES(60, 'Descripción', 12.2345, 54.234, 'Name Dos', '8765567', '123456789', 300.0);

INSERT INTO public.contacts(id, description, latitud, longitud, name_contact, number_phone, identification, distancia)
    VALUES(90, 'Descripción', 12.2345, 54.234, 'Name Tres', '987654', '123456789', 0.0);

INSERT INTO public.contacts(id, description, latitud, longitud, name_contact, number_phone, identification, distancia)
    VALUES(120, 'Descripción', 6.149138, -75.376924, 'Name Cuantro', '9876543000', '123456789', 0.0);


INSERT INTO public.rol_users(id, rol, id_rol_user)
    VALUES (-1, 'Peaton', '123456789');

