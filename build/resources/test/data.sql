INSERT INTO public.type_id(id, type) VALUES (1, 'C.C');
INSERT INTO public.type_id(id, type) VALUES (2, 'T.I');
INSERT INTO public.type_id(id, type) VALUES (3, 'NIT');


INSERT INTO public.users(identification, cellphone, full_name, password, type_identification)
	VALUES ('123456789', '54676468', 'Nomble 1', 'w6Unpo<code>t0d0', 1);

INSERT INTO public.users(identification, cellphone, full_name, password, type_identification)
	VALUES ('34646768', '345679098', 'Nomble 2', 'w1Unpo<code>t0d0', 2);




INSERT INTO public.contacts(id, description, latitud, longitud, name_contact, number_phone, identification, distancia)
    VALUES(30, 'Descripción', 12.2345, 54.234, 'Name', '123456789', '123456789', 200.0);

INSERT INTO public.contacts(id, description, latitud, longitud, name_contact, number_phone, identification, distancia)
    VALUES(60, 'Descripción', 12.2345, 54.234, 'Name Dos', '8765567', '123456789', 300.0);

INSERT INTO public.contacts(id, description, latitud, longitud, name_contact, number_phone, identification, distancia)
    VALUES(90, 'Descripción', 12.2345, 54.234, 'Name Tres', '987654', '123456789', 0.0);

INSERT INTO public.contacts(id, description, latitud, longitud, name_contact, number_phone, identification, distancia)
    VALUES(120, 'Descripción', 6.149138, -75.376924, 'Name Cuantro', '9876543000', '123456789', 0.0);

