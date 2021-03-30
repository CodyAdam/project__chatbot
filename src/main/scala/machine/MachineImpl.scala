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
        val langSentence: Language = AnalyseSentence.getMajorLanguage(words);

        if (langSentence != null && langSentence != StateManager.currentLanguage) {
          StateManager.changeLanguage(langSentence)
          StateManager.userState = ChangingLanguage;
          return List(StateManager.currentLanguage.expression.askLanguage);
        }

        if (AnalyseSentence.isLinternauteQuery(words)) {
          val adress = Linternaute.messageAdress(words)
          if (!adress.equals("")) {
            return adress
          }
        }

        if (AnalyseSentence.isBlagueQuery(words)) {
          var joke = DataBase.getJoke();
          if (joke != null) {
            return List(joke.text + " <br/> <br/> <br/> <i>" + joke.answer + "</i>");
          }
        }
        
        if (AnalyseSentence.isDefinitionQuery(words)) {
          
        }

        var politePrefix: List[String] = List[String]()
        if (AnalyseSentence.hasPoliteWord(words)) {
          if (words.length == 1) return List(StateManager.currentLanguage.politesse(0))
          politePrefix = List(StateManager.currentLanguage.politesse(0))
        }

        val seachKeywords: Set[String] = AnalyseSentence.findKeysFromWords(words);
        val placesFound: List[Place] = DataBase.findByKeywords(seachKeywords);

        if (placesFound.length == 0)
          return politePrefix ++ List(StateManager.currentLanguage.expression.dontUnderstand)
        else if (placesFound.length == 1)
          return politePrefix ++ MultiRequetes.getAddress(placesFound(0))
        else {
          StateManager.userState = IsChosing
          return politePrefix ++ MultiRequetes.addLineBreaks(MultiRequetes.formatMultiResults(placesFound))
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
            return ask(s)
          }
        }
      }

      case ChangingLanguage => {
        val words: List[String] = AnalyseSentence.getWords(s.toLowerCase())
        if (AnalyseSentence.containsWithTypingError(words, StateManager.currentLanguage.expression.agree)) {
          StateManager.userState = IsAsking
          return List(StateManager.currentLanguage.expression.whatQuery)
        } else {
          StateManager.changeLanguage(StateManager.getNextLanguage());
          return List(StateManager.currentLanguage.expression.askLanguage)
        }
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
}