-- Listar prédios e salas
SELECT p.nome AS predio, s.nome AS sala
FROM predios p
JOIN salas s ON p.id_predio = s.id_predio;

-- Listar professores e seus coordenadores
SELECT pr.nome AS professor, u.nome AS coordenador
FROM professores pr
JOIN coordenador_professor cp ON pr.id_professor = cp.id_professor
JOIN usuarios u ON cp.id_coordenador = u.id_usuario;

-- Verificar se as chaves estão associadas corretamente às salas
SELECT s.nome, c.codigo
FROM salas s
JOIN chaves c ON s.id_sala = c.id_sala;
