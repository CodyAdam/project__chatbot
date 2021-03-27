package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class CaseDefault {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TODO TESTER LA FONCTIONNALITÉ : F1 du tp avec les phrases de bases avec la fonction MachineImpl.ask()
  
  /**
   * Cas à tester :
   * 
   * - Hotel de ville
   * - Mairie
   * - Mairie de Rennes
   * - TNB
   * - Theatre de Bretagne
   * - Theatre National de Bretagne
   * - Gare
   * - Gare SNCF 
   * - Theatre la Paillette
   * - La Paillette
   */
}