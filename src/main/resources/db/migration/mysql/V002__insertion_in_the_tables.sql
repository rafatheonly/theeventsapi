INSERT INTO usuario (id, nome, rg, email, senha, foto, perfil, ativo) 
VALUES (1, "Rafael Calearo", "1091507911", "rafaelcalearo@theevents.com", '$2a$10$8Pc1KGsm3tR1x9vHpolQLuk8iGaNUzvNoubnt1mA7x4OPabXipaOW', "-", "ROLE_ADMIN", "1");
INSERT INTO usuario (id, nome, rg, email, senha, foto, perfil, ativo) 
VALUES (2, "michel Luz", "1091507922", "michelluz@theevents.com", '$2a$10$8Pc1KGsm3tR1x9vHpolQLuk8iGaNUzvNoubnt1mA7x4OPabXipaOW', "-", "ROLE_ADMIN", "1");

INSERT INTO tipoevento (id, descricao_tipo_evento, usuario_id) VALUES (1, "Festa Junina", 1);
INSERT INTO tipoevento (id, descricao_tipo_evento, usuario_id) VALUES (2, "Festa Country", 1);

INSERT INTO evento (id, usuario_id, tipoevento_id, titulo, data_criacao, descricao, local, foto, ativo) 
VALUES (1, 1, 1, "Array do Gugu", "30 mar 2019", "Bla, Bla, Bla, e pipoca.", "Teatro Guarani", "-", "0");
INSERT INTO evento (id, usuario_id, tipoevento_id, titulo, data_criacao, descricao, local, foto, ativo) 
VALUES (2, 1, 2, "Barreto in Fest", "30 nov 2018", "Bla, Bla, Bla, e vaca.", "AV. Bento Gonçalves", "-", "0");