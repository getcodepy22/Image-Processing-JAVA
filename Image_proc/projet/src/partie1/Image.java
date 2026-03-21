package partie1;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * La classe Image fournit des fonctionnalités pour manipuler et traiter les images.
 */
public class Image {

	// Chemin du fichier image
	public String file;

	/*
	 * Constructeur de la classe Image.
	 *
	 * @param path Chemin du fichier image.
	 */
	public Image(String path) {
		this.file = path;
	}

	/*
	 * Charge une image à partir du fichier spécifié.
	 *
	 * @return L'objet BufferedImage représentant l'image chargée.
	 * @throws IOException En cas d'erreur lors de la lecture du fichier.
	 */
	public BufferedImage ImageCharge() throws IOException {
		BufferedImage image = ImageIO.read(new File(this.file));
		return image;
	}

	/*
	 * Redimensionne l'image à la nouvelle largeur et hauteur spécifiées.
	 *
	 * @param newWidth  Nouvelle largeur de l'image.
	 * @param newHeight Nouvelle hauteur de l'image.
	 * @throws IOException En cas d'erreur lors de la manipulation de l'image.
	 */
	public void ImageSize(int newWidth, int newHeight) throws IOException {
		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(ImageCharge(), 0, 0, newWidth, newHeight, null);
		g.dispose();
	}

	/*
	 * Applique un filtre à convolution à l'image.
	 *
	 * @param matrix       Matrice représentant le noyau du filtre.
	 * @param width_karnel Largeur du noyau du filtre.
	 * @param height_karnel Hauteur du noyau du filtre.
	 * @return L'objet BufferedImage représentant l'image filtrée.
	 * @throws IOException En cas d'erreur lors de l'application du filtre.
	 */
	public BufferedImage FilterImg(float[] matrix, int width_karnel, int height_karnel) throws IOException {
		ConvolveOp op = new ConvolveOp(new Kernel(width_karnel, height_karnel, matrix), ConvolveOp.EDGE_NO_OP, null);
		BufferedImage blurredImage = op.filter(ImageCharge(), null);
		return blurredImage;
	}

	/*
	 * Enregistre l'image modifiée sous un nouveau nom et un nouveau chemin.
	 *
	 * @param image L'image modifiée à enregistrer.
	 * @param name  Le nouveau nom de l'image.
	 * @param path  Le nouveau chemin où enregistrer l'image.
	 * @throws IOException En cas d'erreur lors de l'enregistrement de l'image.
	 */
	public void SaveImg(BufferedImage image, String name, String path) throws IOException {
		ImageIO.write(image, "png", new File(path + "modifier\\" + name + ".png"));
	}
}
