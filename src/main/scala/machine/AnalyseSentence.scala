package machine
import util.control.Breaks._
import scala.collection.mutable
import scala.collection.parallel.ParSeq
import org.apache.commons.lang3.StringUtils.stripAccents
import scala.collection.mutable.HashMap

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
   * if multiples words from different languages found, return one of the
   * languages without preferences
   */
  def getLanguageIfPolite(words: List[String]): Option[Language] = {
    for (language: Language <- DataBase.getLanguages())
      for (politeWord: String <- language.politesse)
        if (containsWithTypingError(words, politeWord))
          return Some(language)
    return None
  }

  def hasPoliteWord(words: List[String]): Boolean = {
    for (language: Language <- DataBase.getLanguages())
      for (politeWord: String <- language.politesse)
        if (containsWithTypingError(words, politeWord))
          return true
    return false
  }

  /**
   * @param words from the user sentence
   * @return the langage with most words from the sentence
   */
  def getMajorLanguage(words: List[String]): Language = {
    var hash: HashMap[Language, Integer] = new HashMap();
    for (w <- words) {
      var ref = DataBase.getWordsLanguage().get(w);
      ref match {
        case None => {}
        case Some(set) => {
          for (lang <- set) {
            hash.get(lang) match {
              case None => {
                hash.put(lang, 1);
              }
              case Some(set) => hash(lang) = hash(lang) + 1;
            }
          }
        }
      }
    }
    var max: Language = null;
    var maxInt: Integer = 0;
    hash.foreach {
      case (key, value) => {
        if (value > maxInt) {
          max = key;
          maxInt = value;
        } else if (value == maxInt) {
          if (key == StateManager.currentLanguage) {
            max = key;
            maxInt = value;
          }
        }
      }
    }
    return max;
  }

  /**
   * @param words from the user sentence
   * @return the language if a search language keyword is found
   */
  def getLanguageIfSearching(words: List[String]): Option[Language] = {
    for (language: Language <- DataBase.getLanguages())
      for (politeWord: String <- language.recherche ++ List(language.langue)) {
        if (containsWithTypingError(words, politeWord))
          return Some(language)
      }
    return None
  }

  /**
   * @param words from the user sentence
   * @return if the user input contains a Linternaute triggerword in the current language
   */
  def isLinternauteQuery(words: List[String]): Boolean = {
    for (language: Language <- DataBase.getLanguages())
      for (internauteWord: String <- language.linternauteTrigger)
        if (containsWithTypingError(words, internauteWord))
          return true
    return false
  }

  /**
   * @param words from the user sentence
   * @return if the user input contains a joke triggerword in the current language
   */
  def isBlagueQuery(words: List[String]): Boolean = {
    for (language: Language <- DataBase.getLanguages())
      for (jokeWord: String <- language.blagueTrigger)
        if (containsWithTypingError(words, jokeWord))
          return true
    return false
  }

  /**
   * @param words from the user sentence
   * @return if the user input contains a definition triggerword in the current language
   */
  def isDefinitionQuery(words: List[String]): Boolean = {
    for (language: Language <- DataBase.getLanguages())
      for (definitionWord: String <- language.definitionTrigger)
        if (containsWithTypingError(words, definitionWord))
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
   *
   * @note L’avatar tolère l’oubli d’accents, majuscules, ou de mots de liaison.
   * @note L’avatar accepte au plus une erreur de frappe par mot cle (une lettre soit manquante, soit erronee)
   */
  def isEqualsWithTypingError(str1: String, str2: String): Boolean = {
    var s1: String = str1.toLowerCase();
    var s2: String = str2.toLowerCase();
    s1 = stripAccents(s1);
    s2 = stripAccents(s2);
    if (s2.length <= 2) return s1 == s2;
    return levenshtein(s1, s2) <= 1;
  }

  /**
   * @param s1 a string
   * @param s2 a string
   * @return the levenhstein distance between s1 & s2
   * @note SOURCE ALGO : https://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance
   */
  def levenshtein(s1: String, s2: String): Int = {
    val memorizedCosts = mutable.Map[(Int, Int), Int]()
    def lev: ((Int, Int)) => Int = {
      case (k1, k2) =>
        memorizedCosts.getOrElseUpdate((k1, k2), (k1, k2) match {
          case (i, 0) => i
          case (0, j) => j
          case (i, j) =>
            ParSeq(
              1 + lev((i - 1, j)),
              1 + lev((i, j - 1)),
              lev((i - 1, j - 1))
                + (if (s1(i - 1) != s2(j - 1)) 1 else 0)).min
        })
    }
    lev((s1.length, s2.length))
  }

  /**
   * split les mots d'une phrase en une liste de mots
   * les charactères séparateurs sont : " ,'.;:!?¿-_"
   * @param une phrase
   * @return les mots de la phrase sentence dans une liste
   */
  def getWords(sentence: String): List[String] = {
    "( +)|(,+)|('+)|(\\.+)|(;+)|(:+)|(!+)|(\\?+)|(¿+)|(-+)|(_+)".r.split(sentence).toList
  }
}