package testMachine

import org.junit.Test
import org.junit.Assert._
import machine._

class MachineImpl {

  // initialisation des objets sous test
  DataBase.init()
  MachineImpl.reinit

  @Test
  def test1 {
    assertEquals(MachineImpl.ask("Où est donc la Mairie de Rennes?"), List("L'adresse de Mairie de Rennes est : Place de la Mairie"))
  }
}