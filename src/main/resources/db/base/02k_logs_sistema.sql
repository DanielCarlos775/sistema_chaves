CREATE TABLE IF NOT EXISTS logs_sistema (
  id_log INT NOT NULL AUTO_INCREMENT,

  id_usuario INT NULL,
  acao VARCHAR(100) NOT NULL,
  tabela_afetada VARCHAR(50) NULL,
  id_registro INT NULL,

  dados_anteriores JSON NULL,
  dados_novos JSON NULL,

  ip_address VARCHAR(45) NULL,
  user_agent TEXT NULL,
  data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (id_log),

  FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
) ENGINE = InnoDB;
