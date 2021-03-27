package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class CaseMulti {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TODO TESTER LA FONCTIONNALITÉ : F6 du tp avec les choix multiples avec la fonction MachineImpl.ask()
  
  /**
   * Cas à tester :
   * 
   * - avec un mot qui renvoi X résultats et on choisi un nombre entre 1 et X
   * - avec un mot qui renvoi X résultats et on choisi un nombre supérieur à X
   * - avec un mot qui renvoi X résultats et on choisi autre chose qu'un nombre
   * - avec un mot qui renvoi X résultats et on choisi un nombre entre 1 et X qui est introduit dans une phrase
   */
}