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

        if (AnalyseSentence.isLinternauteQuery(words)) {
          //TODO Search on Linternaute as :
          //https://wvw.linternaute.com/restaurant/guide/ville-rennes-35000/?name=words(0)+words(1)+words(2)+....+words(length-1)
          //Create a new Scala object for all Linternaute functions
          return ???
        }

        var politePrefix: List[String] = List[String]()
        AnalyseSentence.getLanguageIfPolite(words) match { 
          case Some(lang: Language) => {
            if (lang == StateManager.currentLanguage)
              politePrefix = List(lang.politesse(0)) //TODO ajouter une majuscule au premier mot de politesse avec AnalyseSentence.AddUpperToFirst() function
            else {
              StateManager.changeLanguage(lang)
              StateManager.userState = ChangingLanguage;
              return List(StateManager.currentLanguage.expression.askLanguage);
            }
          }
          case None => Unit
        }

        AnalyseSentence.getLanguageIfSearching(words) match {
          case Some(lang: Language) => {
            if (lang != StateManager.currentLanguage) {
              StateManager.changeLanguage(lang)
              StateManager.userState = ChangingLanguage;
              return List(StateManager.currentLanguage.expression.askLanguage);
            }
          }
          case None => Unit
        }

        //TODO check if 'words' is in trigger words to perform :
        // language change

        val seachKeywords: Set[String] = AnalyseSentence.findKeysFromWords(words);
        val placesFound: List[Place] = DataBase.findByKeywords(seachKeywords);

        if (placesFound.length == 0)
          return politePrefix ++ List(StateManager.currentLanguage.expression.dontUnderstand)
        else if (placesFound.length == 1)
          return politePrefix ++ MultiRequetes.getAddress(placesFound(0))
        else {
          StateManager.userState = IsChosing
          return politePrefix ++ MultiRequetes.formatMultiResults(placesFound)
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
        if(AnalyseSentence.containsWithTypingError(words, StateManager.currentLanguage.expression.agree)){
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

  /**
   * test de l'avatar
   *  @param l une liste de requête
   *  @return la liste des réponses produites par l'avatar
   */
  def test(l: List[String]): List[String] = ???
}