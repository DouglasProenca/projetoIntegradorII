package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;

import at.favre.lib.crypto.bcrypt.BCrypt;

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

	public static BufferedImage setImagemDimensao(String caminhoImg, Integer imgLargura, Integer imgAltura) {
		Double novaImgLargura = null;
		Double novaImgAltura = null;
		Double imgProporcao = null;
		Graphics2D g2d = null;
		BufferedImage imagem = null, novaImagem = null;

		try {
			// --- Obtém a imagem a ser redimensionada ---
			imagem = ImageIO.read(new File(caminhoImg));
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		// --- Obtém a largura da imagem ---
		novaImgLargura = (double) imagem.getWidth();

		// --- Obtám a altura da imagem ---
		novaImgAltura = (double) imagem.getHeight();

		// --- Verifica se a altura ou largura da imagem recebida é maior do que os ---
		// --- parâmetros de altura e largura recebidos para o redimensionamento ---
		if (novaImgLargura >= imgLargura) {
			imgProporcao = (novaImgAltura / novaImgLargura);// calcula a proporção
			novaImgLargura = (double) imgLargura;

			// --- altura deve <= ao parâmetro imgAltura e proporcional a largura ---
			novaImgAltura = (novaImgLargura * imgProporcao);

			// --- se altura for maior do que o parâmetro imgAltura, diminui-se a largura de
			// ---
			// --- forma que a altura seja igual ao parâmetro imgAltura e proporcional a
			// largura ---
			while (novaImgAltura > imgAltura) {
				novaImgLargura = (double) (--imgLargura);
				novaImgAltura = (novaImgLargura * imgProporcao);
			}
		} else if (novaImgAltura >= imgAltura) {
			imgProporcao = (novaImgLargura / novaImgAltura);// calcula a proporção
			novaImgAltura = (double) imgAltura;

			// --- se largura for maior do que o parâmetro imgLargura, diminui-se a altura
			// de ---
			// --- forma que a largura seja igual ao parâmetro imglargura e proporcional a
			// altura ---
			while (novaImgLargura > imgLargura) {
				novaImgAltura = (double) (--imgAltura);
				novaImgLargura = (novaImgAltura * imgProporcao);
			}
		}

		novaImagem = new BufferedImage(novaImgLargura.intValue(), novaImgAltura.intValue(), BufferedImage.TYPE_INT_RGB);
		g2d = novaImagem.createGraphics();
		g2d.drawImage(imagem, 0, 0, novaImgLargura.intValue(), novaImgAltura.intValue(), null);

		return novaImagem;
	}

	public static byte[] getImgBytes(BufferedImage image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "JPEG", baos);
		} catch (IOException ex) {
			// handle it here.... not implemented yet...
		}

		// InputStream is = new ByteArrayInputStream(baos.toByteArray())
		return baos.toByteArray();
	}

	public static BufferedImage exibiImagemLabel(byte[] minhaimagem) {
		try {
			return minhaimagem != null ? ImageIO.read(new ByteArrayInputStream(minhaimagem)) : null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static BufferedImage iconToBufferedImage(Icon icon) {
		BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		icon.paintIcon(null, g, 0, 0);
		return bi;
	}
}
