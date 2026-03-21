
package partieGraphique;

import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import javax.swing.border.EmptyBorder;

import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * La classe FilterApp est une application graphique pour appliquer différents filtres à une image.
 */
public class FilterApp {

    private static final long serialVersionUID = 1L;

    private JFrame frame;
    private JLabel originalImageLabel;
    private JLabel filteredImageLabel;
    private JLabel text;
    private BufferedImage originalImage;
    private BufferedImage filteredImage;

    // Différents filtres prédéfinis

    private float[] filtre_moyenneur ={
            0.04f,0.04f,0.04f,0.04f,0.04f,
            0.04f,0.04f,0.04f,0.04f,0.04f,
            0.04f,0.04f,0.04f,0.04f,0.04f,
            0.04f,0.04f,0.04f,0.04f,0.04f,
            0.04f,0.04f,0.04f,0.04f,0.04f
    };

    private float[] filtre_gaussian =
            {
                    0.075f, 0.125f, 0.075f,
                    0.125f, 0.200f, 0.125f,
                    0.075f, 0.125f, 0.075f
            };

    private float[] filtre_sobel_h ={
            -1f,-2f,-1f,
            0f,0f,0f,
            1f,2f,1f
    };
    private float[] filtre_sobel_v ={
            -1f,0f,1f,
            -2f,0f,2f,
            -1f,0f,1f
    };

