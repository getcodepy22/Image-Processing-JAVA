import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/*
 * La classe OtsuMethode utilise la méthode d'Otsu pour binariser une image.
 */
public class OtsuMethode {

    // Chargement de la bibliothèque OpenCV native
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    /*
     * Méthode pour appliquer la méthode d'Otsu à une image.
     *
     * @param pathS Chemin de l'image source.
     * @param pathR Chemin où enregistrer l'image résultante après binarisation.
     */
    public void Otsu_Method(String pathS, String pathR) {
        // Charger l'image en niveaux de gris
        Mat grayImage = Imgcodecs.imread(pathS, Imgcodecs.IMREAD_GRAYSCALE);

        // Appliquer la méthode d'Otsu pour trouver le seuil optimal
        Mat binaryImage = new Mat();
        Imgproc.threshold(grayImage, binaryImage, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);

        // Enregistrer l'image binaire résultante
        Imgcodecs.imwrite(pathR, binaryImage);
    }
}
