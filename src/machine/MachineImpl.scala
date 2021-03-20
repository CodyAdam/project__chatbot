package machine

object MachineImpl extends MachineDialogue {
  /**
   * envoi d'une requête à la machine et réccupération de sa réponse
   *  @param s la requête
   *  @result la liste de réponses
   */
  def ask(s: String): List[String] = {
    val words: List[String] = AnalyseSentence.getWords(s.toLowerCase())
    val seachKeywords : Set[String] = AnalyseSentence.findKeysFromWords(words);
    val placesFound : List[Place] = BaseDonnees.findByKeywords(seachKeywords);
    val response : List[String] = MultiRequetes.formatResults(placesFound)
    return response;
  }

  // Pour la partie test par le client
  /** réinitialisation de l'avatar */
  def reinit = ???
  /**
   * test de l'avatar
   *  @param l une liste de requête
   *  @return la liste des réponses produites par l'avatar
   */
  def test(l: List[String]): List[String] = ???
}