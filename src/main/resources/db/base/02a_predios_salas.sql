CREATE TABLE IF NOT EXISTS predios (
    id_predio INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL COMMENT 'Nome do prédio (ex: Bloco A, Prédio Administrativo)',
    descricao TEXT NULL COMMENT 'Descrição adicional sobre o prédio',
    ativo BOOLEAN NOT NULL DEFAULT 1 COMMENT 'Indica se o prédio está ativo (1=Sim, 0=Não)',
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT 'Data de criação do registro',
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Data da última atualização'
) ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;