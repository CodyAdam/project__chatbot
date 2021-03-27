package machine

trait MachineDialogue {
  /** envoi d'une requête à la machine et réccupération de sa réponse
   *  @param s la requête
   *  @result la liste de réponses
   */
  def ask(s:String):List[String]
  
  // Pour la partie test par le client
  /** réinitialisation de l'avatar */
  def reinit:Unit
  
}