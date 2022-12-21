package br.senac.objects;

import at.favre.lib.crypto.bcrypt.BCrypt;
import java.awt.Color;

/**
 *
 * @author Douglas
 */
public abstract class Utils {

    public static Color convertColor(String cor) {
        String corArray[] = cor.split(",");
        int r = Integer.parseInt(corArray[0]);
        int g = Integer.parseInt(corArray[1]);
        int b = Integer.parseInt(corArray[2]);
        return new Color(r, g, b);
    }

    public static String colorToString(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        return r + "," + g + "," + b;
    }

    public static String gerarhashSenha(String senhaAberta) {
        return BCrypt.withDefaults().hashToString(8, senhaAberta.toCharArray());
    }

    public static boolean verificarSenha(String senhaAberta, String senhaFechada) {
        BCrypt.Result resultado = BCrypt.verifyer().verify(senhaAberta.toCharArray(), senhaFechada);
        return resultado.verified;
    }
    
    
}
