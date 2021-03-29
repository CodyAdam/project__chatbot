package testMachine

import org.junit.Test
import org.junit.Before
import org.junit.Assert._
import machine._

class CaseDefault {

  // initialisation des objets sous test
  DataBase.init()
  @Before
  def resetState = {
    MachineImpl.reinit
  }

  //TODO TESTER LA FONCTIONNALITÉ : F1 du tp avec les phrases de bases avec la fonction MachineImpl.ask()
  
  /**
   * Cas à tester :
   * 
   * - Hotel de ville
   * - Mairie
   * - Mairie de Rennes
   * - TNB
   * - Theatre de Bretagne
   * - Theatre National de Bretagne
   * - Gare
   * - Gare SNCF 
   * - Theatre la Paillette
   * - La Paillette
   */
  
  //Pour la Mairie
  
  //1-Sans aucune faute
  
  @Test
  def ask{
	  assertEquals(
			  MachineImpl.ask("Où se trouve l'Hotel de ville ?"),
			  List("L'adresse de Mairie de Rennes est : Place de la Mairie")  
			  )
  }
  
  @Test
  def ask2{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Mairie ?"),
			  List("L'adresse de Mairie de Rennes est : Place de la Mairie")  
			  )
  }
    
  @Test
  def ask3{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Mairie de Rennes ?"),
			  List("L'adresse de Mairie de Rennes est : Place de la Mairie")  
			  )
  }
  
  //2- Avec une erreur de frappe acceptée
  
  @Test
  def ask4{
	  assertEquals(
			  MachineImpl.ask("Où se trove l'Hotl de vile ?"),
			  List("L'adresse de Mairie de Rennes est : Place de la Mairie")  
			  )
  }
  
  @Test
  def ask5{
	  assertEquals(
			  MachineImpl.ask("Ou se trouve la Mairié ?"),
			  List("L'adresse de Mairie de Rennes est : Place de la Mairie")  
			  )
  }
    
  @Test
  def ask6{
	  assertEquals(
			  MachineImpl.ask("Ou se trouve la Marie de Renens ?"),
			  List("L'adresse de Mairie de Rennes est : Place de la Mairie")  
			  )
  }
  
  //3-Erreurs non tolérées(plus d'une erreur dans un mot clé, plus d'une lettre manquante, inversement de lettres...)
  
  @Test
  def ask7{
	  assertEquals(
			  MachineImpl.ask("Où se trouve l'Hot de vile ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
  @Test
  def ask8{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Maie ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
    
  @Test
  def ask9{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Mair,ie de Rennes ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
    //Pour le TNB
  
  //1-Sans aucune faute
  
  @Test
  def ask10{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le TNB?"),
			  List("L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier")  
			  )
  }
  
  
  @Test
  def ask11{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le Théâtrè National de Brétàgnè ?"),
			  List("L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier")  
			  )
  }
    
  @Test
  def ask12{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le Théâtre de Bretagne ?"),
			  List("L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier")  
			  )
  }
  
  //2- Avec une erreur de frappe acceptée
  
  @Test
  def ask13{
	  assertEquals(
			  MachineImpl.ask("Où se trove le tn ?"),
			  List("L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier")  
			  )
  }
  
  @Test
  def ask14{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le Théâtre National Bretagne?"),
			  List("L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier")  
			  )
  }
    
  @Test
  def ask15{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le Théâte Bretane ?"),
			  List("L'adresse de Théâtre National de Bretagne est : 1, Rue Saint-Hélier")  
			  )
  }
  
  //3-Erreurs non tolérées(plus d'une erreur dans un mot clé, plus d'une lettre manquante, inversement de lettres...)
  
  @Test
  def ask16{
	  assertEquals(
			  MachineImpl.ask("Où se trove le ttnnb ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
  @Test
  def ask17{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le thaetre national de Bretagne ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
    
  @Test
  def ask18{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le Theatre de Bertagne ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
  // Pour la Gare
  
  //1-Sans aucune faute
  
  @Test
  def ask19{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Gare?"),
			  List("L'adresse de Gare SNCF est : 19, Place de la Gare")  
			  )
  }
  
  @Test
  def ask20{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Gare SNCF ?"),
			  List("L'adresse de Gare SNCF est : 19, Place de la Gare")  
			  )
  }
  
  //2- Avec une erreur de frappe acceptée
  
  @Test
  def ask21{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Garre SNCF"),
			  List("L'adresse de Gare SNCF est : 19, Place de la Gare")  
			  )
  }
  
  @Test
  def ask22{
	  assertEquals(
			  MachineImpl.ask("Ou se trouve la Gares ?"),
			  List("L'adresse de Gare SNCF est : 19, Place de la Gare")  
			  )
  }
  
  //3-Erreurs non tolérées(plus d'une erreur dans un mot clé, plus d'une lettre manquante, inversement de lettres...)
  
  @Test
  def ask23{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Ga,re SNFC?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
  @Test
  def ask24{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Gaer ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }

  
    // Pour le Théâtre la Paillette
  
  //1-Sans aucune faute
  
  @Test
  def ask25{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le Théâtre la Paillette ?"),
			  List("L'adresse de Théâtre La Paillette est : 2, Rue du Pré de Bris")  
			  )
  }

  
  //2- Avec une erreur de frappe acceptée
  
  @Test
  def ask26{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le théatre la pailette ?"),
			  List("L'adresse de Théâtre La Paillette est : 2, Rue du Pré de Bris")  
			  )
  }

  
  //3-Erreurs non tolérées(plus d'une erreur dans un mot clé, plus d'une lettre manquante, inversement de lettres...)
  
  @Test
  def ask27{
	  assertEquals(
			  MachineImpl.ask("Où se trouve la Paillette ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
  @Test
  def ask28{
	  assertEquals(
			  MachineImpl.ask("Où se trouve le theatre la Palliette ?"),
			  List("Je ne comprends pas votre demande")  
			  )
  }
  
}