package testMachine

import scala.collection.immutable.Set;
import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class DataBase {
  // initialisation des objets sous test
  DataBase.init()
  var resultatMairie = List[machine.Place]();
  var resultatPiscine = List[machine.Place]();
  var resultatVide = List[machine.Place]()
  
  @Before
  def resetState = {
    MachineImpl.reinit
    resultatMairie = List(Place("Mairie de Rennes",
      "Mme Nathalie APPERE, maire.\n217 309 habitants (source INSEE - recensement de la population 2013).",
      "metropole.rennes.fr",
      "Place de la Mairie",
      "35031",
      "Rennes",
      "02 23 62 10 10"))
    
    resultatPiscine = List(
       Place("Piscine Bréquigny",
      "Piscine municipale olympique. Elle dispose de 2 bassins. Dispositifs de mise à l'eau pour les personnes handicapées.\nEcole municipale de natation : cours collectifs à partir de 5 ans. Apprentissage, perfectionnement. Partenaire de la carte Sortir.",
      "metropole.rennes.fr",
      "10, Boulevard Albert 1er",
      "35031",
      "Rennes",
      "02 23 62 27 30"), 
      
      Place("Piscine Gayeulles",
      "Bassin de 25 mètres,fosse de plongée de 10 mètres de profondeur, espace détente, hammams,lagune pour les tout- petits et piscine à vagues. École municipale de natation : cours collectifs à partir de 5 ans. Partenaire de la carte Sortir.\nVendredi  10 avril : fermeture à 19h pour permettre au jeune public de 11 à 16 ans de participer à la soirée Ados.",
      "metropole.rennes.fr",
      "16, AVENUE DES GAYEULLES,AVENUE DES GAYEULLES",
      "35031",
      "Rennes",
      "02 23 62 27 40"), 
      
      Place("Piscine Saint-Georges",
      "Bassin (33,33 x 14 m), bain-douche (accès rue Gambetta). Piscine décorée par le mosaïste Odorico. Ecole municipale de natation : cours collectifs à partir de 5 ans. Partenaire de la carte Sortir.",
      "metropole.rennes.fr",
      "4, RUE GAMBETTA",
      "35031",
      "Rennes",
      "02 23 62 15 40"), 
      
      Place("Piscine Villejean",
      "2 bassins (25x12,50 m et 12,5x10 m).\nEcole municipale de natation : cours collectifs à partir de 5 ans. Partenaire de la carte Sortir.",
      "metropole.rennes.fr",
      "1, SQUARE D'ALSACE",
      "35031",
      "Rennes",
      "02 23 62 27 50"))
  }

  //TODO TEST DE LA FONCTION : DataBate.findByKeyword()

  /**
   * Cas à tester :
   *
   * - Avec un mot vide
   * - Avec un mot qui correspond à un nom d'Endroit (ex : "mairie")
   * - Avec un mot qui correspond à un nom d'Endroit avec majuscules (ex : "mAiRiE")
   * - Avec un mot qui correspond à plusieurs noms d'Endroits (ex : "piscine")
   * - Avec un mot qui ne correspond à aucun nom d'Endroit (ex : "djkxvn")
   */
   
   //mot vide : tout les resultats
   @Test
   def DataBasefindKeyword1{
     assertEquals(
         DataBase.findByKeyword(""),
         DataBase.getPlaces())
   }
   
   //même resultat qu'avec un filter : deux méthodes égales
   @Test
   def DataBasefindKeyword2{
     assertEquals(
     DataBase.findByKeyword("mairie"),
     DataBase.getPlaces().filter((place: Place) => {
     place.name.toLowerCase().contains("mairie".toLowerCase())
    }))
   }
   
   //Avec un mot qui correspond à un nom d'Endroit : résultats contenants ce mot
   @Test
   def DataBasefindKeyword3{
     assertEquals(DataBase.findByKeyword("mairie"),resultatMairie)
   }
   
   //Avec un mot qui correspond à un nom d'Endroit avec majuscules : mêmes résultats que le mot en minuscule
   @Test
   def DataBasefindKeyword4{
     assertEquals(DataBase.findByKeyword("mAiRiE"),resultatMairie)
   }
   
   //Avec un mot qui correspond à plusieurs noms d'Endroits : touts les résultats contenants ce mot
   @Test
   def DataBasefindKeyword5{
     assertEquals(DataBase.findByKeyword("piscine"),resultatPiscine)
   }
   
   //Avec un mot qui ne correspond à aucun nom d'Endroit : aucun résultat
   @Test
   def DataBasefindKeyword6{
     assertEquals(DataBase.findByKeyword("azertyuiop"), resultatVide)
   }
   
   
  //TODO TEST DE LA FONCTION : DataBate.findByKeywords()

  /**
   * Cas à tester :
   *
   * - Avec un ensemble vide
   * - Avec un ensemble de mots vides
   * - Avec un ensemble contenant un mot qui correspond à un nom d'Endroit (ex : "mairie")
   * - contenant un ensemble contenant des mots qui correspondent à un nom d'Endroit avec majusculescontenant : "mAiRiE")
   * - Avec un ensemble contenant des mots qui correspondent à plusieurs nom d'Endroits (ex : "piscine")
   * - Avec un ensemble contenant des mots qui ne correspondent à aucun auncun nom d'Endroits (ex : "asdd")
   * - Avec un ensemble contenant des mots qui correspondent soit à plusieurs nom endroits soit à aucun auncun (ex : "piscine" et "asdd")
   */
   
   //Avec un ensemble vide : 0 recherche
   @Test
   def DataBasefindKeywords1{
     assertEquals(
         DataBase.findByKeywords(Set()),
         resultatVide)
   }
   
   //Avec un ensemble de mots vides : tout les resultats
   @Test
   def DataBasefindKeywords2{
     assertEquals(
     DataBase.findByKeywords(Set("","","")),
     DataBase.getPlaces())
   }
   
   //Avec un ensemble contenant un mot qui correspond à un nom d'Endroit
   @Test
   def DataBasefindKeywords3{
     assertEquals(DataBase.findByKeywords(Set("mairie")),resultatMairie)
   }
   
   //Avec un mot qui correspond à un nom d'Endroit avec majuscules
   @Test
   def DataBasefindKeywords4{
     assertEquals(DataBase.findByKeywords(Set("mAiRiE")),resultatMairie)
   }
   
   //Avec un mot qui correspond à plusieurs noms d'Endroits
   @Test
   def DataBasefindKeywords5{
     assertEquals(DataBase.findByKeywords(Set("piscine")),resultatPiscine)
   }
   
   //Avec un mot qui ne correspond à aucun nom d'Endroit
   @Test
   def DataBasefindKeywords6{
     assertEquals(DataBase.findByKeywords(Set("azertyuiop","qsdfghjklm")),resultatVide)
   }
   
   //Avec un ensemble contenant des mots qui correspondent soit à plusieurs nom endroits soit à aucun auncun
   @Test
   def DataBasefindKeywords7{
     assertEquals(DataBase.findByKeywords(Set("azertyuiop","qsdfghjklm","piscine")),resultatPiscine)
   }
}