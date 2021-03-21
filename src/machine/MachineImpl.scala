package machine

object MachineImpl extends MachineDialogue {
  /**
   * envoi d'une requête à la machine et réccupération de sa réponse
   *  @param s la requête
   *  @result la liste de réponses
   */
  def ask(s: String): List[String] = {
    StateManager.userState match {
      case IsAsking => {
        val words: List[String] = AnalyseSentence.getWords(s.toLowerCase())
        val politePrefix: List[String] = List[String]()
        
        //TODO check if 'words' is in trigger words to perform :
        // language change, polite word, search in other language

        val seachKeywords: Set[String] = AnalyseSentence.findKeysFromWords(words);
        val placesFound: List[Place] = DataBase.findByKeywords(seachKeywords);
        if (placesFound.length == 0)
          return List(StateManager.currentLanguage.expression.dontUnderstand)
        else if (placesFound.length == 1)
          return MultiRequetes.getAddress(placesFound(0))
        else {
          StateManager.userState = IsChosing
          val response: List[String] = MultiRequetes.formatMultiResults(placesFound)
          return response;
        }
      }
      case IsChosing => { 
        "\\d+".r.findFirstIn(s) match { //extract number from input
          case Some(string) => {
            val number: Int = string.toInt - 1
            if (number < MultiRequetes.stagedResults.length) { // check validity
              StateManager.userState = IsAsking
              return MultiRequetes.getAddress(MultiRequetes.stagedResults(number))
            } else {
              StateManager.userState = IsAsking
              return List(StateManager.currentLanguage.expression.dontUnderstand)
            }
          }
          case None => {
            StateManager.userState = IsAsking
            return List(StateManager.currentLanguage.expression.dontUnderstand)
          }
        }
      }
      case ChangingLanguage => {
        ??? //TODO
        /*
         * il demande confirmation de la nouvelle langue à l'utilisateur. Si celui-ci confirme,
         * l'avatar lui demandera de poser sa requête dans cette nouvelle langue.
         * Si l'utilisateur ne confirme pas, l'avatar lui proposera la prochaine
         * langue jusqu'à ce que celui confirme. L'ordre de proposition des langues est :
         * français, anglais, espagnol, allemand, italien, puis de nouveau français, anglais,
         * etc. Pendant la confirmation d'une langue, si l'utilisateur utilise un mot dans
         * une autre langue le changement de langue se poursuit pour cette autre langue.
         */
      }
      case _ => List(StateManager.currentLanguage.expression.dontUnderstand)
    }
  }

  // Pour la partie test par le client
  /** réinitialisation de l'avatar */
  def reinit = {
    StateManager.userState = IsAsking
    StateManager.currentLanguage = DataBase.getLanguages()(0)
  }

  /**
   * test de l'avatar
   *  @param l une liste de requête
   *  @return la liste des réponses produites par l'avatar
   */
  def test(l: List[String]): List[String] = ???
}