package partie1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
/*
	cette classe permet d'extraire les valeurs des pixels d'une image et calculer l'histogramme de chaque coleur et les afficher:
         * On a une image couleur est composé de trois couleurs( Red, Green, Blue) dans notre cas;
         * Donc chaque pixel est composé de trois couleurs pour cela on utilise HashMap ( c'est un dictionnaire qui
                   permet de stocker l'histogramme  de chaque couleur en utilisant une cle (Key)
 */
public class Getpixels {
	private BufferedImage img;
	// <String : parceque on a utilisé un cle en chaine de caractere et int[] : parceque on va stocker des tableau d'Histogrammes des Couleurs
	HashMap<String, int[]> Color_Histo = new HashMap<String, int[]>();

	   //constructeur de classe
	 public Getpixels(BufferedImage file){
			this.img = file;
		}
		/*
		* cette methode permet de recuperer les valeurs des pixels d'une image  et calculer les histogrammes et les stocker dans un HashMap
		* */
     private HashMap<String, int[]> GetPixels() {

		 //Definition les matrices pour chaque couleur
		 int[][] red_array = new int[this.img.getHeight()][this.img.getWidth()];
		 int[][] green_array = new int[this.img.getHeight()][this.img.getWidth()];
		 int[][] blue_array = new int[this.img.getHeight()][this.img.getWidth()];

		 //Declaration les tableau des histogrammes pour langueur de 256 (l'intervale des couleurs est [0..255])
		 int[] histogram_blue = new int[256];
		 int[] histogram_red = new int[256];
		 int[] histogram_green = new int[256];

		 for (int y = 0; y < this.img.getHeight(); y++) {
			 for (int x = 0; x < this.img.getWidth(); x++) {
				 //R�cup�rer le contenu d'un pixel
				 int pixel = this.img.getRGB(x, y);
				 //Creation d'un objet Color a partir d'une valeur de pixel
				 Color color = new Color(pixel, false);
				 // cette instructio permet de recuperer un objet de couleur avec opacity si on met a True sinon le contraire

				 //Recuperation des valeurs Red Green Blue
				 int red = color.getRed();
				 int green = color.getGreen();
				 int blue = color.getBlue();

				 //stocker chaque valeur de couleurs dans les matrices
				 red_array[y][x] = red;
				 green_array[y][x] = green;
				 blue_array[y][x] = blue;

                 //calculer l'histogramme pour chaque couleur
				 histogram_red[red]++;
				 histogram_green[green]++;
				 histogram_blue[blue]++;



			 }
		 }
		 //stocker les histogrammes
		 Color_Histo.put("red",histogram_red);
		 Color_Histo.put("blue",histogram_blue);
		 Color_Histo.put("green",histogram_green);


		 return Color_Histo;
	 }

    // cette methode permet d'afficher l'histogramme pour chaque couleur
    public void  Afficher_Histogramme(String Description) {
		 new Histogramme(this.GetPixels().get("blue"), "Couleur Blue "+Description);
		new Histogramme(this.GetPixels().get("red"), "Couleur Rouge "+Description);
		new Histogramme(this.GetPixels().get("green"), "Couleur Vert "+Description);

	}
  /*
  * GetPixles().get("red") : cette pseudo-instruction permet de recuperer histogramme en utilisant la cle
  * */
	}

