DELIMITER $$

CREATE TRIGGER trg_agendamento_unico
BEFORE INSERT ON agendamentos
FOR EACH ROW
BEGIN
    DECLARE conflito INT;

    -- Verifica se já existe um agendamento ativo ou concluído na mesma sala, data e período
    SELECT COUNT(*)
    INTO conflito
    FROM agendamentos
    WHERE id_sala = NEW.id_sala
      AND periodo = NEW.periodo
      AND (
            (NEW.data_inicial BETWEEN data_inicial AND data_final)
            OR
            (NEW.data_final BETWEEN data_inicial AND data_final)
            OR
            (data_inicial BETWEEN NEW.data_inicial AND NEW.data_final)
          )
      AND status IN ('ATIVO', 'CONCLUIDO');

    IF conflito > 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Erro: já existe um agendamento para esta sala nesse período e data.';
    END IF;
END$$

DELIMITER ;
