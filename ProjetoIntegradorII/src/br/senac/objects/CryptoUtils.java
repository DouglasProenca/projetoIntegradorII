package br.senac.objects;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 *
 * @author Douglas
 */
public final class CryptoUtils {

    public static String gerarhashSenha(String senhaAberta) {
        return BCrypt.withDefaults().hashToString(8, senhaAberta.toCharArray());
    }

    public static boolean verificarSenha(String senhaAberta, String senhaFechada) {
        BCrypt.Result resultado = BCrypt.verifyer().verify(senhaAberta.toCharArray(), senhaFechada);
        return resultado.verified;
    }

}
