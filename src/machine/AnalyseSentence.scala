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

    for (key: List[String] <- BaseDonnees.getAlias.keys) {
      breakable {
        for (word: String <- key) {
          if (!words.contains(word))
            break
        }
        possibleKeys += key
      }
    }

    possibleKeys.foreach((key: List[String]) => {
      BaseDonnees.getAlias.get(key) match {
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
   * @param une phrase
   * @return les mots de la phrase sentence
   */
  def getWords(sentence: String): List[String] = "( +)|(,+)|('+)|(\\.+)|(;+)|(:+)|(!+)|(\\?+)|(¿+)".r.split(sentence).toList
}