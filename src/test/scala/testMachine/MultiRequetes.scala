package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class MultiRequetes {
  
  val gare = new Place("Gare","gare","www.gare.fr","12 place gare","35000","Rennes","911")

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
    
  }
  //TODO TEST DE LA FONCTION : MultiRequetes.formatMultiResults()

  /**
   * Cas à tester :
   *
   * - teste pour une liste de 1 élément pour toutes les langues
   * - teste pour une liste de 2 éléments pour toutes les langues
   * - teste pour une liste de 3 éléments pour toutes les langues
   */

  //TODO TEST DE LA FONCTION : MultiRequetes.getAddress()

  /**
   * Cas à tester :
   *
   * 1 par langue
   * - teste pour une Place pour toutes les langues
   */
  
  /*
   * Test en français
   */
  @Test
  def getAdresse{
     assertEquals(MultiRequetes.getAddress(gare), List("L'adresse de Gare est : 12 place gare"))
   }
  
  /*
   * Test en anglais
   */
  @Test
  def getAdresse1{
    StateManager.currentLanguage = DataBase.getLanguages()(1)
     assertEquals(MultiRequetes.getAddress(gare), List("The address of Gare is : 12 place gare"))
   }
  
  /*
   * Test en espagnol
   */
  @Test
  def getAdresse2{
    StateManager.currentLanguage = DataBase.getLanguages()(2)
     assertEquals(MultiRequetes.getAddress(gare), List("La dirección de Gare es : 12 place gare"))
   }
  
  /*
   * Test en allemand
   */
  @Test
  def getAdresse3{
    StateManager.currentLanguage = DataBase.getLanguages()(3)
     assertEquals(MultiRequetes.getAddress(gare), List("Die adresse von Gare ist : 12 place gare"))
   }
  
  /*
   * Test en italien
   */
  @Test
  def getAdresse4{
    StateManager.currentLanguage = DataBase.getLanguages()(4)
     assertEquals(MultiRequetes.getAddress(gare), List("Indirizzo di Gare è : 12 place gare"))
   }
  
}