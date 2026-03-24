# 🖼️ Installation d'OpenCV avec Java sur Eclipse

## 📌 Description

Ce guide explique étape par étape comment configurer **OpenCV** avec **Java** dans **Eclipse** sous Windows.

---

## 📥 1. Télécharger OpenCV

1. Aller sur le site officiel :
   👉 https://opencv.org/releases/

2. Télécharger la version Windows

3. Extraire le fichier ZIP dans un dossier, par exemple :

```bash
C:\opencv\
```

---

## 📁 2. Fichiers importants

Après extraction :

```bash
C:\opencv\build\java\
```

### 📦 Contenu :

* `opencv-4xx.jar` → bibliothèque Java
* `x64/opencv_java4xx.dll` → bibliothèque native

---

## ⚙️ 3. Configuration dans Eclipse

### 🔧 Étape 1 : Ajouter le JAR

1. Clic droit sur le projet → **Properties**
2. Aller dans **Java Build Path**
3. Onglet **Libraries**
4. Cliquer sur **Add External JARs**
5. Ajouter :

```bash
opencv-4xx.jar
```

⚠️ Important :
👉 Le JAR doit être dans **Classpath (PAS Modulepath)**

---

### 🔧 Étape 2 : Configurer la DLL

1. Aller dans :
   👉 **Run → Run Configurations**

2. Onglet **Arguments**

3. Dans **VM arguments**, ajouter :

```bash
-Djava.library.path=C:\opencv\build\java\x64;C:\opencv\build\x64\vc15\bin
```

---

## 🧪 4. Test d'installation

Créer une classe Java :

```java
import org.opencv.core.Core;

public class TestOpenCV {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("OpenCV chargé avec succès !");
    }
}
```

---

## ▶️ 5. Exécution

1. Lancer le programme
2. Résultat attendu :

```bash
OpenCV chargé avec succès !
```

---

## ❌ 6. Erreurs fréquentes et solutions

### 🔴 Erreur : `NoClassDefFoundError`

👉 Cause : JAR non ajouté au Classpath
✔️ Solution : Ajouter `opencv-xxx.jar` dans **Classpath**

---

### 🔴 Erreur : `UnsatisfiedLinkError`

👉 Cause : DLL non trouvée
✔️ Solution :

* Vérifier `-Djava.library.path`
* Ajouter aussi :

```bash
C:\opencv\build\x64\vc15\bin
```

---

### 🔴 Erreur : `Can't load library`

👉 Cause :

* Mauvais chemin
* DLL manquante
* Incompatibilité 32/64 bits

✔️ Solution :

* Java 64 bits + OpenCV x64
* Vérifier le nom exact :

```bash
opencv_java470.dll
```

---

## 💡 Conseils

* Toujours utiliser la même architecture : **Java x64 + OpenCV x64**
* Éviter les espaces dans les chemins
* Faire **Project → Clean** après configuration
* Vérifier que le JAR est visible dans **Referenced Libraries**

---

## 📂 Exemple de structure

```text
projet/
│
├── src/
│   └── TestOpenCV.java
│
├── lib/
│   └── opencv-470.jar
```

---



# 🖼️ Installation d'OpenCV avec Java sur Visual Studio Code

## 📌 Description

Ce guide explique comment configurer **OpenCV** avec **Java** dans **Visual Studio Code (VS Code)** sous Windows.

---

## 📥 1. Télécharger OpenCV

1. Aller sur :
   👉 https://opencv.org/releases/

2. Télécharger la version Windows

3. Extraire dans :

```bash
C:\opencv\
```

---

## 📁 2. Fichiers importants

Après extraction :

```bash
C:\opencv\build\java\
```

### 📦 Contenu :

* `opencv-4xx.jar` → bibliothèque Java
* `x64/opencv_java4xx.dll` → bibliothèque native

---

## ⚙️ 3. Installer les extensions VS Code

Installer :

* **Extension Pack for Java**
* **Debugger for Java**

---

## 🧱 4. Structure du projet

```text
project/
│
├── src/
│   └── TestOpenCV.java
│
├── lib/
│   └── opencv-470.jar
```

👉 Copier le fichier `.jar` dans `lib/`

---

## ⚙️ 5. Configuration dans VS Code

### 🔧 Étape 1 : Ajouter le JAR

Créer/modifier le fichier :

```bash
.vscode/settings.json
```

Ajouter :

```json
{
  "java.project.referencedLibraries": [
    "lib/opencv-470.jar"
  ]
}
```

---

### 🔧 Étape 2 : Configurer la DLL

Créer/modifier :

```bash
.vscode/launch.json
```

Ajouter :

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Run OpenCV",
      "request": "launch",
      "mainClass": "TestOpenCV",
      "vmArgs": "-Djava.library.path=C:/opencv/build/java/x64;C:/opencv/build/x64/vc15/bin"
    }
  ]
}
```

---

## 🧪 6. Test d'installation

Créer :

```java
import org.opencv.core.Core;

public class TestOpenCV {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("OpenCV chargé avec succès !");
    }
}
```

---

## ▶️ 7. Exécution

* Appuyer sur **Run ▶️** dans VS Code

Résultat attendu :

```bash
OpenCV chargé avec succès !
```

---

## ❌ 8. Erreurs fréquentes

### 🔴 `NoClassDefFoundError`

👉 Cause : JAR non chargé
✔️ Vérifier :

```json
"java.project.referencedLibraries"
```

---

### 🔴 `UnsatisfiedLinkError`

👉 Cause : DLL non trouvée
✔️ Vérifier :

```bash
-Djava.library.path=...
```

---

### 🔴 `Can't load library`

👉 Cause :

* Mauvais chemin
* DLL manquante
* Version incompatible

✔️ Solution :

* Java x64 + OpenCV x64
* Vérifier nom :

```bash
opencv_java470.dll
```

---

## 💡 Conseils

* Utiliser `/` au lieu de `\` dans les chemins
* Redémarrer VS Code après configuration
* Vérifier que le dossier `lib/` contient bien le `.jar`

---

## 👨‍💻 Auteur

**ALICHE OMAR**

---

## 🚀 Conclusion

OpenCV est maintenant configuré avec Java sur VS Code et Eclipse.


---


