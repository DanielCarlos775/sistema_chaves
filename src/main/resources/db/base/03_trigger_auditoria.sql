DELIMITER $$

CREATE TRIGGER before_usuario_update
BEFORE UPDATE ON usuarios
FOR EACH ROW
BEGIN
    INSERT INTO logs_sistema (id_usuario, acao, tabela_afetada, id_registro, dados_anteriores, dados_novos)
    VALUES (
        OLD.id_usuario,
        'UPDATE',
        'usuarios',
        OLD.id_usuario,
        JSON_OBJECT('nome', OLD.nome, 'email', OLD.email, 'tipo_usuario', OLD.tipo_usuario),
        JSON_OBJECT('nome', NEW.nome, 'email', NEW.email, 'tipo_usuario', NEW.tipo_usuario)
    );
END$$

DELIMITER ;
