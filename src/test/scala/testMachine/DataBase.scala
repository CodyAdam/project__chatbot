package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class DataBase {
  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TODO TEST DE LA FONCTION : DataBate.findByKeyword()

  /**
   * Cas à tester :
   *
   * - Avec un mot vide
   * - Avec un mot qui est dans un nom d'Endroit (ex : "mairie")
   * - Avec un mot qui est dans un nom d'Endroit avec majuscules (ex : "mAiRiE")
   * - Avec un mot qui est dans plusieurs nom d'Endroits (ex : "piscine")
   * - Avec un mot qui est dans auncun nom d'Endroit (ex : "djkxvn")
   */
   
   //mot vide
   @Test
   def DataBasefindKeyword1{
     assertEquals(
         DataBase.findByKeyword(""),
         DataBase.getPlaces())
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