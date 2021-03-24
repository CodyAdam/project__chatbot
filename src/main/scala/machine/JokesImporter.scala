package machine

import scala.io.Source
import scala.collection.mutable.HashMap
import scala.util.Random

object JokesImporter {
  
  def loadJokes(ll : List[Language]) : HashMap[Language, JokeWheel] = {
    var lj : List[Joke] = List();
    var lend : HashMap[Language, JokeWheel] = new HashMap();
    for(lang <- ll){
      lj = List();
      val filename = "blague\\"+lang.formatxx_XX+".txt";
      for (line <- Source.fromFile(filename).getLines) {
          var cut = line.split('$');
          if(cut.length>1) lj = lj ++ List(new Joke(cut(0), cut(2)));
      }
      lj = melange(lj);
      lend.put(lang, new JokeWheel(lj, 0));
    }
    lend;
  }
  
  def melange(l : List[Joke]) : List[Joke] = {
    var l1 = l;
    for(i <- 0 to 5){
      var end : Integer = l.length - 1;
      var i1 : Integer = (new Random().nextFloat * end).toInt;
      l1 = l1.patch(i1, List(), 1).patch(end, List(l1(i1)), 0);
    }
    l1;
  }
  
}

case class Joke(text : String, answer : String);

case class JokeWheel(list : List[Joke], current : Integer);