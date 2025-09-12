CREATE TABLE IF NOT EXISTS movimentacoes (
  id_movimentacao INT NOT NULL AUTO_INCREMENT,

  id_agendamento INT NOT NULL,
  id_chave INT NOT NULL,
  id_usuario_guarita INT NOT NULL,

  id_usuario INT NULL,
  id_professor INT NULL,
  id_pessoa INT NULL,

  tipo_movimentacao ENUM('CHECK_IN', 'CHECK_OUT') NOT NULL,
  data_hora TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  observacoes TEXT NULL,

  id_biometria INT NULL,

  PRIMARY KEY (id_movimentacao),

  FOREIGN KEY (id_agendamento) REFERENCES agendamentos(id_agendamento),
  FOREIGN KEY (id_chave) REFERENCES chaves(id_chave),
  FOREIGN KEY (id_usuario_guarita) REFERENCES usuarios(id_usuario),
  FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
  FOREIGN KEY (id_professor) REFERENCES professores(id_professor),
  FOREIGN KEY (id_pessoa) REFERENCES pessoas_autorizadas(id_pessoa),
  FOREIGN KEY (id_biometria) REFERENCES biometria(id_biometria),

  CHECK (
    (id_usuario IS NOT NULL AND id_professor IS NULL AND id_pessoa IS NULL) OR
    (id_usuario IS NULL AND id_professor IS NOT NULL AND id_pessoa IS NULL) OR
    (id_usuario IS NULL AND id_professor IS NULL AND id_pessoa IS NOT NULL)
  )
) ENGINE = InnoDB;
