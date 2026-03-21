package partie2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/*
 * La classe ImageGray fournit des fonctionnalités pour manipuler et traiter les images en niveaux de gris.
 */
public class ImageGray {
    private BufferedImage img;

    /*
     * Constructeur de la classe ImageGray.
     *
     * @param image L'image à traiter en niveaux de gris.
     */
    public ImageGray(BufferedImage image) {
        this.img = image;
    }

    /*
     * Applique la recommandation 601-CEI pour convertir l'image en niveaux de gris.
     *
     * @return L'image convertie en niveaux de gris.
     */
    public BufferedImage Recommandation_601() {
        for (int y = 0; y < this.img.getHeight(); y++) {
            for (int x = 0; x < this.img.getWidth(); x++) {
                // Obtenir la couleur du pixel
                Color c = new Color(this.img.getRGB(x, y));
                // Calculer la valeur de gris du pixel recommandation 601 - CEI
                int gray = (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
                // Créer une nouvelle couleur en niveaux de gris
                Color grayColor = new Color(gray, gray, gray);
                // Définir la nouvelle couleur pour le pixel
                this.img.setRGB(x, y, grayColor.getRGB());
            }
        }
        return this.img;
    }

    /*
     * Convertit l'image en niveaux de gris en utilisant la moyenne des valeurs des composantes RGB.
     *
     * @return L'image convertie en niveaux de gris.
     */
    public BufferedImage Values_mean() {
        BufferedImage outputImage = new BufferedImage(this.img.getWidth(), this.img.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < this.img.getHeight(); y++) {
            for (int x = 0; x < this.img.getWidth(); x++) {
                Color color = new Color(this.img.getRGB(x, y));
                int grayValue = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                Color grayColor = new Color(grayValue, grayValue, grayValue);
                outputImage.setRGB(x, y, grayColor.getRGB());
            }
        }
        return outputImage;
    }

    /*
     * Enregistre l'image en niveaux de gris sous un nouveau nom et un nouveau chemin.
     *
     * @param path Le chemin où enregistrer l'image.
     * @param name Le nouveau nom de l'image.
     * @throws IOException En cas d'erreur lors de l'enregistrement de l'image.
     */
    public void save_Img_gray(String path, String name) throws IOException {
        File output = new File(path + "\\modifier\\" + name + ".jpg");
        if (name.equals("Recommandation601")) {
            ImageIO.write(Recommandation_601(), "jpg", output);
        } else {
            ImageIO.write(Values_mean(), "jpg", output);
        }
    }

    /*
     * Calcule l'histogramme d'une image en niveaux de gris.
     *
     * @param grayImage L'image en niveaux de gris.
     * @return Un tableau représentant l'histogramme.
     */
    public int[] Histogramme(BufferedImage grayImage) {
        int[] histogram = new int[256];
        for (int y = 0; y < grayImage.getHeight(); y++) {
            for (int x = 0; x < grayImage.getWidth(); x++) {
                int grayLevel = new Color(grayImage.getRGB(x, y)).getRed();
                histogram[grayLevel]++;
            }
        }
        return histogram;
    }

    /*
     * Normalise l'histogramme fourni en utilisant le nombre total de pixels de l'image.
     *
     * @param data L'histogramme à normaliser.
     * @return Un tableau représentant l'histogramme normalisé.
     */
    public double[] Histogramme_Normalization(int[] data) {
        int numPixels = Values_mean().getWidth() * Values_mean().getHeight();
        double[] normalizedHist = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            normalizedHist[i] = (double) data[i] / numPixels;
        }
        return normalizedHist;
    }

    /*
     * Crée une fonction de transformation à partir d'un histogramme normalisé.
     *
     * @param data L'histogramme normalisé.
     * @return Un tableau représentant la fonction de transformation.
     */
    public int[] Transformation_function(double[] data) {
        int[] lookupTable = new int[256];
        double sum = 0;
        for (int i = 0; i < lookupTable.length; i++) {
            sum += data[i];
            lookupTable[i] = (int) Math.round(255 * sum);
        }
        return lookupTable;
    }

    /*
     * Égalise l'image en niveaux de gris en utilisant une fonction de transformation fournie.
     *
     * @param grayImage   L'image en niveaux de gris à égaliser.
     * @param lookupTable La fonction de transformation à appliquer.
     * @throws IOException En cas d'erreur lors de l'enregistrement de l'image égalisée.
     */
    public void GrayImgEgalise(BufferedImage grayImage, int[] lookupTable,String Path) throws IOException {
        for (int y = 0; y < grayImage.getHeight(); y++) {
            for (int x = 0; x < grayImage.getWidth(); x++) {
                int grayLevel = new Color(grayImage.getRGB(x, y)).getRed();
                int newGrayLevel = lookupTable[grayLevel];
                Color newColor = new Color(newGrayLevel, newGrayLevel, newGrayLevel);
                grayImage.setRGB(x, y, newColor.getRGB());
            }
        }
        File output = new File(Path+"modifier\\image_égalisée.jpg");
        ImageIO.write(grayImage, "jpg", output);
    }

    /*
     * Affiche les histogrammes des deux méthodes de conversion en niveaux de gris.
     */
    public void Afficher_Histogramme() {
        new Histogramme(this.Histogramme(this.Values_mean()), "Niveau De gris avec La methode des moyens");
        new Histogramme(this.Histogramme(this.Recommandation_601()), "Niveau De gris avec Recommandation_601");
    }
}
