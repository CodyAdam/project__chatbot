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

  // Français :

  @Test
  def ask() {
    StateManager.currentLanguage = DataBase.getLanguages()(0)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(DataBase.getJoke()))
  }

  // Anglais

  @Test
  def ask2() {
    StateManager.currentLanguage = DataBase.getLanguages()(1)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(DataBase.getJoke()))
    assertTrue(DataBase.getJokesCurrentLang.list.contains(DataBase.getJoke()))
    assertTrue(DataBase.getJokesCurrentLang.list.contains(DataBase.getJoke()))
  }

  // Espagnol

  @Test
  def ask3() {
    StateManager.currentLanguage = DataBase.getLanguages()(2)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(DataBase.getJoke()))
  }

    // Allemand

    @Test
    def ask4() {
      StateManager.currentLanguage = DataBase.getLanguages()(3)
      assertTrue(DataBase.getJokesCurrentLang.list.contains(DataBase.getJoke()))
    }

    // Italien
    @Test
    def ask5() {
      StateManager.currentLanguage = DataBase.getLanguages()(4)
      assertTrue(DataBase.getJokesCurrentLang.list.contains(DataBase.getJoke()))
    }

  }
