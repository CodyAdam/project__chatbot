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
  def ask(){
     StateManager.currentLanguage = DataBase.getLanguages()(0)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("blague")))
  }
 
  @Test
  def ask13(){
     StateManager.currentLanguage = DataBase.getLanguages()(0)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("plasanterie")))
  }
 
// Anglais

  
  @Test
  def ask2(){
     StateManager.currentLanguage = DataBase.getLanguages()(1)
      assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("joke")))
  }
   
  @Test
  def ask12(){
     StateManager.currentLanguage = DataBase.getLanguages()(1)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("jokes")))
  }
   
   // Espagnol

  @Test
  def ask3(){
     StateManager.currentLanguage = DataBase.getLanguages()(2)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("broma")))
  }
   
  @Test
  def ask10(){
     StateManager.currentLanguage = DataBase.getLanguages()(2)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("chanca")))
  }
  
  @Test
  def ask11(){
     StateManager.currentLanguage = DataBase.getLanguages()(2)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("chistes")))
  }
   
   // Allemand
                                       
  @Test
  def ask4(){
     StateManager.currentLanguage = DataBase.getLanguages()(3)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("scherz")))
  }
   
     @Test
  def ask8(){
     StateManager.currentLanguage = DataBase.getLanguages()(3)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("wits")))
  }
     
       @Test
  def ask9(){
     StateManager.currentLanguage = DataBase.getLanguages()(3)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("straich")))
  }
   
   // Italien
  @Test
  def ask5(){
     StateManager.currentLanguage = DataBase.getLanguages()(4)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("barzelletta")))
  }
   
     @Test
  def ask6(){
     StateManager.currentLanguage = DataBase.getLanguages()(4)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("Storiela")))
  }
     
       @Test
  def ask7(){
     StateManager.currentLanguage = DataBase.getLanguages()(4)
    assertTrue(DataBase.getJokesCurrentLang.list.contains(MachineImpl.ask("batutà")))
  }
}