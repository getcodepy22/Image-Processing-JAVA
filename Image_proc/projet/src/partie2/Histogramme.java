package partie2;

import javax.swing.*;
import java.awt.*;

/*
 * La classe Histogramme étend JFrame pour créer une fenêtre graphique affichant un histogramme.
 */
public class Histogramme extends JFrame {

    private int[] data; // Les données pour l'histogramme (version entière)
    private double[] data2; // Les données pour l'histogramme (version double)
    private String title;

    /*
     * Constructeur pour créer un histogramme avec des données entières.
     *
     * @param data   Tableau d'entiers représentant les données de l'histogramme.
     * @param titre  Titre de l'histogramme.
     */
    public Histogramme(int[] data, String titre) {
        this.data = data;
        this.title = titre;
        setTitle(titre);

        // Créer un panneau pour le titre
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Histogramme de " + titre);
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        panel.add(label);

        // Ajouter le panneau au contenu de la fenêtre
        getContentPane().add(panel);

        // Configurer les paramètres de la fenêtre
        setSize(1024, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /*
     * Constructeur pour créer un histogramme avec des données de type double.
     *
     * @param data   Tableau de doubles représentant les données de l'histogramme.
     * @param titre  Titre de l'histogramme.
     */
    public Histogramme(double[] data, String titre) {
        this.data2 = data;
        this.title = titre;
        setTitle(titre);

        // Créer un panneau pour le titre
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Histogramme de " + titre);
        Font labelFont = label.getFont();
        label.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        panel.add(label);

        // Ajouter le panneau au contenu de la fenêtre
        getContentPane().add(panel);

        // Configurer les paramètres de la fenêtre
        setSize(1024, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /*
     * Méthode de peinture qui appelle la méthode pour dessiner l'histogramme.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        dessinerHistogramme(g);
    }

    /*
     * Méthode privée pour dessiner l'histogramme en utilisant les données entières.
     *
     * @param g Objet Graphics utilisé pour dessiner.
     */
    private void dessinerHistogramme(Graphics g) {
        int largeurBarre = 2;
        int espaceEntreBarres = 3;
        int x = 3;
        int hauteurMax = getHeight() - 255;

        // Trouver la valeur maximale dans les données pour normaliser la hauteur des barres
        int valeurMax = 0;
        for (int valeur : data) {
            if (valeur > valeurMax) {
                valeurMax = valeur;
            }
        }

        // Dessiner chaque barre de l'histogramme
        for (int valeur : data) {
            int hauteurBarre = (int) ((double) valeur / valeurMax * hauteurMax);
            g.fillRect(x, getHeight() - hauteurBarre - 30, largeurBarre, hauteurBarre);
            x += largeurBarre + espaceEntreBarres;
        }
    }
}
