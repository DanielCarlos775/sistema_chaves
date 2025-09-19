CREATE TABLE IF NOT EXISTS salas (
    id_sala INT AUTO_INCREMENT PRIMARY KEY,
    id_predio INT NOT NULL COMMENT 'FK para o prédio ao qual a sala pertence',
    nome VARCHAR(100) NOT NULL COMMENT 'Nome ou número da sala (ex: 101, Laboratório 1)',
    descricao TEXT NULL COMMENT 'Descrição adicional sobre a sala',
    capacidade INT NULL COMMENT 'Capacidade máxima da sala (em pessoas)',
    ativo BOOLEAN NOT NULL DEFAULT 1 COMMENT '1=Ativo, 0=Inativo',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação do registro',
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data da última atualização',

    CONSTRAINT fk_salas_predio FOREIGN KEY (id_predio)
        REFERENCES predios(id_predio)
        ON DELETE RESTRICT
        ON UPDATE CASCADE,

    CONSTRAINT unique_sala_predio UNIQUE (id_predio, nome)
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;