package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class MultiRequetes {

  val gare = new Place("Gare", "gare", "www.gare.fr", "12 place gare", "35000", "Rennes", "911")
  val mairie = Place("Mairie", "maire", "metropole.rennes.fr", "Place de la Mairie", "35031", "Rennes", "02 23 62 10 10")
  val list1 = List(gare)
  val list2 = List(gare,mairie)
  val list3 = List(gare,mairie,gare)
  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit

  }
  //TEST DE LA FONCTION : MultiRequetes.formatMultiResults()

  /**
   * Cas à tester :
   *
   * - teste pour une liste de 1 élément pour toutes les langues
   * - teste pour une liste de 2 éléments pour toutes les langues
   * - teste pour une liste de 3 éléments pour toutes les langues MultiRequetes
   */

  /*
   * Test en français
   */
  @Test
  def testformatMultiResults1 {
    assertEquals(MultiRequetes.formatMultiResults(list1), List("J'ai 1 réponses possibles :","1) Gare"))
    assertEquals(MultiRequetes.formatMultiResults(list2), List("J'ai 2 réponses possibles :","1) Gare","2) Mairie"))
    assertEquals(MultiRequetes.formatMultiResults(list3), List("J'ai 3 réponses possibles :","1) Gare","2) Mairie","3) Gare"))
  }

  /*
   * Test en anglais
   */
  @Test
  def testformatMultiResults2 {
    StateManager.currentLanguage = DataBase.getLanguages()(1)
    assertEquals(MultiRequetes.formatMultiResults(list1), List("I found 1 answers :","1) Gare"))
    assertEquals(MultiRequetes.formatMultiResults(list2), List("I found 2 answers :","1) Gare","2) Mairie"))
    assertEquals(MultiRequetes.formatMultiResults(list3), List("I found 3 answers :","1) Gare","2) Mairie","3) Gare"))
  }

  /*
   * Test en espagnol
   */
  @Test
  def testformatMultiResults3 {
    StateManager.currentLanguage = DataBase.getLanguages()(2)
    assertEquals(MultiRequetes.formatMultiResults(list1), List("Tengo 1 opciones :","1) Gare"))
    assertEquals(MultiRequetes.formatMultiResults(list2), List("Tengo 2 opciones :","1) Gare","2) Mairie"))
    assertEquals(MultiRequetes.formatMultiResults(list3), List("Tengo 3 opciones :","1) Gare","2) Mairie","3) Gare"))
  }

  /*
   * Test en allemand
   */
  @Test
  def testformatMultiResults4 {
    StateManager.currentLanguage = DataBase.getLanguages()(3)
    assertEquals(MultiRequetes.formatMultiResults(list1), List("Ich habe 1 Antworten :","1) Gare"))
    assertEquals(MultiRequetes.formatMultiResults(list2), List("Ich habe 2 Antworten :","1) Gare","2) Mairie"))
    assertEquals(MultiRequetes.formatMultiResults(list3), List("Ich habe 3 Antworten :","1) Gare","2) Mairie","3) Gare"))
  }

  /*
   * Test en italien
   */
  @Test
  def testformatMultiResults5 {
    StateManager.currentLanguage = DataBase.getLanguages()(4)
    assertEquals(MultiRequetes.formatMultiResults(list1), List("Ho 1 risposte :","1) Gare"))
    assertEquals(MultiRequetes.formatMultiResults(list2), List("Ho 2 risposte :","1) Gare","2) Mairie"))
    assertEquals(MultiRequetes.formatMultiResults(list3), List("Ho 3 risposte :","1) Gare","2) Mairie","3) Gare"))
  }

  
  
  
  
  
  //TEST DE LA FONCTION : MultiRequetes.getAddress()
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
  def getAdresse {
    assertEquals(MultiRequetes.getAddress(gare), List("L'adresse de Gare est : 12 place gare"))
  }

  /*
   * Test en anglais
   */
  @Test
  def getAdresse1 {
    StateManager.currentLanguage = DataBase.getLanguages()(1)
    assertEquals(MultiRequetes.getAddress(gare), List("The address of Gare is : 12 place gare"))
  }

  /*
   * Test en espagnol
   */
  @Test
  def getAdresse2 {
    StateManager.currentLanguage = DataBase.getLanguages()(2)
    assertEquals(MultiRequetes.getAddress(gare), List("La dirección de Gare es : 12 place gare"))
  }

  /*
   * Test en allemand
   */
  @Test
  def getAdresse3 {
    StateManager.currentLanguage = DataBase.getLanguages()(3)
    assertEquals(MultiRequetes.getAddress(gare), List("Die adresse von Gare ist : 12 place gare"))
  }

  /*
   * Test en italien
   */
  @Test
  def getAdresse4 {
    StateManager.currentLanguage = DataBase.getLanguages()(4)
    assertEquals(MultiRequetes.getAddress(gare), List("Indirizzo di Gare è : 12 place gare"))
  }

}