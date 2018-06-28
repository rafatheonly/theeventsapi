INSERT INTO usuario (id, nome, rg, email, senha, foto, perfil, ativo) 
VALUES (1, "Rafael Calearo", "1091507911", "rafaelcalearo@theevents.com", '$2a$10$8Pc1KGsm3tR1x9vHpolQLuk8iGaNUzvNoubnt1mA7x4OPabXipaOW', "-", "ROLE_ADMIN", "1");
INSERT INTO usuario (id, nome, rg, email, senha, foto, perfil, ativo) 
VALUES (2, "michel Luz", "1091507922", "michelluz@theevents.com", '$2a$10$8Pc1KGsm3tR1x9vHpolQLuk8iGaNUzvNoubnt1mA7x4OPabXipaOW', "-", "ROLE_ADMIN", "1");

INSERT INTO tipoevento (id, descricao_tipo_evento, usuario_id) VALUES (1, "Festa Junina", 1);
INSERT INTO tipoevento (id, descricao_tipo_evento, usuario_id) VALUES (2, "Festa Country", 1);

INSERT INTO evento (id, usuario_id, tipoevento_id, titulo, data_criacao, descricao, local, foto, ativo) 
VALUES (1, 1, 1, "Encontro de carros", "30 mar 2019", "Burn her! How do you know she is a witch? You don't frighten us, English pig-dogs! Go and boil yo...", "AV. Bento Gonçalves", "1.jpg", "1");
INSERT INTO evento (id, usuario_id, tipoevento_id, titulo, data_criacao, descricao, local, foto, ativo) 
VALUES (2, 1, 2, "Festa de rua", "26 nov 2018", "Burn her! How do you know she is a witch? You don't frighten us, English pig-dogs! Go and boil yo...", "Praça 20 de Setembro", "2.jpg", "1");
INSERT INTO evento (id, usuario_id, tipoevento_id, titulo, data_criacao, descricao, local, foto, ativo) 
VALUES (3, 2, 2, "Passeio em Veneza", "02 dez 2018", "Burn her! How do you know she is a witch? You don't frighten us, English pig-dogs! Go and boil yo...", "Praça de São Marcos", "3.jpg", "1");
INSERT INTO evento (id, usuario_id, tipoevento_id, titulo, data_criacao, descricao, local, foto, ativo) 
VALUES (4, 1, 1, "São João na rua", "01 nov 2019", "Burn her! How do you know she is a witch? You don't frighten us, English pig-dogs! Go and boil yo...", "Praia do Laranjal", "4.jpg", "1");
INSERT INTO evento (id, usuario_id, tipoevento_id, titulo, data_criacao, descricao, local, foto, ativo) 
VALUES (5, 2, 2, "Parque Tupy", "01 fev 2019", "Burn her! How do you know she is a witch? You don't frighten us, English pig-dogs! Go and boil yo...", "AV. Bento Gonçalves", "5.jpg", "1");
INSERT INTO evento (id, usuario_id, tipoevento_id, titulo, data_criacao, descricao, local, foto, ativo) 
VALUES (6, 1, 1, "Luau na ponte", "09 mar 2019", "Burn her! How do you know she is a witch? You don't frighten us, English pig-dogs! Go and boil yo...", "Ponte velha", "6.jpg", "1");
INSERT INTO evento (id, usuario_id, tipoevento_id, titulo, data_criacao, descricao, local, foto, ativo) 
VALUES (7, 2, 2, "Festival de balões", "09 abr 2019", "Burn her! How do you know she is a witch? You don't frighten us, English pig-dogs! Go and boil yo...", "Ponte velha", "7.jpg", "1");
INSERT INTO evento (id, usuario_id, tipoevento_id, titulo, data_criacao, descricao, local, foto, ativo) 
VALUES (8, 1, 1, "Encontro dos raios", "09 abr 2020", "Burn her! How do you know she is a witch? You don't frighten us, English pig-dogs! Go and boil yo...", "Na rua", "8.jpg", "1");

INSERT INTO convidado (id, nome, rg, evento_id, usuario_id) 
VALUES (1, "Pedro de Lara", "1091547899", 1, 1);
INSERT INTO convidado (id, nome, rg, evento_id, usuario_id) 
VALUES (2, "Moacir Amaral", "3091511199", 2, 2);
INSERT INTO convidado (id, nome, rg, evento_id, usuario_id) 
VALUES (3, "Fabio Breno", "4091547499", 1, 1);