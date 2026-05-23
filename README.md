# NeoSchool-QA-Automation
# 🎓 NeoSchool QA Automation Framework

> Framework de test automatisé pour l'application **NeoSchool** — plateforme de gestion scolaire.  
> Projet réalisé dans le cadre d'un stage QA en 2026.

![Java](https://img.shields.io/badge/Java-25-orange?logo=java)
![Selenium](https://img.shields.io/badge/Selenium-4.43-green?logo=selenium)
![TestNG](https://img.shields.io/badge/TestNG-7.9-red)
![Maven](https://img.shields.io/badge/Maven-Build-blue?logo=apachemaven)
![Status](https://img.shields.io/badge/Status-En%20cours-yellow)

---

## 📋 Description

Ce projet implémente un framework de test automatisé end-to-end pour l'application NeoSchool, en suivant le pattern **Page Object Model (POM)**. Il couvre les modules fonctionnels critiques de la plateforme et génère des rapports HTML détaillés après chaque exécution.

---

## 🛠️ Stack technique

| Outil | Version | Rôle |
|---|---|---|
| Java | 25 | Langage principal |
| Selenium WebDriver | 4.43 | Automatisation navigateur |
| TestNG | 7.9 | Framework de test |
| Maven | 3.x | Gestion des dépendances & build |
| ExtentReports | 5.x | Rapports HTML |
| WebDriverManager | 5.x | Gestion automatique ChromeDriver |
| Chrome | 148 | Navigateur cible |

---

## 📁 Structure du projet

```
NeoSchool-QA-Automation/
│
├── src/
│   └── test/
│       └── java/
│           ├── base/
│           │   └── BaseTest.java          # Setup/Teardown + ExtentReports
│           ├── pages/
│           │   ├── BasePage.java          # Méthodes communes (saisir, cliquer…)
│           │   ├── LoginPage.java
│           │   ├── DashboardPage.java
│           │   ├── AcademicYearPage.java
│           │   ├── TermPage.java
│           │   ├── SchoolPage.java
│           │   ├── AdminPage.java
│           │   ├── TeacherPage.java
│           │   ├── LevelPage.java
│           │   └── ClassPage.java
│           └── tests/
│               ├── TC01_CreerEcole.java
│               ├── TC02_CreerAnneeAcademique.java
│               ├── TC03_CreerTrimestre.java
│               ├── TC04_CreerAdmin.java
│               ├── TC05_CreerEnseignant.java
│               ├── TC06_CreerNiveau.java
│               └── TC07_CreerClasse.java
│
├── test-output/
│   └── custom-report/                     # Rapports HTML générés
│
├── testng.xml                             # Suite de tests
├── pom.xml                                # Dépendances Maven
└── README.md
```

---

## 🧪 Cas de test couverts

| ID | Module | Description | Statut |
|---|---|---|---|
| TC-01 | École | Créer une école | ✅ |
| TC-02 | Année académique | Créer l'année 2026/2027 | ✅ |
| TC-03 | Trimestre | Créer les 3 trimestres | ✅ |
| TC-04 | Admin | Créer un administrateur | ✅ |
| TC-05 | Enseignant | Créer un enseignant | ✅ |
| TC-06 | Niveau | Créer un niveau scolaire | ✅ |
| TC-07 | Classe | Créer une classe | ✅ |

---

## ⚙️ Architecture — Page Object Model

```
BaseTest (setup/teardown/rapport)
    └── TC0X_NomTest (@Test)
            └── XxxPage (Page Object)
                    └── BasePage (actions communes)
```

- **`BaseTest`** : initialise le driver Chrome, effectue le login automatiquement avant chaque test via `@BeforeMethod`, capture les screenshots en cas d'échec.
- **`BasePage`** : centralise les interactions Selenium (`saisir`, `cliquer`, `cliquerJS`, `scrollEtCliquer`…).
- **`Pages`** : chaque page de l'appli a sa propre classe avec ses locators et ses méthodes métier.
- **`Tests`** : chaque TC hérite de `BaseTest` et se concentre uniquement sur le scénario fonctionnel.

---

## 🚀 Lancer les tests

### Prérequis

- Java 11+ installé
- Maven installé
- Google Chrome installé
- Connexion internet (WebDriverManager télécharge ChromeDriver automatiquement)

### Exécution

```bash
# Cloner le projet
git clone https://github.com/TON_USERNAME/NeoSchool-QA-Automation.git
cd NeoSchool-QA-Automation

# Lancer toute la suite
mvn test

# Lancer un test spécifique
mvn test -Dtest=TC03_CreerTrimestre
```

### Rapport HTML

Après l'exécution, le rapport est généré dans :

```
test-output/custom-report/Execution Results - yyyy-MM-dd HH-mm-ss.html
```

Ouvrir ce fichier dans un navigateur pour consulter les résultats détaillés avec screenshots.

---

## 📊 Exemple de rapport

Le rapport ExtentReports inclut :
- ✅ Statut de chaque test (PASSED / FAILED / SKIPPED)
- 📸 Screenshot automatique en cas d'échec
- 📝 Logs d'étapes (`test.info(...)`)
- 🕐 Durée d'exécution

---

## 🔍 Bonnes pratiques appliquées

- ✅ Pattern **Page Object Model** strict
- ✅ **`@BeforeMethod`** centralisé dans `BaseTest` (login unique)
- ✅ Locators robustes (`By.xpath` pour les IDs avec caractères spéciaux)
- ✅ `WebDriverWait` explicite (pas de `Thread.sleep`)
- ✅ Rapports automatiques avec **ExtentReports**
- ✅ Screenshots sur échec
- ✅ Code commenté en français

---

## 👤 Auteur

**Meher Hammami**  
QA Test Automation Engineer  
📍 Tunisie  
🎓 BeeOne Academy — QA MasterClass (2026)  


---

## 📄 Licence

Ce projet est réalisé à des fins pédagogiques et de démonstration portfolio.
