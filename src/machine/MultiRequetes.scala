package machine

object MultiRequetes {
  
  private var stagedResults : Array[Place] = Array[Place]()
  
  def formatResults(placesFound : List[Place]) : List[String] = {
    var result : List[String] = List[String](s"J'ai ${placesFound.length} réponses possibles :" ) //TODO ADD languages
    stagedResults = placesFound.toArray
    for(i : Int <- 0 to placesFound.length-1){
      // example : "1) Piscine Bréquigny"
      result ++=  List(s"${i+1}) ${placesFound(i).name}")
    }
    return result
  }
}