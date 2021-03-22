package machine

sealed trait UserState
case object IsAsking extends UserState // Can enter a sentence to analyse
case object IsChosing extends UserState // Can enter a number to chose between multiple requests
case object ChangingLanguage extends UserState // Waiting for a answer
case object Waiting extends UserState // Waiting for a answer
case object Blocked extends UserState // Can not interract with the application UI

case class Language(
  langue:             String,
  formatxx_XX:        String,
  politesse:          List[String],
  recherche:          List[String],
  linternauteTrigger: List[String],
  expression:         Expression)
case class Expression(
  agree:          String,
  disagree:       String,
  address:        String,
  dontUnderstand: String,
  askLanguage:    String,
  whatQuery:      String,
  foundPossibles: String,
  chose:          String)

object StateManager {
  var userState: UserState = IsAsking
  var currentLanguage: Language = DataBase.getLanguages()(0)

  /**
   * replace the current language by a new one
   * @param newLanguage the language that will replace the current one
   */
  def changeLanguage(newLanguage: Language): Unit = {
    currentLanguage = newLanguage;
  }

  /**
   * @return the next language in the order : français, anglais, espagnol, allemand, italien, puis de nouveau français, anglais,
   * etc.
   */
  def getNextLanguage(): Language = {
    val currentIndex = DataBase.getLanguages().indexOf(currentLanguage);
    DataBase.getLanguages()((currentIndex + 1) % DataBase.getLanguages().length);
  }

}