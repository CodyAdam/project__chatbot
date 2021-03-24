package machine

import org.jsoup.Jsoup

object Linternaute {

  /**
   * Crée une liste de mots utilisés pour la recherche
   * @param la liste de mots rentrés par l'utilisateur
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
   * @param une liste de chaîne de caractères à recherche
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
   * @param une chaîne de caractères correspondant aux mots clés à rechercher
   * @return une chaîne de caractères correspondant à l'adresse de la page du restaurant
   */
  def findRestaurant(keyWords: String): String = {
    val r = Jsoup.connect("https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=" + keyWords).get().select("h2.bu_restaurant_title_2 > a").first()
    if (r != null)
      r.attr("href")
    else
      ""
  }

  /**
   * Se rend à l'adresse de la page du restaurant recherché et trouve l'adresse de celui-ci
   * @param une chaîne de caractères correspondant à l'adresse internet du restaurant
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

object TestLinternaute extends App {
  println(Linternaute.lookForAdress(Linternaute.findRestaurant("la+tomate+moulue")))
}
