-- ========================
-- TESTE DE INTEGRIDADE E FUNCIONAMENTO
-- SISTEMA DE GERENCIAMENTO DE CHAVES
-- ========================

-- LIMPAR TABELAS (ordem reversa dos relacionamentos)
DELETE FROM movimentacao_chave;
DELETE FROM agendamento;
DELETE FROM professor_autorizado;
DELETE FROM chave;
DELETE FROM sala;
DELETE FROM predio;
DELETE FROM usuario;
DELETE FROM log_auditoria;

-- ========================
-- 1. INSERIR PRÉDIOS
-- ========================
INSERT INTO predio (nome) VALUES
('Prédio Administrativo'),
('Prédio de Ensino');

-- ========================
-- 2. INSERIR SALAS
-- ========================
INSERT INTO sala (nome, id_predio) VALUES
('Sala 101', 1),
('Sala 201', 2);

-- ========================
-- 3. INSERIR CHAVES
-- ========================
INSERT INTO chave (codigo, id_sala) VALUES
('CH-101', 1),
('CH-201', 2);

-- ========================
-- 4. INSERIR USUÁRIOS
-- ========================
INSERT INTO usuario (nome, email, tipo_usuario) VALUES
('João Admin', 'admin@email.com', 'ADM'),
('Carlos Coord', 'coord@email.com', 'COORDENADOR'),
('Maria Func', 'maria@email.com', 'ADMINISTRATIVO'),
('José Portaria', 'guarita@email.com', 'GUARITA');

-- ========================
-- 5. INSERIR PROFESSORES
-- ========================
-- Prof. Ana supervisionada pelo coordenador (id_usuario = 2)
INSERT INTO professor_autorizado (nome, id_coordenador) VALUES
('Prof. Ana', 2),
('Prof. Bruno', NULL); -- Não supervisionado

-- ========================
-- 6. AGENDAMENTOS VÁLIDOS
-- ========================

-- Coordenador agenda Sala 101 para Prof. Ana (válido)
INSERT INTO agendamento (id_usuario_agendador, id_professor, id_sala, data_inicio, data_fim, periodo)
VALUES (2, 1, 1, '2025-06-05', '2025-06-05', 'MANHÃ');

-- Administrativo agenda Sala 201 para si mesmo (válido)
INSERT INTO agendamento (id_usuario_agendador, id_professor, id_sala, data_inicio, data_fim, periodo)
VALUES (3, NULL, 2, '2025-06-06', '2025-06-06', 'TARDE');

-- ========================
-- 7. AGENDAMENTO INVÁLIDO (Agendamento deve ter beneficiário válido)
-- ========================
-- Descomente para testar:
--SELECT * FROM agendamentos
--WHERE (id_professor IS NULL AND id_pessoa_autorizada IS NULL);

-- ========================
-- 8. AGENDAMENTO INVÁLIDO (coordenador para professor que ele NÃO supervisiona)
-- ========================
-- Esperado: erro, se validação existir via trigger ou na aplicação
-- ATENÇÃO: essa linha pode não dar erro automaticamente se o banco não tiver validações ou triggers para isso
-- Descomente para testar:
-- INSERT INTO agendamento (id_usuario_agendador, id_professor, id_sala, data_inicio, data_fim, periodo)
-- VALUES (2, 2, 2, '2025-06-07', '2025-06-07', 'NOITE');

-- ========================
-- 9. CONFLITO DE AGENDAMENTO
-- ========================
-- Esperado: erro, se controle de conflito estiver implementado
-- Descomente para testar:
-- INSERT INTO agendamento (id_usuario_agendador, id_professor, id_sala, data_inicio, data_fim, periodo)
-- VALUES (3, NULL, 1, '2025-06-05', '2025-06-05', 'MANHÃ');

-- ========================
-- 10. MOVIMENTAÇÃO (CHECK-IN / CHECK-OUT)
-- ========================
-- Check-in da chave CH-101 pelo Prof. Ana
INSERT INTO movimentacao_chave (id_chave, id_usuario_responsavel, id_professor, tipo_movimentacao, data_movimentacao)
VALUES (1, 4, 1, 'ENTREGA', '2025-06-05 07:00:00');

-- Check-out da chave CH-101 pelo Prof. Ana
INSERT INTO movimentacao_chave (id_chave, id_usuario_responsavel, id_professor, tipo_movimentacao, data_movimentacao)
VALUES (1, 4, 1, 'DEVOLUCAO', '2025-06-05 12:00:00');

-- ========================
-- 11. CONSULTAS DE VERIFICAÇÃO
-- ========================

-- Quais agendamentos existem
SELECT * FROM agendamento;

-- Quais chaves foram movimentadas
SELECT * FROM movimentacao_chave;

-- Professores e seus coordenadores
SELECT p.nome AS professor, u.nome AS coordenador
FROM professor_autorizado p
LEFT JOIN usuario u ON p.id_coordenador = u.id_usuario;

-- Conteúdo da auditoria
SELECT * FROM log_auditoria;

-- ========================
-- FIM DO SCRIPT
-- ========================
