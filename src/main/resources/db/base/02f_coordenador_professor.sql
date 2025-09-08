CREATE TABLE IF NOT EXISTS coordenador_professor (
    id_coordenador_professor INT AUTO_INCREMENT PRIMARY KEY,
    id_coordenador INT NOT NULL COMMENT 'ID do coordenador (usuário)',
    id_professor INT NOT NULL COMMENT 'ID do professor vinculado',
    data_vinculacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Data da vinculação',
    ativo TINYINT(1) NOT NULL DEFAULT 1 COMMENT '1=Ativo, 0=Inativo',

    CONSTRAINT fk_coordenador FOREIGN KEY (id_coordenador)
        REFERENCES usuarios (id_usuario)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    CONSTRAINT fk_professor FOREIGN KEY (id_professor)
        REFERENCES professores (id_professor)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,

    CONSTRAINT unique_coordenador_professor UNIQUE (id_coordenador, id_professor)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;