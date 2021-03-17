package machine
import scala.xml.XML

object XMLImporter extends App{
	val xml = XML.loadFile("doc/vAr.xml")
  print(xml)
}