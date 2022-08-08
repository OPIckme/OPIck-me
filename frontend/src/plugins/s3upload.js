import AWS from 'aws-sdk';


// Enter copied or downloaded access ID and secret key here
const ID = 'AKIAXBWZWGCWM4GCD5XC';
const SECRET = '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';
// The name of the bucket that you have created
const BUCKET_NAME = 'jaeyeong-s3';

const s3 = new AWS.S3({
    accessKeyId: ID,
    secretAccessKey: SECRET
});

export const uploadFile = (fileName) => {
    // Read content from the file
    const fileContent = fileName;
        // Setting up S3 upload parameters
    const params = {
        Bucket: BUCKET_NAME,
        Key: 'testAudio.mp3', // File name you want to save as in S3            
        Body: fileContent,
        ContentType: 'audio/mp3'
    };
        // Uploading files to the bucket
    s3.upload(params, function(err, data) {
        if (err) {
            console.log(err)
        }
        console.log(`File uploaded successfully. ${data.Location}`);
    });
};

