package machine

import org.jsoup.Jsoup

object DefinitionLinternaute {

  /**
   * prend les mots de l'utilisateurs et supprime le trigger word de
   * l'internaute (ex : "restaurant") pour rendre uniquement les autres mots
   * @param words la liste de mots rentrés par l'utilisateur contient forcément au moins un
   * triggerword
   * @return le mot utilisé pour la recherche de définition sur l'Internaute
   */
  def searchingWord(words: List[String]): String = {
    var found = false
    for (unknownWord: String <- words) {
      if (found) {
        return unknownWord
      }
      if ((StateManager.currentLanguage.definitionTrigger).contains(unknownWord.toLowerCase())) {
        found = true
      }
    }
    ""
  }

  /**
   * Prends en entrée une chaîne de caractères et elnlève tous ses accents
   * @param une chaîne de caractères
   * @return la chaîne de caractères sans les accents
   */
  def ignoreAccent(s: String): String = {
    val sLower = s.toLowerCase()
    var withoutAccent = ""
    val accentTab = Map('á' -> 'a', 'à' -> 'a', 'â' -> 'a', 'ä' -> 'a', 'é' -> 'e', 'è' -> 'e', 'ê' -> 'e', 'ë' -> 'e',
      'ó' -> 'o', 'ò' -> 'o', 'ô' -> 'o', 'ö' -> 'o', 'í' -> 'i', 'ì' -> 'i', 'î' -> 'i', 'ï' -> 'i', 'ú' -> 'u', 'ù' -> 'u', 'û' -> 'u', 'ü' -> 'u')
    for (c: Char <- sLower) {
      if (accentTab.contains(c)) {
        accentTab.get(c) match {
          case None    => withoutAccent
          case Some(s) => withoutAccent = withoutAccent + s
        }
      } else {
        withoutAccent = withoutAccent + c
      }
    }
    withoutAccent
  }

  def lookingForDefinition(words: List[String]): String = {
    val unknownWord = searchingWord(words)
    println(unknownWord)
    if (!unknownWord.equals("")) {
      val r = Jsoup.connect("https://www.linternaute.fr/dictionnaire/fr/definition/" + unknownWord).get().select("div.grid_last > a")
      if (r != null)
        r.html
      else
        ""
    } else {
      ""
    }
  }

}

object TestAccent extends App {

  println(DefinitionLinternaute.lookingForDefinition(List("définition","théâtre")))
}