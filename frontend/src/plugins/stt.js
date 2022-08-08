
export function main(blob) {
  // [START speech_quickstart]
  // Imports the Google Cloud client library
  const speech = require('@google-cloud/speech');

  // Creates a client
  const client = new speech.SpeechClient();

  async function quickstart() {
    // The path to the remote LINEAR16 file
    // const gcsUri = 'gs://cloud-samples-data/speech/brooklyn_bridge.raw';
    // const filename = 'C:/Users/multicampus/Desktop/al2.wav';
    // The audio file's encoding, sample rate in hertz, and BCP-47 language code
    const audio = {
      // uri: gcsUri,
      // content: fs.readFileSync(filename).toString('base64'),
      content: blob.toString('base64'),
    };
    const config = {
      encoding: 'LINEAR16',
      sampleRateHertz: 44100,
      languageCode: 'en-US',
      audioChannelCount: 2,
    };
    const request = {
      audio: audio,
      config: config,
    };

    // Detects speech in the audio file
    const [response] = await client.recognize(request);
    const transcription = response.results
      .map(result => result.alternatives[0].transcript)
      .join('\n');
    console.log(`Transcription: ${transcription}`);
  }
  quickstart();
  // [END speech_quickstart]
}

// process.on('unhandledRejection', err => {
//   console.error(err.message);
//   process.exitCode = 1;
// });

// main(...process.argv.slice(2));
