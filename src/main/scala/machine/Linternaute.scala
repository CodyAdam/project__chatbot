package machine

import org.jsoup.Jsoup

object Linternaute {

  /**
   * prend les mots de l'utilisateurs et supprime le trigger word de
   * l'internaute (ex : "restaurant") pour rendre uniquement les autres mots
   * @param words la liste de mots rentrés par l'utilisateur contient forcément au moins un 
   * triggerword
   * @return les mots utilisés pour la recherche sur l'Internaute
   */
  def searchingWords(words: List[String]): List[String] = {
    var searchingWords: List[String] = List()
    var found = false
    for (politeWord: String <- words) {
      if (found) {
        searchingWords = searchingWords :+ politeWord
      }
      if ((StateManager.currentLanguage.linternauteTrigger).contains(politeWord.toLowerCase())) {
        found = true
      }
    }
    searchingWords
  }

  /**
   * Crée une chaîne de caractères à partir de la liste de mots à rechercher sur l'Internaute
   * avec des "+" entre chaques mots
   * @param une liste de chaîne de caractères non vide à rechercher
   * @return une chaîne de caractères à rechercher
   */
  def keyWords(searchingWords: List[String]): String = {
    searchingWords match {
      case Nil    => ""
      case e :: r => e + "+" + keyWords(r)
    }
  }

  /**
   * Isole l'adresse du restaurant correspond à la recherche à partir des mots clés
   * @param une chaîne de caractères correspondant aux mots clés à rechercher qui 
   * est de la forme "mot1+mot2+mot3"
   * @return une chaîne de caractères correspondant à la fin du lien qui mene vers la page du restaurant
   * ex : "/restaurant/restaurant/9072/la-tomate.shtml"
   */
  def findRestaurant(keyWords: String): String = {
    if (!keyWords.equals("")) {
      val r = Jsoup.connect("https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=" + keyWords).get().select("h2.bu_restaurant_title_2 > a").first()
      if (r != null)
        r.attr("href")
      else
        ""
    } else {
      ""
    }
  }

  /**
   * Se rend à l'adresse de la page du restaurant recherché et trouve l'adresse de celui-ci
   * @param une chaîne de caractères correspondant à la fin de l'adresse internet du restaurant
   * ex : "/restaurant/restaurant/9072/la-tomate.shtml"
   * @return une chaîne de caractères correspondant à l'adresse physique du restaurant
   */
  def lookForAdress(restaurant: String): String = {
    if (restaurant.equals(""))
      return ""
    else {
      val r = Jsoup.connect("https://www.linternaute.com/" + restaurant).get().select("li.icomoon-location > span").first()
      if (r != null)
        r.html()
      else
        ""
    }
  }
}
