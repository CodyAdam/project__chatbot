package machine

import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.cloud.speech.v1.LongRunningRecognizeMetadata;
import com.google.cloud.speech.v1.LongRunningRecognizeResponse;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.StreamingRecognitionConfig;
import com.google.cloud.speech.v1.StreamingRecognitionResult;
import com.google.cloud.speech.v1.StreamingRecognizeRequest;
import com.google.cloud.speech.v1.StreamingRecognizeResponse;
import com.google.cloud.speech.v1.WordInfo;
import com.google.common.util.concurrent.SettableFuture;
import com.google.protobuf.ByteString;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.TargetDataLine;

object SpeechToText {

  private var inRecognition: Boolean = false;
  private var cancel: Boolean = false;
  var client : SpeechClient = null;
  var responseObserver: ResponseObserver[StreamingRecognizeResponse] = null;

  def startRecognition(): Unit = {

    if (inRecognition) {
      this.cancelReco();
      return ;
    }

    inRecognition = true;

    try {
      client = SpeechClient.create();

      responseObserver =
        new ResponseObserver[StreamingRecognizeResponse]() {
          var responses: List[StreamingRecognizeResponse] = List();

          def onStart(controller: StreamController): Unit = {}

          def onResponse(response: StreamingRecognizeResponse): Unit = {
            responses = responses ++ List(response);
          }

          def onComplete(): Unit = {
            if (!inRecognition) return ;
            var text: String = "";
            for (response: StreamingRecognizeResponse <- responses) {
              var result: StreamingRecognitionResult = response.getResultsList().get(0);
              var alternative: SpeechRecognitionAlternative = result.getAlternativesList().get(0);
              text += alternative.getTranscript();
            }
            endReco(text);
          }

          def onError(t: Throwable): Unit = {
            closeSpeech();
          }
        };
        
      var clientStream: ClientStream[StreamingRecognizeRequest] =
        client.streamingRecognizeCallable().splitCall(responseObserver);

      var recognitionConfig: RecognitionConfig =
        RecognitionConfig.newBuilder()
          .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
          .setLanguageCode("fr-FR")
          .setSampleRateHertz(16000)
          .build();
      var streamingRecognitionConfig: StreamingRecognitionConfig =
        StreamingRecognitionConfig.newBuilder().setConfig(recognitionConfig).build();

      var request: StreamingRecognizeRequest =
        StreamingRecognizeRequest.newBuilder()
          .setStreamingConfig(streamingRecognitionConfig)
          .build(); // The first request in a streaming call has to be a config

      clientStream.send(request);
      // SampleRate:16000Hz, SampleSizeInBits: 16, Number of channels: 1, Signed: true,
      // bigEndian: false
      var audioFormat: AudioFormat = new AudioFormat(16000, 16, 1, true, false);
      var targetInfo: DataLine.Info = new DataLine.Info(classOf[TargetDataLine], audioFormat); // Set the system information to read from the microphone audio stream

      if (!AudioSystem.isLineSupported(targetInfo)) {
        println("Microphone not supported");
        return ;
      }
      // Target data line captures the audio stream the microphone produces.
      var targetDataLine: TargetDataLine = AudioSystem.getLine(targetInfo).asInstanceOf[TargetDataLine];
      targetDataLine.open(audioFormat);
      targetDataLine.start();
      println("Start speaking");
      var startTime: Long = System.currentTimeMillis();
      // Audio Input Stream
      var audio: AudioInputStream = new AudioInputStream(targetDataLine);
      while (true) {
        var estimatedTime: Long = System.currentTimeMillis() - startTime;
        var data: Array[Byte] = new Array[Byte](6400);
        audio.read(data);
        if (estimatedTime > 5000 || cancel) { // 5 seconds
          println("Stop speaking.");
          targetDataLine.stop();
          targetDataLine.close();
          responseObserver.onComplete();
          closeSpeech();
          
          return ;
        }
        request =
          StreamingRecognizeRequest.newBuilder()
            .setAudioContent(ByteString.copyFrom(data))
            .build();
        clientStream.send(request);
      }
    } catch {
      case e: Throwable => { closeSpeech(); }
    }
    responseObserver.onComplete();
    closeSpeech();
  }

  def endReco(text: String) {
    //inRecognition = false;
    if (text != "") ui.MessageTextField.text = text;
  }
  
  def closeSpeech(){
    responseObserver = null;
    client.shutdown();
    client = null;
    inRecognition = false;
  }
  
  def cancelReco() {
    if (inRecognition) cancel = true;
  }

}