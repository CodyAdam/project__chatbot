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
}