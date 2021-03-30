package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class CasePolite {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TODO TESTER LA FONCTIONNALITÉ : F3 du tp avec les formules de politesse avec la fonction MachineImpl.ask()
  
  /**
   * Cas à tester :
   * 
   * - avec un mot de politesse de la langue actuel
   * - avec un mot de politesse de la langue actuel et d'autres mots
   */
  
  //avec un mot de politesse de la langue actuel
  @Test
  def politesse1() = {
    assertEquals(MachineImpl.ask("Bonjour"), List("Bonjour"))
  }
  
  //avec un mot de politesse de la langue actuel
  @Test
  def politesse2() = {
    assertEquals(MachineImpl.ask("Salut"), List("Bonjour"))
  }
  
  //avec un mot de politesse de la langue actuel et un mot incompris par le robot
  @Test
  def politesse3() = {
    assertEquals(MachineImpl.ask("Bonjour boss"), List("Bonjour","Je ne comprends pas votre demande"))
  }
  
  //avec un mot de politesse d'une autre langue
  @Test
  def politesse4() = {
    assertEquals(MachineImpl.ask("Hello"), List("Do you speak english?"))
  }
  
  //avec un mot de politesse dans la langue déjà modifiée
  @Test
  def politesse5() = {
    StateManager.changeLanguage(DataBase.getLanguages()(1))
    assertEquals(MachineImpl.ask("Hello"), List("Hi"))
  }
  
  //avec un mot de politesse dans la langue déjà modifiée et un mot incompris par le robot
  @Test
  def politesse6() = {
    StateManager.changeLanguage(DataBase.getLanguages()(1))
    assertEquals(MachineImpl.ask("Hello frero"), List("Hi","I do not understand"))
  }

}