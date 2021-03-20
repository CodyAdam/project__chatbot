package machine

sealed trait UserState
case object IsAsking extends UserState // Can enter a sentence to analyse
case object IsChosing extends UserState // Can enter a number to chose between multiple requests
case object Waiting extends UserState // Waiting for a answer
case object Blocked extends UserState // Can not interract with the application UI

sealed trait Language
case object Francais extends Language
case object Espagnol extends Language
case object Allemand extends Language
case object Italien extends Language
case object Autre extends Language

object stateManager {
  var userState : UserState = IsAsking
  var currentLanguage : Language = Francais
}