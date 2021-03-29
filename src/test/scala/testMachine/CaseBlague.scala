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
  
 val blagues : List[List[String]] = List(List("Quelle mamie fait peur aux voleurs ?" + "   \n\n" +  "Mamie Traillette"), 
                              List("Pourquoi est-ce qu'on met tous les crocos en prison ?"+ "   \n\n" +"Parce que les crocos dealent."),
                              List("Qu'est-ce qu'un tennisman adore faire ?"+ "   \n\n" +"Rendre des services"),
                              List("Que se passe-t-il quand 2 poissons s'énervent ?"+ "   \n\n" +"Le thon monte"),
                              List("Que fait une lampe quand elle se fait agresser ?"+ "   \n\n" +"Elle crie a LED"))
  
  
  @Test
  def ask(){
     StateManager.currentLanguage = DataBase.getLanguages()(0)
    assertTrue(blagues.contains(MachineImpl.ask("blague")))
  }
 
 @Test
  def ask13(){
     StateManager.currentLanguage = DataBase.getLanguages()(0)
    assertTrue(blagues.contains(MachineImpl.ask("plasanterie")))
  }
 
 //marche car le langage courrant est français de base
 
// Anglais
   
   val jokes : List[List[String]] = List(List("A woman in labour suddenly shouted, “Shouldn’t! Wouldn’t! Couldn’t! Didn’t! Can’t!”" + "   \n\n" +  "“Don’t worry,” said the doctor. “Those are just contractions.”"), 
                                        List("Did you hear about the claustrophobic astronaut?"+ "   \n\n" +"He just needed a little space."),
                                        List("A man tells his doctor, “Doc, help me. I’m addicted to Twitter!”"+ "   \n\n" +"The doctor replies, “Sorry, I don’t follow you…”"))
 
  
  
  
  @Test
  def ask2(){
     StateManager.currentLanguage = DataBase.getLanguages()(1)
    assertTrue(jokes.contains(MachineImpl.ask("joke")))
  }
   
  @Test
  def ask12(){
     StateManager.currentLanguage = DataBase.getLanguages()(1)
    assertTrue(jokes.contains(MachineImpl.ask("jokes")))
  }
   
   // Espagnol
   
   val bromas : List[List[String]] = List(List("- Soy Rosa." + "   \n\n" +  "- Ah, perdóname, es que soy daltónico."), 
                                        List("- Oye, ¿cuál es tu plato favorito y por qué?"+ "   \n\n" +"- Pues el hondo, porque cabe más comida…"),
                                        List("¿Qué pasa si tiras un pato al agua?."+ "   \n\n" +"Nada."),
                                        List("- Papá, ¿qué está más lejos, Córdoba o la Luna?."+ "   \n\n" +"- Pero vamos a ver, ¿tú ves desde aquí Córdoba?"))
 
  @Test
  def ask3(){
     StateManager.currentLanguage = DataBase.getLanguages()(2)
    assertTrue(bromas.contains(MachineImpl.ask("broma")))
  }
   
  @Test
  def ask10(){
     StateManager.currentLanguage = DataBase.getLanguages()(2)
    assertTrue(bromas.contains(MachineImpl.ask("chanca")))
  }
  
  @Test
  def ask11(){
     StateManager.currentLanguage = DataBase.getLanguages()(2)
    assertTrue(bromas.contains(MachineImpl.ask("chistes")))
  }
   
   // Allemand
   
   val scherzs : List[List[String]] = List(List("Was ist gefährlich?" + "   \n\n" +  "Niesen bei Durchfall."), 
                                        List("Welche Hunderasse isst mit den Ohren?"+ "   \n\n" +"- Alle. Oder hast du schon mal einen Hund gesehen, der vor dem Essen die Ohren abnimmt."))
 //Il manque la 3ème blague parce que les guillemets changent la phrase donc le test ne marche pas pour cette blague
                                        
  @Test
  def ask4(){
     StateManager.currentLanguage = DataBase.getLanguages()(3)
    assertTrue(scherzs.contains(MachineImpl.ask("scherz")))
  }
   
     @Test
  def ask8(){
     StateManager.currentLanguage = DataBase.getLanguages()(3)
    assertTrue(scherzs.contains(MachineImpl.ask("wits")))
  }
     
       @Test
  def ask9(){
     StateManager.currentLanguage = DataBase.getLanguages()(3)
    assertTrue(scherzs.contains(MachineImpl.ask("straich")))
  }
   
   // Italien
   
   val barzelletta : List[List[String]] = List(List("Una moglie dice a suo marito, “Caro, sono incinta! Cosa vorresti che fosse?" + "   \n\n" +  "Il marito risponde, “Uno scherzo!”"), 
                                        List("Come si chiama un boomerang che non torna?"+ "   \n\n" +"Un bastone!"),
                                        List("Un cervello entra in un bar e dice al barista, “Una birra, per favore.”"+ "   \n\n" +"Il barista dice, “Mi dispiace, non posso servirti. Sei fuori di testa.”"))
 
  
  
  
  @Test
  def ask5(){
     StateManager.currentLanguage = DataBase.getLanguages()(4)
    assertTrue(barzelletta.contains(MachineImpl.ask("barzelletta")))
  }
   
     @Test
  def ask6(){
     StateManager.currentLanguage = DataBase.getLanguages()(4)
    assertTrue(barzelletta.contains(MachineImpl.ask("Storiela")))
  }
     
       @Test
  def ask7(){
     StateManager.currentLanguage = DataBase.getLanguages()(4)
    assertTrue(barzelletta.contains(MachineImpl.ask("batutà")))
  }
}