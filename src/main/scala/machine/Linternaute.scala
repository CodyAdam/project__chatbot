package machine

object Linternaute {

  def searchingWords(words: List[String]): List[String] = {
    var searchingWords: List[String] = List()
    var found = false
    for(politeWord: String <- words) {
      if((StateManager.currentLanguage.linternauteTrigger).contains(politeWord.toLowerCase()))
      found = true
      if(found)
      searchingWords = searchingWords :+ politeWord
    }
    searchingWords
  }
  
  def keyWords(searchingWords: List[String]):String = {
    searchingWords match {
      case Nil => ""
      case e :: r => e + "+" + keyWords(r)
    }
  }
  
  def request(keyWords: String) {
    Document doc = Jsoup.connect("https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=" + keyWords).get()
  }

}