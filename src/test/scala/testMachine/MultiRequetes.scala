package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class MultiRequetes {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }
  //TODO TEST DE LA FONCTION : MultiRequetes.formatMultiResults()

  /**
   * Cas à tester :
   *
   * - teste pour une liste de 1 élément pour toutes les langues
   * - teste pour une liste de 2 éléments pour toutes les langues
   * - teste pour une liste de 3 éléments pour toutes les langues
   */

  //TODO TEST DE LA FONCTION : MultiRequetes.getAddress()

  /**
   * Cas à tester :
   *
   * - teste pour une Place pour toutes les langues
   */
}