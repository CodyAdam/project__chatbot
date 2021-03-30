package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class CaseMulti {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TODO TESTER LA FONCTIONNALITÉ : F6 du tp avec les choix multiples avec la fonction MachineImpl.ask()
  
  /**
   * Cas à tester :
   * 
   * - avec un mot qui renvoi X résultats et on choisi un nombre entre 1 et X
   * - avec un mot qui renvoi X résultats et on choisi un nombre supérieur à X
   * - avec un mot qui renvoi X résultats et on choisi autre chose qu'un nombre
   * - avec un mot qui renvoi X résultats et on choisi un nombre entre 1 et X qui est introduit dans une phrase
   */
  
  /**
   * avec un mot qui renvoi X résultats et on choisi un nombre entre 1 et X
   */
  @Test
  def multiChoix1() = {
    assertEquals(MachineImpl.ask("Ou est la piscine"), List("J'ai 4 réponses possibles : <br/> 1) Piscine Bréquigny <br/> 2) Piscine Gayeulles <br/> 3) Piscine Saint-Georges <br/> 4) Piscine Villejean <br/> "))
    assertEquals(MachineImpl.ask("3"), List("L'adresse de Piscine Saint-Georges est : 4, RUE GAMBETTA"))
  }
  
    /**
   * avec un mot qui renvoi X résultats et on choisi un nombre supérieur à X
   */
  @Test
  def multiChoix2() = {
    assertEquals(MachineImpl.ask("Ou est la piscine"), List("J'ai 4 réponses possibles : <br/> 1) Piscine Bréquigny <br/> 2) Piscine Gayeulles <br/> 3) Piscine Saint-Georges <br/> 4) Piscine Villejean <br/> "))
    assertEquals(MachineImpl.ask("5"), List("Je ne comprends pas votre demande"))
  }
  
  
    /**
   * avec un mot qui renvoi X résultats et on choisi autre chose qu'un nombre
   */
  @Test
  def multiChoix3() = {
    assertEquals(MachineImpl.ask("Ou est la piscine"), List("J'ai 4 réponses possibles : <br/> 1) Piscine Bréquigny <br/> 2) Piscine Gayeulles <br/> 3) Piscine Saint-Georges <br/> 4) Piscine Villejean <br/> "))
    assertEquals(MachineImpl.ask("baignade"), List("Je ne comprends pas votre demande"))
  }
  
    /**
   * avec un mot qui renvoi X résultats et on choisi un nombre entre 1 et X qui est introduit dans une phrase
   */
  @Test
  def multiChoix4() = {
    assertEquals(MachineImpl.ask("Ou est la piscine"), List("J'ai 4 réponses possibles : <br/> 1) Piscine Bréquigny <br/> 2) Piscine Gayeulles <br/> 3) Piscine Saint-Georges <br/> 4) Piscine Villejean <br/> "))
    assertEquals(MachineImpl.ask("Je cherche la 4 "), List("L'adresse de Piscine Villejean est : 1, SQUARE D'ALSACE"))
  }
 


}