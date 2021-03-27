package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class CaseErrors {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TODO TESTER LA FONCTIONNALITÉ : F2 du tp avec erreurs d'orthographes avec la fonction MachineImpl.ask()
  
  /**
   * Cas à tester :
   * 
   * - Accents
   * - Mots de même longueur mais change de 1 lettre
   * - Mots avec 1 lettre en moins
   * - Mots avec 1 lettre en plus
   */
}