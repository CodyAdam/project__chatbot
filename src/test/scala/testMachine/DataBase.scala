package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class DataBase {
  // initialisation des objets sous test
  DataBase.init()
  var resultatmairie = List[machine.Place]();
  
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
  }

  //TODO TEST DE LA FONCTION : DataBate.findByKeyword()

  /**
   * Cas à tester :
   *
   * - Avec un mot vide
   * - Avec un mot qui correspond à un nom d'Endroit (ex : "mairie")
   * - Avec un mot qui correspond à un nom d'Endroit avec majuscules (ex : "mAiRiE")
   * - Avec un mot qui correspond à plusieurs noms d'Endroits (ex : "piscine")
   * - Avec un mot qui est dans auncun nom d'Endroit (ex : "djkxvn")
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
   def DataBasefindKeyword4{
        assertEquals(DataBase.findByKeyword("mAiRiE"),resultatmairie)
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