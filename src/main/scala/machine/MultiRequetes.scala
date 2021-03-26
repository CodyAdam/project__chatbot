package machine

object MultiRequetes {

  var stagedResults: Array[Place] = Array[Place]()

    /**
   * @param placesFound une liste de Place non vide
   * @return une liste de phrases qui va etre envoyer dans le chat qui 
   * correspond à une phrase introductrice suivant la langue suivis d'une liste de nom d'endroit trouvé
   * example : 
   * "J'ai 2 réponses possibles :"
   * "1) Piscine Bréquigny"
   * "2) Piscine Bibouboubou"
   */
  def formatMultiResults(placesFound: List[Place]): List[String] = {
    var result: List[String] = List[String](
      s"${StateManager.currentLanguage.expression.foundPossibles.replace("XXX", placesFound.length.toString)} :")
    stagedResults = placesFound.toArray
    for (i: Int <- 0 to placesFound.length - 1) {
      // 
      result ++= List(s"${i + 1}) ${placesFound(i).name}")
    }
    return result
  }

  /**
   * @param place une Place
   * @return une liste de phrases qui va etre envoyer dans le chat qui correspond 
   * à l'addresse de l'endroit introduit par une phrase suivant la langue
   */
  def getAddress(place: Place): List[String] = {
    List(s"${StateManager.currentLanguage.expression.address.replace("XXX", place.name)} : ${place.address}")
  }
}