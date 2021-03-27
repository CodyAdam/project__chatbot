package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class CaseBlague {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TODO TESTER LA FONCTIONNALITÉ : blagues avec la fonction MachineImpl.ask()

  /**
   * Cas à tester :
   *
   * 	 (il faut vérifier que le rendu te donne bien une blague contenue dans la liste des blague de la langue actuel)
   * - demander une blague avec les mots de la langue actue
   */
}