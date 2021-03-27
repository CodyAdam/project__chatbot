package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class CaseChangeLang {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TODO TESTER LA FONCTIONNALITÉ : Changement de langue avec la fonction MachineImpl.ask()
  
  /**
   * Cas à tester :
   * 
   * - changement avec un mot de politesse
   * - changement avec un mot de recherche 
   * - changement avec un nom de langue
   * - changement en faisant le tour de toutes les langues au moins 1 fois
   */
}