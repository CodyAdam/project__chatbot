package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class Linternaute {
  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TEST DE LA FONCTION : Linternaute.searchingWords()

  /*
   * Cas à tester :
   *
   * - Avec 1 trigger words dans la phrase et 0 autre mot
   * - Avec 2 trigger words dans la phrase et 0 autre mot
   * - Avec 1 trigger words dans la phrase et 1 autre mot
   * - Avec 2 trigger words dans la phrase et 1 autre mot
   * - Avec 1 trigger words dans la phrase et pleins autres mots
   * - Avec 2 trigger words dans la phrase et pleins autres mots
   */

  @Test
  def searchingWords1() {
    assertEquals(Linternaute.searchingWords(List("restaurant")), List())
  }

  @Test
  def searchingWords2() {
    assertEquals(Linternaute.searchingWords(List("restaurant", "creperie")), List("creperie"))
  }

  @Test
  def searchingWords3() {
    assertEquals(Linternaute.searchingWords(List("restaurant", "gare")), List("gare"))
  }

  @Test
  def searchingWords4() {
    assertEquals(Linternaute.searchingWords(List("restaurant", "creperie", "gare")), List("creperie", "gare"))
  }

  @Test
  def searchingWords5() {
    assertEquals(Linternaute.searchingWords(List("salut", "bonjour", "restaurant", "la", "tomate")), List("la", "tomate"))
  }

  @Test
  def searchingWords6() {
    assertEquals(Linternaute.searchingWords(List("creperie", "gare", "bonjour", "restaurant", "la", "tomate")), List("gare", "bonjour", "restaurant", "la", "tomate"))
  }

  //TEST DE LA FONCTION : Linternaute.keyWords()

  /*
   * Cas à tester :
   *
   * - Avec mot vide
   * - Avec 1 mot
   * - Avec 2 mots
   * - Avec 3 mots
   */

  @Test
  def keyWords1() {
    assertEquals(Linternaute.keyWords(List()), "")
  }

  @Test
  def keyWords2() {
    assertEquals(Linternaute.keyWords(List("")), "+")
  }

  @Test
  def keyWords3() {
    assertEquals(Linternaute.keyWords(List("tomate")), "tomate+")
  }

  @Test
  def keyWords4() {
    assertEquals(Linternaute.keyWords(List("la", "tomate")), "la+tomate+")
  }

  @Test
  def keyWords5() {
    assertEquals(Linternaute.keyWords(List("la", "grosse", "tomate")), "la+grosse+tomate+")
  }

  //TEST DE LA FONCTION : Linternaute.findRestaurant()

  /*
   * Cas à tester :
   *
   * - Avec mot vide
   * - Avec des mots clés qui mêne à un restaurant qui existe
   * - Avec des mots clés qui mêne à un restaurant qui n'existe pas
   */

  @Test
  def findRestaurant1() {
    assertEquals(Linternaute.findRestaurant(List()), "")

  }

  @Test
  def findRestaurant2() {
    assertEquals(Linternaute.findRestaurant(List("restaurant", "la", "tomate")), "/restaurant/restaurant/9072/la-tomate.shtml")
  }

  @Test
  def findRestaurant3() {
    assertEquals(Linternaute.findRestaurant(List("restaurant", "le", "petit", "paradis")), "")
  }

  //TEST DE LA FONCTION : Linternaute.lookForAdress()

  /*
   * Cas à tester :
   *
   * - Avec mot vide
   * - Avec des mots clés qui mêne à un restaurant qui existe
   * - Avec des mots clés qui mêne à un restaurant qui n'existe pas
   */

  @Test
  def lookForAdresse1() {
    assertEquals(Linternaute.lookForAdress(List()), None)
  }

   @Test
  def lookForAdresse2() {
    assertEquals(Linternaute.lookForAdress(List("restaurant", "la", "tomate")), Some(new Place("La Tomate", "", "", "18, rue Saint Georges", "", "", "")))
   }
   
    @Test
  def lookForAdresse3() {
      assertEquals(Linternaute.lookForAdress(List("restaurant", "le", "petit", "paradis")), None)
    }
    
    @Test
  def messageAdress1(){
    assertEquals(Linternaute.messageAdress(List("restaurant", "la", "tomate")), List("L'adresse de La Tomate est : 18, rue Saint Georges"))
  }
  
  @Test
  def messageAdress2(){
    assertEquals(Linternaute.messageAdress(List("restaurant", "le", "petit", "chef", "en", "paille")), List("Je ne comprends pas votre demande"))
  }
  
  @Test
  def messageAdress3(){
    assertEquals(Linternaute.messageAdress(List("restaurant", "le", "petit")), List("L'adresse de Au Petit Grenier est : 5 r Chapitre"))
  }
  
}