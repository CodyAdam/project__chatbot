package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class TestExemple{
  // initialisation des objets sous test
  
  DataBase.init()
  @Before 
  def resetState = { // Cette fonction est lancé avant chaque fonctions @Test
    MachineImpl.reinit // Reset la langue en francais et le mode de dialogue par defaut
  }

  @Test
  def nomDeLaFonctionATester1 {
    assertEquals(
      MachineImpl.ask("Où est donc la Mairie de Rennes?"),
      List("L'adresse de Mairie de Rennes est : Place de la Mairie"))
  }

  @Test
  def nomDeLaFonctionATester2 {
    assertEquals(
      MachineImpl.ask("Salut"),
      List("Bonjour"))
  }
}