    /*
     * Constructeur de la classe FilterApp.
     */
    public FilterApp() {
        // Configuration de la fenêtre principale
        frame = new JFrame();
        frame.setTitle("Image Filter App");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Définissez la taille de la fenêtre pour utiliser toute la largeur et la hauteur de l'écran
        frame.setSize(screenSize.width, screenSize.height);
        //frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialisation des composants de l'interface utilisateur
        originalImageLabel = new JLabel();
        filteredImageLabel = new JLabel();
        Font myFont = new Font("Serif", Font.BOLD, 20);
        text = new JLabel();
        text.setFont(myFont);
        JPanel label = new JPanel();
        label.add(text);

        // Bouton pour charger l'image
        JButton loadButton = new JButton("Charger Image");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadAndDisplayImage();
            }
        });

        // Boutons pour appliquer différents filtres
        JButton blurButton = new JButton("Niveau de gris");
        blurButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    applyFilter(FilterType.GRAYSCALE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton MFButton = new JButton("Filtre Moyenneur");
        MFButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    applyFilter(FilterType.MOYENNEUR);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton GFButton = new JButton("Filtre Gaussian");
        GFButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    applyFilter(FilterType.GAUSSIAN);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton SHFButton = new JButton("Filtre Sobel Horizontal");
        SHFButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    applyFilter(FilterType.SOBEL_H);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton SVFButton = new JButton("Filtre Sobel Verticale");
        SVFButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    applyFilter(FilterType.SOBEL_V);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        JButton EGButton = new JButton("Egalisation");
        EGButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    applyFilter(FilterType.EGALISE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Configuration du panel de boutons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(loadButton);
        buttonPanel.add(blurButton);
        buttonPanel.add(MFButton);
        buttonPanel.add(GFButton);
        buttonPanel.add(SHFButton);
        buttonPanel.add(SVFButton);
        buttonPanel.add(EGButton);


        buttonPanel.setBorder(new EmptyBorder(0, 0, 50, 0));
        // Configuration de la mise en page de la fenêtre principale
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(originalImageLabel, BorderLayout.WEST);
        frame.getContentPane().add(filteredImageLabel, BorderLayout.EAST);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(label, BorderLayout.NORTH);
    }

    /*
     * Méthode pour charger et afficher une image.
     */
    private void loadAndDisplayImage() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                originalImage = ImageIO.read(selectedFile);
                displayImage(originalImage, originalImageLabel);
                text.setText("Image Originale");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * Méthode pour afficher une image dans un composant JLabel.
     */
    private void displayImage(BufferedImage image, JLabel label) {
        ImageIcon icon = new ImageIcon(image.getScaledInstance(300, 300, Image.SCALE_SMOOTH));
        label.setIcon(icon);
        label.setBorder(new EmptyBorder(10, 10, 10, 10));

    }

    /*
     * Méthode pour appliquer un filtre spécifique à l'image chargée.
     */
    private void applyFilter(FilterType filterType) throws IOException {
        if (originalImage != null) {
            switch (filterType) {
                case MOYENNEUR:
                    filteredImage = FilterImg(filtre_moyenneur, 5, 5);
                    text.setText("Image en Filtre moyenneur");
                    break;
                case GRAYSCALE:
                    filteredImage = Recommandation_601(originalImage);
                    text.setText("Image en niveau de gris");
                    break;
                case SOBEL_H:
                    filteredImage = FilterImg(filtre_sobel_h, 3, 3);
                    text.setText("Image en Filtre Sobel Horizontal");
                    break;
                case SOBEL_V:
                    filteredImage = FilterImg(filtre_sobel_v, 3, 3);
                    text.setText("Image en Filtre Sobel Verticale");
                    break;
                case EGALISE:
                    filteredImage = ImgEgalise(originalImage);
                    text.setText("Image Egalisée");
                    break;
                case GAUSSIAN:
                    filteredImage = FilterImg(filtre_gaussian, 3, 3);
                    text.setText("Image en Filtre Gaussian");
                    // Ajouter d'autres types de filtres au besoin
            }
            displayImage(filteredImage, filteredImageLabel);
        }
    }

    /*
     * Méthode pour appliquer un filtre convolutif à l'image.
     */
    private BufferedImage FilterImg(float[] matrix, int width_karnel, int height_karnel) throws IOException {
        ConvolveOp op = new ConvolveOp(new Kernel(width_karnel, height_karnel, matrix), ConvolveOp.EDGE_NO_OP, null);
        BufferedImage blurredImage = op.filter(originalImage, null);
        return blurredImage;
    }

    /*
     * Méthode pour convertir une image en niveaux de gris selon la recommandation 601 - CEI.
     */
    private BufferedImage Recommandation_601(BufferedImage image) {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                // Obtenir la couleur du pixel
                Color c = new Color(image.getRGB(x, y));
                // Calculer la valeur de gris du pixel recommandation 601 - CEI
                int gray = (int) (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue());
                // Créer une nouvelle couleur en niveaux de gris
                Color grayColor = new Color(gray, gray, gray);
                // Définir la nouvelle couleur pour le pixel
                image.setRGB(x, y, grayColor.getRGB());
            }
        }
        return image;
    }

    /*
     * Méthode pour convertir une image en niveaux de gris en utilisant la moyenne des valeurs RVB.
     */
    private BufferedImage Values_mean(BufferedImage image) {
        BufferedImage outputImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                Color color = new Color(image.getRGB(x, y));
                int grayValue = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                Color grayColor = new Color(grayValue, grayValue, grayValue);
                outputImage.setRGB(x, y, grayColor.getRGB());
            }
        }
        return outputImage;
    }

    /*
     * Méthode pour égaliser l'histogramme d'une image.
     */
    private BufferedImage ImgEgalise(BufferedImage img) throws IOException {
        int[] data = Histogramme(Values_mean(img));
        double[] data_normalize = Histogramme_Normalization(data, img);
        int[] lookUpTable = Transformation_function(data_normalize);

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int grayLevel = new Color(img.getRGB(x, y)).getRed();
                int newGrayLevel = lookUpTable[grayLevel];
                Color newColor = new Color(newGrayLevel, newGrayLevel, newGrayLevel);
                img.setRGB(x, y, newColor.getRGB());
            }
        }
        return img;
    }

    /*
     * Méthode pour calculer l'histogramme d'une image en niveaux de gris.
     */
    private int[] Histogramme(BufferedImage grayImage) {
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
     * Méthode pour normaliser un histogramme.
     */
    private double[] Histogramme_Normalization(int[] data, BufferedImage img) {
        int numPixels = Values_mean(img).getWidth() * Values_mean(img).getHeight();
        double[] normalizedHist = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            normalizedHist[i] = (double) data[i] / numPixels;
        }
        return normalizedHist;
    }

    /*
     * Méthode pour générer une table de recherche pour l'égalisation de l'histogramme.
     */
    private int[] Transformation_function(double[] data) {
        int[] lookupTable = new int[256];
        double sum = 0;
        for (int i = 0; i < lookupTable.length; i++) {
            sum += data[i];
            lookupTable[i] = (int) Math.round(255 * sum);
        }
        return lookupTable;
    }

    /*
     * Enumération des types de filtres disponibles.
     */
    private enum FilterType {
        GRAYSCALE, MOYENNEUR, GAUSSIAN, SOBEL_H, SOBEL_V, EGALISE
        // Ajouter d'autres types de filtres au besoin
    }

    /*
     * Méthode principale pour exécuter l'application.
     */
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new NimbusLookAndFeel());

        try {
            FilterApp window = new FilterApp();
            window.frame.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
