package org.example.Utils;

import org.mindrot.jbcrypt.BCrypt;

public class UtilsPassword {
    public static String hashPassword(String plainTextPassword) {
        Logger.logInfo("Hash password");
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        Logger.logInfo("Check password");
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}