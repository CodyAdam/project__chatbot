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

  // Passe de français à anglais
  @Test
  def ask{
	     assertEquals(MachineImpl.ask("Hello"), List("Do you speak english?"))
  }
  
  // Passe d'espagnol à anglais
  @Test
  def ask2{
    StateManager.currentLanguage = DataBase.getLanguages()(2)
	     assertEquals(MachineImpl.ask("Hello"), List("Do you speak english?"))
  }
  
  // Fais le tour des langues quand l'utilisateur répond toujours non
  @Test
  def ask3{
	     assertEquals(MachineImpl.ask("Hello"), List("Do you speak english?"))
	     assertEquals(MachineImpl.ask("No"), List("Hablas español?"))
	     assertEquals(MachineImpl.ask("No"), List("Sprechen Sie Deutsch?"))
	     assertEquals(MachineImpl.ask("No"), List("Parli italiano?"))
	     assertEquals(MachineImpl.ask("No"), List("Parlez-vous français?"))
	     assertEquals(MachineImpl.ask("No"), List("Do you speak english?"))
  }
  
  // Répond oui dans la mauvaise langue puis répond dans la bonne langue
  @Test
  def ask4{
	     assertEquals(MachineImpl.ask("Hello"), List("Do you speak english?"))
	     assertEquals(MachineImpl.ask("No"), List("Hablas español?"))
	     assertEquals(MachineImpl.ask("oui"), List("Sprechen Sie Deutsch?"))
	     assertEquals(MachineImpl.ask("ja"), List("Okay, was ist Ihr Wunsch?"))
  }
}