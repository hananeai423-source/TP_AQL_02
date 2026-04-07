# TP2 – Tests d'Intégration 

## Informations
- **Matière :** Tests Logiciels
- **TP :** TP2 – Tests d'intégration avec JUnit 5 et Mockito
  - **Auteur :** AISSANI Hanane 

---

## Objectifs
Ce TP explore les tests d'intégration en Java à travers trois exercices
de complexité croissante :
- Interaction entre modules (Service + Repository)
- Interaction avec une base de données via DAO
- Intégration d'une API externe avec gestion d'erreurs

---

## Technologies utilisées
- Java 21
- JUnit 5.10.0 (jupiter)
- Mockito 5.15.2
- Maven

---

## Structure du projet
src/
├── main/java/
│   ├── exercise1/  → User, UserRepository, UserService
│   ├── exercise2/  → Order, OrderDao, OrderService, OrderController
│   └── exercise3/  → Product, ProductApiClient, ApiException, ProductService
└── test/java/
├── exercise1/  → UserServiceTest
├── exercise2/  → OrderControllerTest
└── exercise3/  → ProductServiceTest

---

## Questions & Réponses

### Q1 : Qu'est-ce qu'un test d'intégration ?
Un test d'intégration vérifie que plusieurs modules fonctionnent
correctement ensemble, contrairement au test unitaire qui teste
un seul composant isolé.

### Q2 : Pourquoi utiliser des mocks ?
Les mocks permettent de simuler des dépendances externes
(base de données, API) pour rendre les tests rapides, fiables et
indépendants de l'environnement.

### Q3 : Quelle est la différence entre `verify` et `assert` ?
- `assert` vérifie la valeur retournée par une méthode.
- `verify` (Mockito) vérifie qu'une méthode a bien été appelée,
  avec les bons arguments, le bon nombre de fois.

### Q4 : Pourquoi utiliser l'injection de dépendances ?
Elle permet de substituer les vraies implémentations par des mocks
lors des tests, sans modifier le code de production.

### Q5 : Quels scénarios tester pour une API externe ?
1. **Succès** : l'API retourne les données attendues.
2. **Format invalide** : l'API retourne null ou des données corrompues.
3. **Échec réseau** : l'API lève une exception.
4. **Entrée invalide** : l'appel ne devrait même pas atteindre l'API.

---



## Résultats des tests
Tous les tests passent avec succès :
- UserServiceTest : 2 tests ✅
- OrderControllerTest : 3 tests ✅
- ProductServiceTest : 4 tests ✅

## Conclusion

Ce TP nous a permis de maîtriser les fondamentaux des tests d'intégration en Java. À travers l'Exercice 1, nous avons appris à :

- **Simuler des dépendances** avec Mockito pour isoler la classe à tester (`UserService`)
- **Vérifier les interactions** entre composants en utilisant `verify()`
- **Tester les cas nominaux et limites** (utilisateur trouvé vs non trouvé)
- **Intégrer Mockito avec JUnit 5** via l'annotation `@ExtendWith(MockitoExtension.class)`

Les avertissements rencontrés (`Sharing is only supported for boot loader classes`, `Mockito is currently self-attaching`) se sont révélés être des informations normales sous Java 21 avec IntelliJ IDEA, sans impact sur l'exécution des tests.

Les compétences acquises dans cet exercice serviront de base pour les exercices suivants, notamment :
- Les tests avec base de données (Exercice 2)
- Les tests d'intégration d'API externes (Exercice 3)