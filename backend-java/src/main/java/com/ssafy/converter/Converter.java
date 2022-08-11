package com.ssafy.converter;

import org.springframework.stereotype.Component;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.File;
@Component
public class Converter {
    public void webm2mp3(String sourcePath,String targetPath) {
        try {
            File source = new File(sourcePath);
            File target = new File(targetPath);

            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("mp3");
            audio.setBitRate(128000);
            audio.setChannels(2);
            audio.setSamplingRate(44100);

            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setInputFormat("webm");
            attrs.setAudioAttributes(audio);

            //Encode
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs);

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

}
