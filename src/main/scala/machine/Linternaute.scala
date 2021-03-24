package machine

import org.jsoup.Jsoup

object Linternaute {

  def searchingWords(words: List[String]): List[String] = {
    var searchingWords: List[String] = List()
    var found = false
    for (politeWord: String <- words) {
      if ((StateManager.currentLanguage.linternauteTrigger).contains(politeWord.toLowerCase()))
        found = true
      if (found)
        searchingWords = searchingWords :+ politeWord
    }
    searchingWords
  }

  def keyWords(searchingWords: List[String]): String = {
    searchingWords match {
      case Nil    => ""
      case e :: r => e + "+" + keyWords(r)
    }
  }

  def findRestaurant(keyWords: String): String = {
    Jsoup.connect("https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=" + keyWords).get().select("h2.bu_restaurant_title_2 > a").first().attr("href")
  }

  def lookForAdress(restaurant: String): String = {
    Jsoup.connect("https://www.linternaute.com/" + restaurant).get().select("li.icomoon-location > span").first().html()
  }

}

object TestLinternaute extends App {

  println(Linternaute.lookForAdress(Linternaute.findRestaurant("la+tomate")))

}
