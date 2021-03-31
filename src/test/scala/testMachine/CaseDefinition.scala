package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class CaseDefinition {
  
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }
  
  @Test
  def ask1(){
    assertEquals(MachineImpl.ask(""), List("Je ne comprends pas votre demande"))
  }
  
  @Test
  def ask2(){
    assertEquals(MachineImpl.ask("définition"), List("Je ne comprends pas votre demande"))
  }
  
  @Test
  def ask3(){
    assertEquals(MachineImpl.ask("définition de kayak"), List("Embarcation individuelle des Inuits, à carcasse de bois recouverte de peaux cousues qui entourent l'emplacement du rameur.", "Embarcation propulsée à l'aide d'une pagaie double et dont l'étanchéité est assurée par une jupe imperméable serrée autour de la taille du pagayeur"))
  }
  
}