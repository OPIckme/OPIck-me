import AWS from 'aws-sdk';

// Enter copied or downloaded access ID and secret key here
const ID = 'AKIAXBWZWGCWM4GCD5XC';
const SECRET = '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';
// The name of the bucket that you have created
// const BUCKET_NAME = 'jaeyeong-s3';

const s3 = new AWS.S3({
    accessKeyId: ID,
    secretAccessKey: SECRET
});

export const uploadFile = (params,saveScript,uuid) => {
    // Read content from the file
        // Setting up S3 upload parameters  
        // Uploading files to the bucket

    s3.upload(params, function(err, data) {
        if (err) {
            console.log(err)
        }
        saveScript(uuid)
        console.log(`File uploaded successfully. ${data.Location}`);
    });
};

