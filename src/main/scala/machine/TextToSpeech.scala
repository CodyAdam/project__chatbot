package machine

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io._;
import java.net.URL;
import javax.sound.sampled._;

object TextToSpeech {
  
  private var isSpeaking : Boolean = false;
  private var queue : List[SpeakRequest] = List();
  
  def speak(text: String, lang : Language) : Unit = {
    println("ask Speak")
    queue = queue ++ List(new SpeakRequest(text, lang));
    if(isSpeaking) return;
    isSpeaking = true;
    writeFile();
  }
  
  private def writeFile(){
    if(queue.size==0) return;
    println("Starting initializing")
    var speak : SpeakRequest = queue(0);
    queue = queue.drop(1);
    
    // Example from Google Guide of Text To Speech
    try {
      // Set the text input to be synthesized
    var textToSpeechClient : TextToSpeechClient = TextToSpeechClient.create()
    var input : SynthesisInput = SynthesisInput.newBuilder().setText(speak.text).build();

      // Build the voice request, select the language code ("en-US") and the ssml voice gender
      // ("neutral")
      var voice : VoiceSelectionParams =
          VoiceSelectionParams.newBuilder()
              .setLanguageCode(speak.lang.formatxx_XX)
              .setSsmlGender(SsmlVoiceGender.NEUTRAL)
              .build();

      // Select the type of audio file you want returned
      var audioConfig : AudioConfig =
          AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.LINEAR16).build();

      println("ask response")
      
      // Perform the text-to-speech request on the text input with the selected voice parameters and
      // audio file type
      var response : SynthesizeSpeechResponse  =
          textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

      println("end configuration")
      
      // Get the audio contents from the response
      var audioContents: ByteString  = response.getAudioContent();

      println("getting audio")
      
      // Write the response to the output file.
      try  {
        println("writing audio")
        var out : OutputStream  = new FileOutputStream("output.wav")
        out.write(audioContents.toByteArray());
        textToSpeechClient.shutdown();
        readFile();
      } catch {
        case _ : Throwable => {
          println("error TTS");
          nextSpeak();
        }
      }
    } catch {
      case _ : Throwable => {
        println("error TTS");
        nextSpeak();
      }
    }
  }
  
  private def nextSpeak(){
    if(queue.size==0){
      isSpeaking = false;
      return;
    }
    writeFile();
  }
  
  private def readFile(){
    try {
      println("reading audio")
         // Open an audio input stream.
      var file : File  = new File("output.wav");
      
         var url : URL = file.toURI().toURL();
         var audioIn : AudioInputStream  = AudioSystem.getAudioInputStream(url);
         // Get a sound clip resource.
         var clip : Clip = AudioSystem.getClip();
         // Open audio clip and load samples from the audio input stream.
         clip.addLineListener(e => {
            if (e.getType() == LineEvent.Type.STOP) {
              nextSpeak();
            }
          });
         
         clip.open(audioIn);
         clip.start();
         
      } catch {
        case e : Throwable => {
          println(e)
          nextSpeak();
        }
      }
  }
  
}

case class SpeakRequest(text : String, lang: Language);