INSERT INTO public.report (id, created_at, deleted_at, updated_at, auto_sending, generate_in_friday, generate_in_monday, generate_in_saturday, generate_in_sunday, generate_in_thursday, generate_in_tuesday, generate_in_wednesday, name, send_after_time, time_of_publication) VALUES (1, '2020-12-05 17:39:59.878000', null, '2020-12-06 22:06:04.082000', true, true, false, true, false, true, false, false, 'Отчет', 28800000, '13:00');



INSERT INTO public.publication (id, created_at, deleted_at, updated_at, auto_sending, publication_date, status, report_id) VALUES (1, '2020-12-05 18:15:27.806000', null, '2020-12-05 18:15:27.839000', true, '2020-12-05 18:14:34.431000', 'PUBLISHED', 1);
INSERT INTO public.publication (id, created_at, deleted_at, updated_at, auto_sending, publication_date, status, report_id) VALUES (2, '2020-12-05 18:15:41.844000', null, '2020-12-05 18:15:41.845000', true, '2020-12-05 18:14:34.431000', 'PUBLISHED', 1);
INSERT INTO public.publication (id, created_at, deleted_at, updated_at, auto_sending, publication_date, status, report_id) VALUES (3, '2020-12-05 18:15:45.491000', null, '2020-12-05 18:15:45.493000', true, '2020-12-05 18:14:34.431000', 'SENT', 1);
INSERT INTO public.publication (id, created_at, deleted_at, updated_at, auto_sending, publication_date, status, report_id) VALUES (4, '2020-12-05 18:15:46.447000', null, '2020-12-05 18:15:46.448000', true, '2020-12-05 18:14:34.431000', 'SENT', 1);
INSERT INTO public.publication (id, created_at, deleted_at, updated_at, auto_sending, publication_date, status, report_id) VALUES (5, '2020-12-06 20:50:55.089000', null, '2020-12-06 20:50:55.136000', true, '2020-12-06 20:49:57.371000', 'PUBLISHED', 1);



INSERT INTO public.material_list (id, created_at, deleted_at, updated_at, material_code, material_link, material_measure, material_name, material_owner, report_id) VALUES (2, '2020-12-05 17:41:31.252000', null, '2020-12-05 17:41:31.254000', '210102010601', 210102010601, 'м3', 'Щебень из плотных горных пород для строительных работ М1000, фракция 5-10 мм СТ РК 1284-2004', 210102010600, 1);
INSERT INTO public.material_list (id, created_at, deleted_at, updated_at, material_code, material_link, material_measure, material_name, material_owner, report_id) VALUES (3, '2020-12-05 17:42:58.543000', null, '2020-12-05 17:42:58.544000', '210201010101', 210201010101, 'м3', 'Бетон тяжелый класса В3,5 ГОСТ 7473-2010', 210201010100, 1);
INSERT INTO public.material_list (id, created_at, deleted_at, updated_at, material_code, material_link, material_measure, material_name, material_owner, report_id) VALUES (4, '2020-12-05 17:44:14.398000', null, '2020-12-05 17:44:14.399000', '211004010603', 211004010603, 'м2', 'Рубероид кровельный с пылевидной посыпкой РКП-350Б ГОСТ 10923-93', 211004010600, 1);
INSERT INTO public.material_list (id, created_at, deleted_at, updated_at, material_code, material_link, material_measure, material_name, material_owner, report_id) VALUES (5, '2020-12-05 17:45:02.666000', null, '2020-12-05 17:45:02.667000', '211302090104', 211302090104, 'кг', 'Гвозди строительные ГОСТ 283-75', 211302090100, 1);
INSERT INTO public.material_list (id, created_at, deleted_at, updated_at, material_code, material_link, material_measure, material_name, material_owner, report_id) VALUES (6, '2020-12-05 17:46:22.706000', null, '2020-12-05 17:46:22.707000', '211301020801', 211301020801, 'т', 'Известь строительная негашеная комовая, сорт 1, ГОСТ 9179-77', 211301020800, 1);
INSERT INTO public.material_list (id, created_at, deleted_at, updated_at, material_code, material_link, material_measure, material_name, material_owner, report_id) VALUES (7, '2020-12-05 17:47:37.222000', null, '2020-12-05 17:47:37.224000', '210102010604', 210102010604, 'м3', 'Щебень из плотных горных пород для строительных работ М1000, фракция 40-70 мм СТ РК 1284-2004', 210102010600, 1);
INSERT INTO public.material_list (id, created_at, deleted_at, updated_at, material_code, material_link, material_measure, material_name, material_owner, report_id) VALUES (8, '2020-12-05 17:49:08.105000', null, '2020-12-05 17:49:08.112000', '211308121035', 211308121035, 'т', 'Электроды, d=4 мм, Э42 ГОСТ 9466-75', 211308121000, 1);
INSERT INTO public.material_list (id, created_at, deleted_at, updated_at, material_code, material_link, material_measure, material_name, material_owner, report_id) VALUES (9, '2020-12-05 17:49:39.113000', null, '2020-12-05 17:49:39.113000', '211307031405', 211307031405, 'м3', 'Вода техническая', 211307031400, 1);
INSERT INTO public.material_list (id, created_at, deleted_at, updated_at, material_code, material_link, material_measure, material_name, material_owner, report_id) VALUES (10, '2020-12-05 17:50:08.172000', null, '2020-12-05 17:50:08.174000', '210702010301', 210702010301, 'м3', 'Бруски обрезные хвойных пород длиной от 4 м до 6,5 м, шириной от 75 мм до 150 мм, толщиной от 40 мм до 75 мм, 3 сорта ГОСТ 8486-86', 210702010300, 1);
INSERT INTO public.material_list (id, created_at, deleted_at, updated_at, material_code, material_link, material_measure, material_name, material_owner, report_id) VALUES (11, '2020-12-05 17:50:35.785000', null, '2020-12-05 17:50:35.788000', '210702030305', 210702030305, 'м3', 'Доски обрезные хвойных пород длиной до 6,5 м, шириной от 75 мм до 150 мм, толщиной 44 мм и более, 3 сорта ГОСТ 8486-86', 210702030300, 1);


