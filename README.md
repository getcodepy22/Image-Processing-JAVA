# 🖼️ Projet Java - Traitement d'Images

## 📌 Description

Ce projet Java permet de réaliser différentes opérations de **traitement d’images**.
Il a été enrichi avec plusieurs fonctionnalités avancées comme une **interface graphique**, le **support multi-formats**, et des **filtres supplémentaires**.

---

## ⚙️ Fonctionnalités principales

### 🎯 1. Filtrage d'image

* Filtre Sobel (détection de contours)
* Filtre moyenneur (flou)
* Filtre Gaussien (réduction du bruit)
* Filtre Laplacien (détection de contours avancée)

---

### 📊 2. Histogramme

* Affichage de l’histogramme avant filtrage
* Affichage après filtrage

---

### 🎨 3. Conversion en niveaux de gris

* Transformation RGB → Gray
* Sauvegarde des images transformées

---

### 📈 4. Égalisation d’histogramme

* Normalisation
* Table de transformation (LookUp Table)
* Amélioration du contraste

---

### ⚫ 5. Binarisation (Méthode d’Otsu)

* Calcul automatique du seuil
* Génération d’image binaire

---

### 🖥️ 6. Interface graphique (GUI)

* Interface développée avec **JavaFX / Swing**
* Chargement d’image via interface
* Visualisation des résultats en temps réel
* Interaction utilisateur simplifiée

---

### 🧩 7. Support multi-formats

* Lecture et traitement de plusieurs formats :

  * JPG
  * PNG
  * BMP
  * autres formats compatibles Java

---

### ⚡ 8. Traitement en temps réel

* Application des filtres instantanément
* Mise à jour dynamique de l’image
* Optimisation des performances

---

## 🧱 Structure du projet

```text id="eq7t9u"
src/
│
├── Main.java
│
├── partie1/
│   ├── Image.java
│   ├── Getpixels.java
│   └── OtsuMethode.java
│
├── partie2/
│   └── ImageGray.java
│
├── ui/                  # Interface graphique
│
└── images/
    ├── origine/
    └── modifier/
```

---

## 📂 Configuration

Modifier le chemin des images :

```java id="u1v0sp"
String Path ="C:\\Users\\omar2020\\projet\\images\\";
```

---

## ▶️ Exécution

### 1. Compilation

```bash id="h6x7pi"
javac Main.java
```

### 2. Exécution

```bash id="1xikns"
java Main
```

---

## 🔄 Pipeline de traitement

1. Chargement de l’image
2. Redimensionnement
3. Application des filtres
4. Analyse histogramme
5. Conversion en niveaux de gris
6. Égalisation
7. Binarisation (Otsu)
8. Affichage via interface graphique

---

## 📦 Classes principales

### 🔹 Image

* Chargement / sauvegarde
* Redimensionnement
* Application des filtres

### 🔹 Getpixels

* Extraction des pixels
* Histogrammes

### 🔹 ImageGray

* Conversion en gris
* Égalisation
* Normalisation

### 🔹 OtsuMethode

* Binarisation automatique

---

## ⚠️ Remarques

* Vérifier le chemin des images
* S’assurer que les dossiers existent
* Compatible avec plusieurs formats d’image

---

## 👨‍💻 Auteur

**ALICHE OMAR**

---

## 🚀 Évolutions futures

* Export des résultats en PDF
* Ajout de Deep Learning (classification d’images)
* Déploiement en application desktop complète

---
