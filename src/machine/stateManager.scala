package machine

sealed trait UserState
case object IsAsking extends UserState // Can enter a sentence to analyse
case object IsChosing extends UserState // Can enter a number to chose between multiple requests
case object Waiting extends UserState // Waiting for a answer
case object Blocked extends UserState // Can not interract with the application UI

case class Language(langue: String, politesse: List[String], recherche: List[String], linternauteTrigger: List[String], expression: Expression)
case class Expression(agree: String, disagree: String, address: String, dontUnderstand: String, askLanguage: String, whatQuery: String, chose: String)

object stateManager {
  var userState: UserState = IsAsking
  var currentLanguage: Language = BaseDonnees.getLanguages()(0)
}