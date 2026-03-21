import partie1.Getpixels;
import partie1.Image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import partie1.*;
import partie2.ImageGray;

public class Main {

	public static void main(String[] args) throws IOException {
		float[] matrix =
				{
						1f, 0f, -1f,
						2f, 0f, -2f,
						1f, 0f, -1f
				};
		float[] filtre_moyenneur ={
				0.1111f,0.1111f,0.1111f,
				0.1111f,0.1111f,0.1111f,
				0.1111f,0.1111f,0.1111f
		};
		/*float[] matrix = {
			        0.0625f, 0.125f, 0.0625f,
					0.125f, 0.25f, 0.125f,
					0.0625f, 0.125f, 0.0625f
		};*/

		//saisir votre path
	
		String Path ="C:\\Users\\omar2020\\projet\\images\\"; // saisir votre Path

		// TODO Auto-generated method stub
		Image img = new Image(Path+"origine\\img.jpg");
		img.ImageCharge();
		img.ImageSize(600, 400);
		BufferedImage image = img.FilterImg(matrix,3,3);
		img.SaveImg(image,"image1",Path);

		Getpixels pixels = new Getpixels(img.ImageCharge());
		pixels.Afficher_Histogramme("Avant Filtrage");

		Getpixels pixels1 = new Getpixels(img.FilterImg(matrix,3,3));
		pixels1.Afficher_Histogramme("Apres Filtrage");

		ImageGray r1 = new ImageGray(img.ImageCharge());
		r1.save_Img_gray(Path,"moyenne");
		r1.save_Img_gray(Path,"Recommandation_601");

		int[] data = r1.Histogramme(r1.Values_mean());
		r1.Afficher_Histogramme();

		double[] data_normalize = r1.Histogramme_Normalization(data);

		int [] lookUpTable = r1.Transformation_function(data_normalize);

		r1.GrayImgEgalise(r1.Recommandation_601(),lookUpTable,Path);

		OtsuMethode M1 =new OtsuMethode();
		M1.Otsu_Method(Path+"origine\\img.jpg",Path +"modifier\\img_B.jpg");

	}

}
