-- ========================
-- Teste 1: Integridade referencial
-- ========================

-- Tentando excluir um prédio que tenha salas vinculadas:
DELETE FROM predio WHERE id_predio = 1;
-- Resultado esperado: Erro de integridade referencial (restrição de chave estrangeira), impedindo a exclusão.

-- Tentando agendar a mesma sala no mesmo período/dia
INSERT INTO agendamento (id_usuario_responsavel, id_professor, id_chave, data_inicio, data_fim, periodo)
VALUES (3, NULL, 1, '2025-06-03', '2025-06-03', 'MANHA');
-- Resultado esperado: A inserção deve ser bloqueada.


