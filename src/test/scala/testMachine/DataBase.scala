package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class DataBase {
  // initialisation des objets sous test
  DataBase.init()
  var resultatmairie = List[machine.Place]();
  var resultatpiscine = List[machine.Place]();
  
  @Before
  def resetState = {
    MachineImpl.reinit
     resultatmairie = List(Place("Mairie de Rennes",
       "Mme Nathalie APPERE, maire.\n217 309 habitants (source INSEE - recensement de la population 2013).",
       "metropole.rennes.fr",
       "Place de la Mairie",
       "35031",
       "Rennes",
       "02 23 62 10 10"))
     
     resultatpiscine = List(

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
   
   //mot vide
   @Test
   def DataBasefindKeyword1{
     assertEquals(
         DataBase.findByKeyword(""),
         DataBase.getPlaces())
   }
   
   //même resultat qu'avec un filter
   @Test
   def DataBasefindKeyword2{
     assertEquals(
     DataBase.findByKeyword("mairie"),
     DataBase.getPlaces().filter((place: Place) => {
     place.name.toLowerCase().contains("mairie".toLowerCase())
    }))
   }
   
   //Avec un mot qui correspond à un nom d'Endroit
   @Test
   def DataBasefindKeyword3{
     assertEquals(DataBase.findByKeyword("mairie"),resultatmairie)
   }
   
   //Avec un mot qui correspond à un nom d'Endroit avec majuscules
   @Test
   def DataBasefindKeyword4{
     assertEquals(DataBase.findByKeyword("mAiRiE"),resultatmairie)
   }
   
   //Avec un mot qui correspond à plusieurs noms d'Endroits
   @Test
   def DataBasefindKeyword5{
     assertEquals(DataBase.findByKeyword("mAiRiE"),resultatmairie)
   }
   
   //Avec un mot qui ne correspond à aucun nom d'Endroit
   @Test
   def DataBasefindKeyword6{
     assertEquals(DataBase.findByKeyword("azertyuiop"),List[machine.Place]())
   }
   
   
  //TODO TEST DE LA FONCTION : DataBate.findByKeywords()

  /**
   * Cas à tester :
   *
   * - Avec une liste vide
   * - Avec une liste de mots vides
   * - Avec une liste avec un mot qui est dans un nom d'Endroit (ex : "mairie")
   * - Avec une liste avec un mot qui est dans un nom d'Endroit (ex : "mairie")
   * - Avec une liste avec des mots qui sont dans un nom d'Endroit avec majuscules (ex : "mAiRiE")
   * - Avec une liste avec des mots qui sont dans plusieurs nom d'Endroits (ex : "piscine")
   * - Avec une liste avec des mots qui sont dans plusieurs et auncun nom d'Endroit (ex : "piscine" et "asdd")
   */
}