package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class CaseLinternaute {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  // TESTER LA FONCTIONNALITÉ : F7 du tp avec linternaute avec la fonction MachineImpl.ask()
  
  /**
   * Cas à tester :
   * 
   * - 1 mot trigger linternaute et un restaurant qui existe
   * - 1 mot trigger linternaute et un restaurant qui existe pas
   * - 1 mot trigger linternaute et un mot qui renvois à plusieurs restaurant
   */
  
  @Test
  def ask(){
    assertEquals(MachineImpl.ask("restaurant la tomate"), List("18, rue Saint Georges"))
    assertEquals(MachineImpl.ask("restaurant le petit chef en paille"), List("Je ne comprends pas votre demande"))
    assertEquals(MachineImpl.ask("restaurant le petit"), List("5 r Chapitre"))


  }
}