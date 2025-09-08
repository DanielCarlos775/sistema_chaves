CREATE TABLE IF NOT EXISTS chaves (
  id_chave INT NOT NULL AUTO_INCREMENT,
  id_sala INT NOT NULL UNIQUE,  -- Relacionamento 1:1 com sala. Cada sala tem no máximo 1 chave associada
  codigo VARCHAR(50) NOT NULL UNIQUE, -- Código único da chave (exemplo: "CH001", "CH002")
  descricao TEXT NULL,
  ativo BOOLEAN DEFAULT TRUE,
  data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  PRIMARY KEY (id_chave),

  FOREIGN KEY (id_sala) REFERENCES salas(id_sala)
) ENGINE = InnoDB;
