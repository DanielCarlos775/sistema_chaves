package com.dc_vb.sgc.util;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordHasher {

    private static final int WORK_FACTOR = 12; // ajuste conforme hardware

    private PasswordHasher() {}

    public static String hash(String plain) {
        if (plain == null || plain.isBlank())
            throw new IllegalArgumentException("Senha vazia/ inválida.");
        String salt = BCrypt.gensalt(WORK_FACTOR);
        return BCrypt.hashpw(plain, salt);
    }

    public static boolean verify(String plain, String hashed) {
        if (plain == null || hashed == null || hashed.isBlank())
            return false;
        return BCrypt.checkpw(plain, hashed);
    }
}
