package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._


class DefinitionLinternaute {
  
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }
  
  @Test
  def searchingWord1(){
    assertEquals(DefinitionLinternaute.searchingWord(List()), "")
  }
  
  @Test
  def searchingWord2(){
    assertEquals(DefinitionLinternaute.searchingWord(List("")), "")
  }
  
  @Test
  def searchingWord3(){
    assertEquals(DefinitionLinternaute.searchingWord(List("définition")), "")
  }
  
  @Test
  def searchingWord4(){
    assertEquals(DefinitionLinternaute.searchingWord(List("définition", "théâtre")), "théâtre")
  }
  
  @Test
  def searchingWord5(){
    assertEquals(DefinitionLinternaute.searchingWord(List("définition", "de", "théâtre")), "théâtre")
  }
  
  @Test
  def searchingWord6(){
    assertEquals(DefinitionLinternaute.searchingWord(List("définition", "de", "théâtre", "salut")), "théâtre")
  }
  
  @Test
  def searchingWord7(){
    assertEquals(DefinitionLinternaute.searchingWord(List("salut", "définition", "de", "théâtre")), "théâtre")
  }
  
  @Test
  def ignoreAccent1(){
    assertEquals(DefinitionLinternaute.ignoreAccent(""), "")
  }
  
  @Test
  def ignoreAccent2(){
    assertEquals(DefinitionLinternaute.ignoreAccent("salut"), "salut")
  }
  
  @Test
  def ignoreAccent3(){
    assertEquals(DefinitionLinternaute.ignoreAccent("théâtre"), "theatre")
  }
  
  @Test
  def lookingForDefinition1(){
    assertEquals(DefinitionLinternaute.lookingForDefinition(List("")), List())
  }
  
  @Test
  def lookingForDefinition2(){
    assertEquals(DefinitionLinternaute.lookingForDefinition(List("définition")), List())
  }
  
  @Test
  def lookingForDefinition3(){
    assertEquals(DefinitionLinternaute.lookingForDefinition(List("définition", "zibevuo")), List(""))
  }
  
  @Test
  def lookingForDefinition4(){
    assertEquals(DefinitionLinternaute.lookingForDefinition(List("définition", "de", "kayak")), List("Embarcation individuelle des Inuits, à carcasse de bois recouverte de peaux cousues qui entourent l'emplacement du rameur.", "Embarcation propulsée à l'aide d'une pagaie double et dont l'étanchéité est assurée par une jupe imperméable serrée autour de la taille du pagayeur"))
  }
  
}