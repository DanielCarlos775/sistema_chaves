DELIMITER $$

CREATE PROCEDURE sp_inserir_agendamento(
    IN p_id_sala INT,
    IN p_id_usuario INT,
    IN p_id_professor INT,
    IN p_id_pessoa INT,
    IN p_data_inicial DATE,
    IN p_data_final DATE,
    IN p_periodo ENUM('MANHA','TARDE','NOITE','INTEGRAL'),
    IN p_observacoes TEXT
)
BEGIN
    INSERT INTO agendamentos (
        id_sala, id_usuario_solicitante, id_professor, id_pessoa_autorizada,
        data_inicial, data_final, periodo, observacoes, status
    ) VALUES (
        p_id_sala, p_id_usuario, p_id_professor, p_id_pessoa,
        p_data_inicial, p_data_final, p_periodo, p_observacoes, 'ATIVO'
    );
END$$

DELIMITER ;
