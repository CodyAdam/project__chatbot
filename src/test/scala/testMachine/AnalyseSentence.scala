package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class AnalyseSentence {
  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TODO TEST DE LA FONCTION : AnalyseSentence.findKeysFromWords()

  /**
   * Cas à tester :
   *
   *  Avec une liste vide
   *  Avec un mot contenu dans la liste des alias (la partie gauche de "doc/alias.txt")
   *  Avec un mot non contenu dans la liste des alias (la partie gauche de "doc/alias.txt")
   * - Avec des mots contenu dans la liste des alias séparé de ";" (la partie gauche de "doc/alias.txt")
   * - Avec des mots non contenu dans la liste des alias séparé de ";" (la partie gauche de "doc/alias.txt")
   */

  
  //TODO TEST DE LA FONCTION : AnalyseSentence.getMajorLanguage()
  
  /*
   * Liste vide
   */
    @Test
  def findKeys{
     assertEquals(AnalyseSentence.findKeysFromWords(List()), (Set()))
   }
  
    /*
     * Mot vide
     */
   @Test
  def findKeys2{
     assertEquals(AnalyseSentence.findKeysFromWords(List("")), (Set()))
   }
   
   /*
    * Mot contenu dans la liste des alias
    */
   @Test
  def findKeys3{
     assertEquals(AnalyseSentence.findKeysFromWords(List("gare")), (Set("gare sncf")))
   }
   
   /*
    * Mot non contenu dans la liste des alias
    */
   @Test
  def findKeys4{
     assertEquals(AnalyseSentence.findKeysFromWords(List("stade")), (Set()))
   }

   
   /*
    * Mot séparé par ; dans la liste des alias
    */
   @Test
  def findKeys5{
     assertEquals(AnalyseSentence.findKeysFromWords(List("gare","sncf")), (Set("gare sncf")))
   }
   
   
    /*
    * Mot séparé par ; dans la liste des alias
    */
   @Test
  def findKeys6{
     assertEquals(AnalyseSentence.findKeysFromWords(List("théâtre","national","bretagne")), (Set("Théâtre National de Bretagne")))
   }
   
   
    /*
    * Mot séparé par ; non contenu dans la liste des alias
    */
   @Test
  def findKeys7{
     assertEquals(AnalyseSentence.findKeysFromWords(List("stade","foot")), (Set()))
   }
   
  /**
   * Cas à tester :
   *
   * - Avec une liste vide
   * - Avec un mot qui n'est pas dans la liste des language keywords
   * - Avec des mots qui ne sont pas dans la liste des language keywords
   * - Avec 1 mot de 1 seul langue
   * - Avec 1 mot de 1 seul langue et d'autre mots random
   * - Avec 1 mot de langue1 et 2 mots de langue2
   */

  //TODO TEST DE LA FONCTION : AnalyseSentence.getLanguageIfSearching()

  /**
   * Cas à tester :
   *
   * - Avec une liste vide
   * - Avec un mot contenu dans la liste des language keywords de recherche
   * - Avec aucun mot contenu dans la liste des language keywords de recherche
   * - Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de recherche de même langue
   */

  //TODO TEST DE LA FONCTION : AnalyseSentence.getLanguageIfPolite()

  /**
   * Cas à tester :
   *
   * - Avec une liste vide
   * - Avec un mot contenu dans la liste des language keywords de politesse
   * - Avec aucun mot contenu dans la liste des language keywords de politesse
   * - Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de politesse de même langue
   */

  //TODO TEST DE LA FONCTION : AnalyseSentence.hasPoliteWord()

  /**
   * Cas à tester :
   *
   * - Avec une liste vide
   * - Avec un mot contenu dans la liste des language keywords
   * - Avec aucun mot contenu dans la liste des language keywords
   * - Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de même langue
   */

  //TODO TEST DE LA FONCTION : AnalyseSentence.isLinternauteQuery()

  /**
   * Cas à tester :
   *
   * - Avec une liste vide
   * - Avec un mot contenu dans la liste des language keywords de linternaute
   * - Avec aucun mot contenu dans la liste des language keywords de linternaute
   * - Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de linternaute de même langue
   */

  //TODO TEST DE LA FONCTION : AnalyseSentence.isBlagueQuery()

  /**
   * Cas à tester :
   *
   * - Avec une liste vide
   * - Avec un mot contenu dans la liste des language keywords de blagues
   * - Avec aucun mot contenu dans la liste des language keywords de blagues
   * - Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de blagues de même langue
   */

  //TODO TEST DE LA FONCTION : AnalyseSentence.getWords()

  /**
   * Cas à tester :
   *
   * - Avec un mot vide
   * - Avec deux mots séparé d'un charactère séparateur (" ,'.;:!?¿-_")
   * - Avec un mot suivis de charactère séparateur à la fin (" ,'.;:!?¿-_")
   * - Avec deux mots séparé de plusieurs charactères séparateurs (" ,'.;:!?¿-_")
   */

  //TODO TEST DE LA FONCTION : AnalyseSentence.isEqualsWithTypingError()

  /**
   * Cas à tester :
   *
   * - Avec un mot vide et un mot vide
   * - Avec un mot vide et un charactère
   * - Avec deux mots proche de 1 lettre
   * - Avec deux mots proche avec l'un qui à une lettre en plus ou en moins
   * - Avec deux mots proche avec l'un qui n'à pas d'accent
   * - Avec deux mots proche avec l'un qui est en minusclue et l'autre en majuscule
   */

  //TODO TEST DE LA FONCTION : AnalyseSentence.containsWithTypingError()

  /**
   * Cas à tester :
   *
   * - Avec une liste vide et un mot random
   * - Avec une liste de mots random et un mot vide
   * - Avec une liste de mots random et un mot qui est dans la liste
   * - Avec une liste de mots random et un mot qui est dans la liste mais avec des fautes
   */
}