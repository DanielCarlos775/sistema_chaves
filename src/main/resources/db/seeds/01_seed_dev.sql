USE sistema_chaves;

-- prédios
INSERT INTO predios (nome, descricao) VALUES
('Bloco A', 'Prédio principal de aulas'),
('Bloco B', 'Laboratórios de informática'),
('Administração', 'Prédio administrativo central');

-- salas
INSERT INTO salas (id_predio, nome, descricao, capacidade) VALUES
(1, '101', 'Sala de aula 101', 40),
(1, '102', 'Sala de aula 102', 35),
(2, 'Lab1', 'Laboratório de Informática 1', 25);

-- usuários
INSERT INTO usuarios (nome, email, senha, tipo_usuario, id_predio)
VALUES
('Administrador Geral', 'admin@sistema.com', 'hash_senha', 'ADM', 3),
('Coordenador João', 'joao@sistema.com', 'hash_senha', 'COORDENADOR', 1),
('Secretária Maria', 'maria@sistema.com', 'hash_senha', 'ADMINISTRATIVO', 1),
('Porteiro José', 'jose@sistema.com', 'hash_senha', 'GUARITA', 3);

-- professores
INSERT INTO professores (nome, email, cpf) VALUES
('Carlos Silva', 'carlos@escola.com', '111.111.111-11'),
('Ana Souza', 'ana@escola.com', '222.222.222-22');

-- coordenador x professor
INSERT INTO coordenador_professor (id_coordenador, id_professor) VALUES
(2, 1), (2, 2);

-- pessoas autorizadas
INSERT INTO pessoas_autorizadas (id_usuario_responsavel, nome, cpf, telefone) VALUES
(3, 'Pedro Lima', '333.333.333-33', '99999-9999');

-- chaves
INSERT INTO chaves (id_sala, codigo, descricao) VALUES
(1, 'CH001', 'Chave da sala 101'),
(2, 'CH002', 'Chave da sala 102'),
(3, 'CH003', 'Chave do Lab1');

-- Coordenador João agenda a sala 101 para Professor Carlos Silva
INSERT INTO agendamentos (id_usuario_solicitante, id_professor, id_sala, data_inicial, data_final, periodo) VALUES
(2, 1, 1, '2025-06-03', '2025-06-03', 'MANHA');

-- Maria (administrativo) agenda a sala 102 para ela mesma
INSERT INTO agendamentos (id_usuario_solicitante, id_professor, id_sala, data_inicial, data_final, periodo) VALUES
(3, NULL, 2, '2025-06-03', '2025-06-03', 'TARDE');
