package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class StateManager {
  
  @Before
  def resetState = {
    MachineImpl.reinit
  }
  
  //TODO TEST DE LA FONCTION : StateManager.getNextLanguage()
  
  /* 
   Cas à tester :
   
   - si le currentLanguage est le premier de la liste rend bien le second de la liste
   - si current Language est le dernier de la liste rend bien le premier de la liste
   - si l'ordre est bien français, anglais, espagnol, allemand, italien, puis de nouveau français, anglais...
  */
}