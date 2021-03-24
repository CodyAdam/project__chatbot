package machine

object MultiRequetes {

  var stagedResults: Array[Place] = Array[Place]()
  
  def formatMultiResults(placesFound: List[Place]): List[String] = {
    var result: List[String] = List[String](
      s"${StateManager.currentLanguage.expression.foundPossibles.replace("XXX", placesFound.length.toString)} :")
    stagedResults = placesFound.toArray
    for (i: Int <- 0 to placesFound.length - 1) {
      // example : "1) Piscine Bréquigny"
      result ++= List(s"${i + 1}) ${placesFound(i).name}")
    }
    return result
  }
  
  def getAddress(place : Place ) : List[String] = {
    List(s"${StateManager.currentLanguage.expression.address.replace("XXX", place.name)} : ${place.address}")
  }
}