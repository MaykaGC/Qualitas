package org.example.Utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utilidades para el manejo de contraseñas utilizando el algoritmo de hash BCrypt.
 * Esta clase proporciona métodos para generar un hash de una contraseña y verificarla.
 */
public class UtilsPassword {

    /**
     * Genera un hash para una contraseña en texto plano utilizando el algoritmo BCrypt.
     *
     * @param plainTextPassword La contraseña en texto plano que se desea hashear.
     * @return El hash de la contraseña generada.
     */
    public static String hashPassword(String plainTextPassword) {
        Logger.logInfo("Hash password");
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * Verifica si una contraseña en texto plano coincide con un hash previamente generado.
     *
     * @param plainTextPassword La contraseña en texto plano que se desea verificar.
     * @param hashedPassword El hash de la contraseña con el que se desea comparar.
     * @return {@code true} si la contraseña en texto plano coincide con el hash, {@code false} en caso contrario.
     */
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        Logger.logInfo("Check password");
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}