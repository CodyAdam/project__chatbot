package machine

object MultiRequetes {

  private var stagedResults: Array[Place] = Array[Place]()

  def formatMultiResults(placesFound: List[Place]): List[String] = {
    var result: List[String] = List[String](
      stateManager.currentLanguage.expression.chose.replace("XXX", placesFound.length.toString()))
    stagedResults = placesFound.toArray
    for (i: Int <- 0 to placesFound.length - 1) {
      // example : "1) Piscine Bréquigny"
      result ++= List(s"${i + 1}) ${placesFound(i).name}")
    }
    return result
  }
}