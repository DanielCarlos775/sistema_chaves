CREATE TABLE IF NOT EXISTS pessoas_autorizadas (
    id_pessoa INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario_responsavel INT NOT NULL COMMENT 'ID do usuário administrativo responsável por essa pessoa autorizada',
    nome VARCHAR(100) NOT NULL COMMENT 'Nome completo da pessoa autorizada',
    cpf VARCHAR(20) NOT NULL UNIQUE COMMENT 'CPF da pessoa autorizada',
    telefone VARCHAR(20) NULL COMMENT 'Telefone de contato',
    email VARCHAR(100) NULL COMMENT 'Email para contato',
    ativo TINYINT(1) NOT NULL DEFAULT 1 COMMENT '1=Ativo, 0=Inativo',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação do registro',
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data da última atualização',

    CONSTRAINT fk_pessoas_autorizadas_usuario FOREIGN KEY (id_usuario_responsavel)
        REFERENCES usuarios(id_usuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;