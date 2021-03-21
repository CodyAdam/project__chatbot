package machine
import util.control.Breaks._

object AnalyseSentence {
  /**
   * @param words une liste de mots
   * @return un ensemble de mots trouvé dans les alias de la base de donnees correpondant au mots clés à chercher
   */
  def findKeysFromWords(words: List[String]): Set[String] = {
    var possibleKeys: Set[List[String]] = Set[List[String]]()
    var keywords: Set[String] = Set[String]()

    var minNumberWord = 1;
    var incremented = false;

    do {
      possibleKeys = Set[List[String]]()
      incremented = false
      for (key: List[String] <- DataBase.getAlias.keys) {
        breakable {
          if (key.length < minNumberWord)
            break
          for (word: String <- key) {
            var containsWord = false
            words.foreach((sentenceWord: String) => {
              if (isEqualsWithTypingError(sentenceWord, word))
                containsWord = true
            })
            if (!containsWord)
              break
          }
          if (key.length > minNumberWord) {
            minNumberWord = key.length
            incremented = true
            break
          }
          possibleKeys += key
        }
      }
    } while (incremented)

    possibleKeys.foreach((key: List[String]) => {
      DataBase.getAlias.get(key) match {
        case Some(value) => {
          for (keyword: String <- value)
            keywords += keyword
        }
        case _ => Unit
      }
    })
    return keywords;
  }

  /**
   * @param words from the user sentence
   * @return the language if a polite language keyword is found
   */
  def getLanguageIfPolite(words: List[String]): Option[Language] = {
    for (language: Language <- DataBase.getLanguages())
      for (politeWord: String <- language.politesse)
        if (containsWithTypingError(words, politeWord))
          return Some(language)
    return None
  }

  /**
   * @param words from the user sentence
   * @return the language if a search language keyword is found
   */
  def getLanguageIfSearching(words: List[String]): Option[Language] = {
    for (language: Language <- DataBase.getLanguages())
      for (politeWord: String <- language.recherche)
        if (containsWithTypingError(words, politeWord))
          return Some(language)
    return None
  }

  /**
   * @param words from the user sentence
   * @return if the user input contains a Linternaute triggerword in the current language
   */
  def isLinternauteQuery(words: List[String]): Boolean = {
    for (politeWord: String <- StateManager.currentLanguage.linternauteTrigger)
      if (containsWithTypingError(words, politeWord))
        return true
    return false
  }

  /**
   * @param list a list of string
   * @param str a string
   * @return is str contained inside list or almost found with typing error
   */
  def containsWithTypingError(list: List[String], str: String): Boolean = {
    for (subStr: String <- list)
      if (isEqualsWithTypingError(subStr, str))
        return true
    return false
  }

  /**
   * @param str1 a string
   * @param str2 a string
   * @return is str1 and str2 almost the same with typing error
   */
  def isEqualsWithTypingError(str1: String, str2: String): Boolean = {
    // TODO inplement typing errors
    /*
     * L’avatar tolère l’oubli d’accents, majuscules, ou de mots de liaison.
     * L’avatar accepteau plus une erreur de frappepar mot cle (une lettre soit manquante, soit erronee)
     */
    return str1 == str2 //Temporaire
  }

  /**
   * split les mots d'une phrase en une liste de mots
   * @param une phrase
   * @return les mots de la phrase sentence dans une liste
   */
  def getWords(sentence: String): List[String] = {
    "( +)|(,+)|('+)|(\\.+)|(;+)|(:+)|(!+)|(\\?+)|(¿+)|(-+)|(_+)".r.split(sentence).toList
  }
}