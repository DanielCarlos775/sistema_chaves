DELIMITER $$

CREATE TRIGGER before_movimentacoes_insert
BEFORE INSERT ON movimentacoes
FOR EACH ROW
BEGIN
    DECLARE ultima_mov ENUM('CHECK-IN','CHECK-OUT');

    SELECT tipo_movimentacao
    INTO ultima_mov
    FROM movimentacoes
    WHERE id_chave = NEW.id_chave
    ORDER BY data_hora DESC
    LIMIT 1;

    IF (NEW.tipo_movimentacao = 'CHECK-OUT' AND ultima_mov = 'CHECK-OUT') THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Chave já devolvida, não pode fazer outro CHECK-OUT';
    ELSEIF (NEW.tipo_movimentacao = 'CHECK-IN' AND (ultima_mov IS NULL OR ultima_mov = 'CHECK-IN')) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Chave está fora, não pode fazer CHECK-IN';
    END IF;
END$$

DELIMITER ;
