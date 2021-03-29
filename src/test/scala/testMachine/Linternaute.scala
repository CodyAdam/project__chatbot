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
  def searchingWords() {
    assertEquals(Linternaute.searchingWords(List("restaurant")),List())
    assertEquals(Linternaute.searchingWords(List("restaurant", "creperie")),List("creperie"))
    assertEquals(Linternaute.searchingWords(List("restaurant", "gare")),List("gare"))
    assertEquals(Linternaute.searchingWords(List("restaurant", "creperie", "gare")),List("creperie", "gare"))
    assertEquals(Linternaute.searchingWords(List("salut", "bonjour","restaurant", "la", "tomate")),List("la", "tomate"))
    assertEquals(Linternaute.searchingWords(List("creperie", "gare", "bonjour","restaurant", "la", "tomate")),List("gare", "bonjour","restaurant", "la", "tomate"))
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
  def keyWords(){
    assertEquals(Linternaute.keyWords(List()),"")
    assertEquals(Linternaute.keyWords(List("")),"+")
    assertEquals(Linternaute.keyWords(List("tomate")),"tomate+")
    assertEquals(Linternaute.keyWords(List("la", "tomate")),"la+tomate+")
    assertEquals(Linternaute.keyWords(List("la", "grosse", "tomate")),"la+grosse+tomate+")
  }

  //TEST DE LA FONCTION : Linternaute.findRestaurant()

  /*
   * Cas à tester :
   *
   * - Avec mot vide
   * - Avec des mots clés qui mêne à un restaurant qui existe
   * - Avec des mots clés qui mêne à un restaurant qui existe pas
   */

  @Test
  def findRestaurant(){
    assertEquals(Linternaute.findRestaurant(""),"")
    assertEquals(Linternaute.findRestaurant("la+tomate+"),"/restaurant/restaurant/9072/la-tomate.shtml")
    assertEquals(Linternaute.findRestaurant("le+petit+paradis"),"")
  }
  
  //TODO TEST DE LA FONCTION : Linternaute.lookForAdress()

  /*
   * Cas à tester :
   *
   * - Avec un lien vide
   * - Avec un lien de restaurant qui existe
   * - Avec un lien de restaurant qui existe restaurant qui existe pas
   * - Avec un lien de restaurant qui existe mais qui n'as pas d'addresses
   */

}