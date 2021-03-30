package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class AnalyseSentence {
  // initialisation des objets sous test
  DataBase.init()

  val Francais = DataBase.getLanguages()(0)
  val Anglais = DataBase.getLanguages()(1)
  val Espagnol = DataBase.getLanguages()(2)
  val Allemand = DataBase.getLanguages()(3)
  val Italien = DataBase.getLanguages()(4)

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
   *  Avec des mots contenu dans la liste des alias séparé de ";" (la partie gauche de "doc/alias.txt")
   *  Avec des mots non contenu dans la liste des alias séparé de ";" (la partie gauche de "doc/alias.txt")
   */

  /*
   * Liste vide
   */
  @Test
  def findKeys {
    assertEquals(AnalyseSentence.findKeysFromWords(List()), (Set()))
  }

  /*
     * Mot vide
     */
  @Test
  def findKeys2 {
    assertEquals(AnalyseSentence.findKeysFromWords(List("")), (Set()))
  }

  /*
    * Mot contenu dans la liste des alias
    */
  @Test
  def findKeys3 {
    assertEquals(AnalyseSentence.findKeysFromWords(List("gare")), (Set("gare sncf")))
  }

  /*
    * Mot non contenu dans la liste des alias
    */
  @Test
  def findKeys4 {
    assertEquals(AnalyseSentence.findKeysFromWords(List("stade")), (Set()))
  }

  /*
    * Mot séparé par ; dans la liste des alias
    */
  @Test
  def findKeys5 {
    assertEquals(AnalyseSentence.findKeysFromWords(List("gare", "sncf")), (Set("gare sncf")))
  }

  /*
    * Mot séparé par ; dans la liste des alias
    */
  @Test
  def findKeys6 {
    assertEquals(AnalyseSentence.findKeysFromWords(List("théâtre", "national", "bretagne")), (Set("Théâtre National de Bretagne")))
  }

  /*
    * Mot séparé par ; non contenu dans la liste des alias
    */
  @Test
  def findKeys7 {
    assertEquals(AnalyseSentence.findKeysFromWords(List("stade", "foot")), (Set()))
  }

  //TODO TEST DE LA FONCTION : AnalyseSentence.getMajorLanguage()

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

  /**
   * Liste Vide
   */
  @Test
  def getMajorLanguage {
    assertEquals(AnalyseSentence.getMajorLanguage(List()), null)
  }

  /**
   * mot qui n'est pas dans la liste
   */
  @Test
  def getMajorLanguage2 {
    assertEquals(AnalyseSentence.getMajorLanguage(List("crêpes")), null)
  }

  /**
   * mots qui ne sont pas dans la liste
   */
  @Test
  def getMajorLanguage3 {
    assertEquals(AnalyseSentence.getMajorLanguage(List("barbecue", "valorant")), null)
  }

  /**
   * mot qui n'est pas dans la liste
   */
  @Test
  def getMajorLanguage4 {
    assertEquals(AnalyseSentence.getMajorLanguage(List("foot", "batiment 28", "etoile", "vilaine")), null)
  }

  /**
   * 1 mot d'une seule langue (FR)
   */
  @Test
  def getMajorLanguage5 {
    assertEquals(AnalyseSentence.getMajorLanguage(List("bonjour")), DataBase.getLanguages()(0))
  }
  /**
   * 1 mot d'une seule langue (EN)
   */
  @Test
  def getMajorLanguage6 {
    assertEquals(AnalyseSentence.getMajorLanguage(List("hello")), DataBase.getLanguages()(1))
  }
  /**
   * 1 mot d'une seule langue (ES)
   */
  @Test
  def getMajorLanguage7 {
    assertEquals(AnalyseSentence.getMajorLanguage(List("hola")), DataBase.getLanguages()(2))
  }

  /**
   * 1 mot d'une seule langue (AL)
   */
  @Test
  def getMajorLanguage8 {
    assertEquals(AnalyseSentence.getMajorLanguage(List("hallo")), DataBase.getLanguages()(3))

  }
  /**
   * 1 mot d'une seule langue (IT)
   */
  @Test
  def getMajorLanguage9 {
    assertEquals(AnalyseSentence.getMajorLanguage(List("buongiorno")), DataBase.getLanguages()(4))
  }

  /**
   * 1 mot d'une seule langue et mot random
   */
  @Test
  def getMajorLanguage10 {
    assertEquals(AnalyseSentence.getMajorLanguage(List("bonjour", "crêpes")), DataBase.getLanguages()(0))
  }

  /**
   * 1 mot de langue1 et 2 mots de langue2
   */
  @Test
  def getMajorLanguage11 {
    assertEquals(AnalyseSentence.getMajorLanguage(List("bonjour", "hola", "donde")), DataBase.getLanguages()(2))
  }

  /**
   * 1 mot de langue1 et 2 mots de langue2 3 mots langue3
   */
  @Test
  def getMajorLanguage12 {
    assertEquals(AnalyseSentence.getMajorLanguage(List("bonjour", "hola", "donde", "esta", "hallo", "ist")), DataBase.getLanguages()(2))
  }
  //TODO TEST DE LA FONCTION : AnalyseSentence.getLanguageIfSearching()

  /**
   * Cas à tester :
   *
   * - Avec une liste vide
   * - Avec un mot contenu dans la liste des language keywords de recherche
   * - Avec aucun mot contenu dans la liste des language keywords de recherche
   * - Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de recherche de même langue
   */

  // Avec une liste vide
  @Test
  def getLanguageIfSearching0 {
    val inputs = List()
    assertEquals(AnalyseSentence.getLanguageIfSearching(inputs), None)
  }

  //Avec un mot contenu dans la liste des language keywords de recherche
  @Test
  def getLanguageIfSearching1 {
    val inputs = List("motRandom1", "recherche", "motRandom2", "motRandom3", "motRandom4")
    assertEquals(AnalyseSentence.getLanguageIfSearching(inputs), Some(Francais))
  }

  @Test
  def getLanguageIfSearching2 {
    val inputs = List("motRandom1", "search", "motRandom2", "motRandom3", "motRandom4")
    assertEquals(AnalyseSentence.getLanguageIfSearching(inputs), Some(Anglais))
  }

  //Avec aucun mot contenu dans la liste des language keywords de recherche

  @Test
  def getLanguageIfSearching3 {
    val inputs = List("motRandom1", "motRandom2", "motRandom3", "motRandom4")
    assertEquals(AnalyseSentence.getLanguageIfSearching(inputs), None)
  }

  //Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de recherche de même langue
  @Test
  def getLanguageIfSearching4 {
    val inputs = List("motRandom1", "recherche", "motRandom2", "trouve", "motRandom4")
    assertEquals(AnalyseSentence.getLanguageIfSearching(inputs), Some(Francais))
  }

  //TODO TEST DE LA FONCTION : AnalyseSentence.getLanguageIfPolite()

  /**
   * Cas à tester :
   *
   * - Avec une liste vide
   * - Avec un mot contenu dans la liste des language keywords de politesse
   * - Avec aucun mot contenu dans la liste des language keywords de politesse
   * - Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de politesse de même langue
   */
  //Avec une liste vide
  @Test
  def getLanguageIfPolite1 {
    val inputs = List()
    assertEquals(AnalyseSentence.getLanguageIfPolite(inputs), None)
  }
  
  //Avec un mot contenu dans la liste des language keywords de politesse
  @Test
  def getLanguageIfPolite2 {
    val inputs = List("motRandom1", "Hi", "motRandom2", "motRandom4")
    assertEquals(AnalyseSentence.getLanguageIfPolite(inputs), Some(Anglais))
  }
  
  //Avec aucun mot contenu dans la liste des language keywords de politesse
  @Test
  def getLanguageIfPolite3 {
    val inputs = List("motRandom1", "motRandom2","motRandom3", "motRandom4")
    assertEquals(AnalyseSentence.getLanguageIfPolite(inputs), None)
  }
  
  //- Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de politesse de même langue
  @Test
  def getLanguageIfPolite4 {
    val inputs = List("motRandom1", "Bonjour","motRandom3","Bonsoir", "motRandom4")
    assertEquals(AnalyseSentence.getLanguageIfPolite(inputs), Some(Francais))
  }
  
  //TODO TEST DE LA FONCTION : AnalyseSentence.hasPoliteWord()

  /**
   * Cas à tester :
   *
   * - Avec une liste vide
   * - Avec un mot contenu dans la liste des language keywords
   * - Avec aucun mot contenu dans la liste des language keywords
   * - Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de même langue
   */
  //Avec une liste vide
  @Test
  def hasPoliteWord1 {
    val inputs = List()
    assertEquals(AnalyseSentence.hasPoliteWord(inputs), false)
  }
  
  //Avec un mot contenu dans la liste des language keywords
  @Test
  def hasPoliteWord2 {
    val inputs = List("motRandom1", "Hallo", "motRandom2", "motRandom4")
    assertEquals(AnalyseSentence.hasPoliteWord(inputs), true)
  }
  
  //Avec aucun mot contenu dans la liste des language keywords
  @Test
  def hasPoliteWord3 {
    val inputs = List("motRandom1", "motRandom2", "motrandom3", "motRandom4")
    assertEquals(AnalyseSentence.hasPoliteWord(inputs), false)
  }
  
  //Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de même langue
  @Test
  def hasPoliteWord4 {
    val inputs = List("motRandom1", "Buenos","motRandom2","Dias","motrandom3", "motRandom4")
    assertEquals(AnalyseSentence.hasPoliteWord(inputs), true)
  }
  
  //TODO TEST DE LA FONCTION : AnalyseSentence.isLinternauteQuery()

  /**
   * Cas à tester :
   *
   * - Avec une liste vide
   * - Avec un mot contenu dans la liste des language keywords de linternaute
   * - Avec aucun mot contenu dans la liste des language keywords de linternaute
   * - Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de linternaute de même langue
   */
  
  //Avec une liste vide
  @Test
  def isLinternauteQuery1 {
    val inputs = List()
    assertEquals(AnalyseSentence.isLinternauteQuery(inputs), false)
  }
  
  //Avec un mot contenu dans la liste des language keywords de linternaute
  @Test
  def isLinternauteQuery2{
    val inputs = List("motRandom1", "ristorante", "motrandom3", "motRandom4")
    assertEquals(AnalyseSentence.isLinternauteQuery(inputs), true)
  }
  
  //Avec aucun mot contenu dans la liste des language keywords de linternaute
  @Test
  def isLinternauteQuery3{
    val inputs = List("motRandom1", "motrandom2", "motrandom3", "motRandom4")
    assertEquals(AnalyseSentence.isLinternauteQuery(inputs), false)
  }
  
  //Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de linternaute de même langue
  @Test
  def isLinternauteQuery4{
    val inputs = List("motRandom1", "creperie", "motrandom3", "motRandom4","pizzeria")
    assertEquals(AnalyseSentence.isLinternauteQuery(inputs), true)
  }
  
  
  //TODO TEST DE LA FONCTION : AnalyseSentence.isBlagueQuery()

  /**
   * Cas à tester :
   *
   * - Avec une liste vide
   * - Avec un mot contenu dans la liste des language keywords de blagues
   * - Avec aucun mot contenu dans la liste des language keywords de blagues
   * - Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de blagues de même langue
   */
  
  //Avec une liste vide
  @Test
  def isBlagueQuery1 {
    val inputs = List()
    assertEquals(AnalyseSentence.isBlagueQuery(inputs), false)
  }
  
  //Avec un mot contenu dans la liste des language keywords de blagues
  @Test
  def isBlagueQuery2 {
    val inputs = List("motRandom1", "Witz", "motrandom3", "motRandom4")
    assertEquals(AnalyseSentence.isBlagueQuery(inputs), true)
  }
  
  //Avec aucun mot contenu dans la liste des language keywords de blagues
  @Test
  def isBlagueQuery3 {
    val inputs = List("motRandom1", "motrandom2", "motrandom3", "motRandom4")
    assertEquals(AnalyseSentence.isBlagueQuery(inputs), false)
  }
  
   //Avec plusieurs mots dont un qui est contenu dans la liste des language keywords de blagues de même langue
  @Test
  def isBlagueQuery4 {
    val inputs = List("motRandom1", "barzelletta", "motrandom3", "battuta","motRandom4")
    assertEquals(AnalyseSentence.isBlagueQuery(inputs), true)
  }

  //TODO TEST DE LA FONCTION : AnalyseSentence.getWords()

  /**
   * Cas à tester :
   *
   * - Avec un mot vide
   * - Avec deux mots séparé d'un charactère séparateur (" ,'.;:!?¿-_")
   * - Avec un mot suivis de charactère séparateur à la fin (" ,'.;:!?¿-_")
   * - Avec plusieurs mots séparés de charactère séparateur (" ,'.;:!?¿-_")
   */
  
  //Avec un mot vide
  @Test
  def getWords1 {
    val inputs = ""
    assertEquals(AnalyseSentence.getWords(inputs), List(""))
  }
  
  //Avec deux mots séparé d'un charactère séparateur (" ,'.;:!?¿-_")
  @Test
  def getWords2 {
    val inputs = "je:suis"
    assertEquals(AnalyseSentence.getWords(inputs), List("je","suis"))
  }
  
  //Avec un mot suivis de charactère séparateur à la fin (" ,'.;:!?¿-_")
  @Test
  def getWords3 {
    val inputs = "je!"
    assertEquals(AnalyseSentence.getWords(inputs), List("je"))
  }
  
  //Avec plusieurs mots séparés de charactère séparateur (" ,'.;:!?¿-_")
  @Test
  def getWords4 {
    val inputs = "nous;sommes!content?de_coder"
    assertEquals(AnalyseSentence.getWords(inputs), List("nous","sommes","content","de","coder"))
  }


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