INSERT INTO public.category (id, created_at, deleted_at, updated_at, category_name, description) VALUES (2, '2020-12-05 19:14:36.518000', null, '2020-12-05 19:14:36.543000', 'Категория 2', '');
INSERT INTO public.category (id, created_at, deleted_at, updated_at, category_name, description) VALUES (1, '2020-12-05 19:11:41.340000', null, '2020-12-05 19:11:41.368000', 'Категория 1', '');
INSERT INTO public.category (id, created_at, deleted_at, updated_at, category_name, description) VALUES (4, '2020-12-06 22:36:49.309000', null, '2020-12-06 22:36:49.340000', 'Категория 3', 'string');
INSERT INTO public.category (id, created_at, deleted_at, updated_at, category_name, description) VALUES (5, '2020-12-06 22:36:54.120000', null, '2020-12-06 22:36:54.120000', 'Категория 6', 'string');
INSERT INTO public.category (id, created_at, deleted_at, updated_at, category_name, description) VALUES (6, '2020-12-06 22:36:56.977000', null, '2020-12-06 22:36:56.978000', 'Категория 7', 'string');



INSERT INTO public.contractor (id, created_at, deleted_at, updated_at, contractor_name, e_mail, category_id, bin, phone_number) VALUES (1, '2020-12-05 19:32:38.160000', null, '2020-12-05 19:32:38.191000', 'BI Group', 'info@bigroup.kz', 1, null, null);
INSERT INTO public.contractor (id, created_at, deleted_at, updated_at, contractor_name, e_mail, category_id, bin, phone_number) VALUES (2, '2020-12-05 19:34:14.482000', null, '2020-12-05 19:34:14.484000', 'Kaz stroi', 'info@kazstroi.kz', 1, null, null);
INSERT INTO public.contractor (id, created_at, deleted_at, updated_at, contractor_name, e_mail, category_id, bin, phone_number) VALUES (3, '2020-12-05 19:34:48.108000', null, '2020-12-05 19:34:48.109000', 'Novacom', 'info@novacom.ru', 1, null, null);
INSERT INTO public.contractor (id, created_at, deleted_at, updated_at, contractor_name, e_mail, category_id, bin, phone_number) VALUES (4, '2020-12-05 19:36:59.597000', null, '2020-12-05 19:36:59.598000', 'Novacom Corporation', 'info@corporation.ru', 2, null, null);
INSERT INTO public.contractor (id, created_at, deleted_at, updated_at, contractor_name, e_mail, category_id, bin, phone_number) VALUES (5, '2020-12-05 19:37:44.238000', null, '2020-12-05 19:37:44.239000', 'ECO Life', 'info@ecolife.kz', 2, null, null);
INSERT INTO public.contractor (id, created_at, deleted_at, updated_at, contractor_name, e_mail, category_id, bin, phone_number) VALUES (6, '2020-12-05 19:38:53.330000', null, '2020-12-05 19:38:53.332000', 'Region Remservice', 'region@remservice.kz', 2, null, null);



INSERT INTO public.report_category (category_id, report_id) VALUES (1, 1);
