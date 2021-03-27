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

  //TODO TESTER LA FONCTIONNALITÉ : F7 du tp avec linternaute avec la fonction MachineImpl.ask()
  
  /**
   * Cas à tester :
   * 
   * - 1 mot trigger linternaute et un restaurant qui existe
   * - 1 mot trigger linternaute et un restaurant qui existe pas
   * - 1 mot trigger linternaute et un mot qui renvois à plusieurs restaurant
   */
}