CREATE TABLE IF NOT EXISTS biometria (
  id_biometria INT NOT NULL AUTO_INCREMENT,

  id_usuario INT NULL,
  id_professor INT NULL,
  id_pessoa INT NULL,

  biometria LONGBLOB NOT NULL,  -- Aqui ficará o template da impressão digital
  dedo ENUM(
    'POLEGAR_DIREITO', 'INDICADOR_DIREITO', 'MEDIO_DIREITO', 'ANELAR_DIREITO', 'MINDINHO_DIREITO',
    'POLEGAR_ESQUERDO', 'INDICADOR_ESQUERDO', 'MEDIO_ESQUERDO', 'ANELAR_ESQUERDO', 'MINDINHO_ESQUERDO'
  ) NOT NULL,

  ativo BOOLEAN DEFAULT TRUE,
  data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  PRIMARY KEY (id_biometria),

  FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario),
  FOREIGN KEY (id_professor) REFERENCES professores(id_professor),
  FOREIGN KEY (id_pessoa) REFERENCES pessoa_autorizada(id_pessoa),

  CHECK (
    (id_usuario IS NOT NULL AND id_professor IS NULL AND id_pessoa IS NULL) OR
    (id_usuario IS NULL AND id_professor IS NOT NULL AND id_pessoa IS NULL) OR
    (id_usuario IS NULL AND id_professor IS NULL AND id_pessoa IS NOT NULL)
  )
) ENGINE = InnoDB;
