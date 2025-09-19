CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL COMMENT 'Nome completo do usuário',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT 'Email para login e contato',
    senha VARCHAR(255) NOT NULL COMMENT 'Hash da senha (usar SHA2 ou bcrypt na aplicação)',
    tipo_usuario ENUM('ADM', 'COORDENADOR', 'ADMINISTRATIVO', 'GUARITA') NOT NULL COMMENT 'Tipo do usuário no sistema',
    id_predio INT NULL COMMENT 'Prédio de trabalho (caso aplicável)',
    id_sala INT NULL COMMENT 'Sala de trabalho (se aplicável - usado por administrativos)',
    ativo BOOLEAN NOT NULL DEFAULT 1 COMMENT '1=Ativo, 0=Inativo',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação do registro',
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data da última atualização',

    CONSTRAINT fk_usuario_predio FOREIGN KEY (id_predio)
        REFERENCES predios(id_predio)
        ON DELETE SET NULL
        ON UPDATE CASCADE,

    CONSTRAINT fk_usuario_sala FOREIGN KEY (id_sala)
        REFERENCES salas(id_sala)
        ON DELETE SET NULL
        ON UPDATE CASCADE
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;