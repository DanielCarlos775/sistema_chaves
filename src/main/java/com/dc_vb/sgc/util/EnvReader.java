package com.dc_vb.sgc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvReader {

    private static final Properties props = new Properties();

    static {
        try (InputStream input = EnvReader.class.getClassLoader().getResourceAsStream("db/config.properties")) {
            if (input == null) {
                throw new RuntimeException("Arquivo config.properties não encontrado em resources/db/");
            }
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar config.properties", e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}