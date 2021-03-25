package test


import org.junit.Test
import org.junit.Assert._

class MachineImpl{
  

machine.DataBase.init()

  // initialisation des objets sous test
  val m= machine.MachineImpl
  m.reinit

  @Test
  def test1 {
   assertEquals(m.ask("Où est donc la Mairie de Rennes?"), List("L'adresse de Mairie de Rennes est : Place de la Mairie"))
  }

  


 
}