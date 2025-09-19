CREATE TABLE IF NOT EXISTS professores (
    id_professor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL COMMENT 'Nome completo do professor',
    email VARCHAR(100) NULL COMMENT 'Email do professor',
    telefone VARCHAR(20) NULL COMMENT 'Telefone de contato',
    cpf VARCHAR(20) NOT NULL UNIQUE COMMENT 'CPF do professor, usado como identificação única',
    ativo BOOLEAN NOT NULL DEFAULT 1 COMMENT '1=Ativo, 0=Inativo',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação do registro',
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data da última atualização'
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;