package com.ssafy.stt;

import com.microsoft.cognitiveservices.speech.*;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

@Component
public class STT2{
    private static String YourSubscriptionKey = "72fefc3811c74709a167d59486643f85";
    private static String YourServiceRegion = "koreacentral";


    private static Semaphore stopTranslationWithFileSemaphore;
    public static String recognizeFromMicrophone(String fileName) throws InterruptedException, ExecutionException {
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(YourSubscriptionKey, YourServiceRegion);
        speechConfig.setSpeechRecognitionLanguage("en-US");



        AudioConfig audioConfig = AudioConfig.fromWavFileInput(fileName);
        SpeechRecognizer recognizer = new SpeechRecognizer(speechConfig, audioConfig);

        // First initialize the semaphore.
        stopTranslationWithFileSemaphore = new Semaphore(0);

        StringBuilder sb = new StringBuilder();

        recognizer.recognizing.addEventListener((s, e) -> {
            System.out.println("RECOGNIZING: Text=" + e.getResult().getText());

        });

        recognizer.recognized.addEventListener((s, e) -> {
            if (e.getResult().getReason() == ResultReason.RecognizedSpeech) {
//                System.out.println("RECOGNIZED: Text=" + e.getResult().getText());
                sb.append(e.getResult().getText()+" ");
            }
            else if (e.getResult().getReason() == ResultReason.NoMatch) {
                System.out.println("NOMATCH: Speech could not be recognized.");
            }
        });

        recognizer.canceled.addEventListener((s, e) -> {
            System.out.println("CANCELED: Reason=" + e.getReason());

            if (e.getReason() == CancellationReason.Error) {
                System.out.println("CANCELED: ErrorCode=" + e.getErrorCode());
                System.out.println("CANCELED: ErrorDetails=" + e.getErrorDetails());
                System.out.println("CANCELED: Did you set the speech resource key and region values?");
            }

            stopTranslationWithFileSemaphore.release();
        });

        recognizer.sessionStopped.addEventListener((s, e) -> {
            System.out.println("\n    Session stopped event.");
            stopTranslationWithFileSemaphore.release();
        });

        // Starts continuous recognition. Uses StopContinuousRecognitionAsync() to stop recognition.
        recognizer.startContinuousRecognitionAsync().get();

// Waits for completion.
        stopTranslationWithFileSemaphore.acquire();

// Stops recognition.
        recognizer.stopContinuousRecognitionAsync().get();

        System.out.println(sb);

        return sb.toString();

    }
}