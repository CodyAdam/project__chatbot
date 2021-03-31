package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class StateManager {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TODO TEST DE LA FONCTION : StateManager.getNextLanguage()

  /*
   Cas à tester :

   - quand le currentLanguage est le premier de la liste, rend bien le second de la liste
   - quand current Language est le dernier de la liste, rend bien le premier de la liste
   - l'ordre est bien français, anglais, espagnol, allemand, italien, puis de nouveau français, anglais...
  */
  
  //quand le currentLanguage est le premier de la liste, rend bien le second de la liste
  @Test
  def testgetNextLanguage1{
    StateManager.currentLanguage = DataBase.getLanguages()(0)
    StateManager.currentLanguage = StateManager.getNextLanguage()
    assertEquals(StateManager.currentLanguage, DataBase.getLanguages()(1))
  }
  
  //quand current Language est le dernier de la liste, rend bien le premier de la liste
  @Test
  def testgetNextLanguage2{
    StateManager.currentLanguage = DataBase.getLanguages()(DataBase.getLanguages().length-1)
    StateManager.currentLanguage = StateManager.getNextLanguage()
    assertEquals(StateManager.currentLanguage, DataBase.getLanguages()(0))
  }
  
  //l'ordre est bien français, anglais, espagnol, allemand, italien, puis de nouveau français, anglais...
  @Test
  def testgetNextLanguage3{
    StateManager.currentLanguage = DataBase.getLanguages()(0)
    assertEquals(StateManager.currentLanguage.langue, "Français")
    StateManager.currentLanguage = StateManager.getNextLanguage()
    assertEquals(StateManager.currentLanguage.langue, "English")
    StateManager.currentLanguage = StateManager.getNextLanguage()
    assertEquals(StateManager.currentLanguage.langue, "Español")
    StateManager.currentLanguage = StateManager.getNextLanguage()
    assertEquals(StateManager.currentLanguage.langue, "Deustch")
    StateManager.currentLanguage = StateManager.getNextLanguage()
    assertEquals(StateManager.currentLanguage.langue, "Italiano")
    StateManager.currentLanguage = StateManager.getNextLanguage()//retour au debut
    assertEquals(StateManager.currentLanguage.langue, "Français")
  }
}