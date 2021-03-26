/* Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
SPDX-License-Identifier: Apache-2.0

ABOUT THIS NODE.JS EXAMPLE: This example works with AWS SDK for JavaScript version 3 (v3),
which is available at https://github.com/aws/aws-sdk-js-v3. This example is in the 'AWS SDK for JavaScript v3 Developer Guide' at
https://docs.aws.amazon.com/sdk-for-javascript/v3/developer-guide/kinesis-examples-capturing-page-scrolling.html.

Purpose:
blog_page.html is part of a tutorial demonstrating how to capture browser user event data and send it to Amazon Kenesis.
To run the full tutorial, see
https://docs.aws.amazon.com/sdk-for-javascript/v3/developer-guide/kinesis-examples-capturing-page-scrolling.html.

Inputs:
- REGION
- IDENTITY_POOL_ID
- PARTITION_KEY
- STREAM_NAME
 */

// snippet-start:[kinesis.JavaScript.kinesis-example_v3.complete]
// snippet-start:[kinesis.JavaScript.kinesis-example_v3.config]
// Configure Credentials to use Cognito
const { CognitoIdentityClient } = require("@aws-sdk/client-cognito-identity");
const {
  fromCognitoIdentityPool,
} = require("@aws-sdk/credential-provider-cognito-identity");
const { Kinesis, PutRecordsCommand } = require("@aws-sdk/client-kinesis");

const REGION = "REGION";
const client = new Kinesis({
  region: REGION,
  credentials: fromCognitoIdentityPool({
    client: new CognitoIdentityClient({region: REGION}),
    identityPoolId: "IDENTITY_POOL_ID" // IDENTITY_POOL_ID
  })
});

// snippet-end:[kinesis.JavaScript.kinesis-example_v3.config]
// snippet-start:[kinesis.JavaScript.kinesis-example_v3.addEventListener]
// Get the ID of the web page element.
var blogContent = document.getElementById('BlogContent');

// Get scrollable height.
var scrollableHeight = blogContent.clientHeight;

var recordData = [];
var TID = null;
blogContent.addEventListener('scroll', function(event) {
  console.log('scrolled');
  clearTimeout(TID);
  // Prevent creating a record while a user is actively scrolling.
  TID = setTimeout(function() {
    // Calculate the percentage.
    var scrollableElement = event.target;
    var scrollHeight = scrollableElement.scrollHeight;
    var scrollTop = scrollableElement.scrollTop;

    var scrollTopPercentage = Math.round((scrollTop / scrollHeight) * 100);
    var scrollBottomPercentage = Math.round(((scrollTop + scrollableHeight) / scrollHeight) * 100);

    // Create the Amazon Kinesis record.
    var record = {
      Data: JSON.stringify({
        blog: window.location.href,
        scrollTopPercentage: scrollTopPercentage,
        scrollBottomPercentage: scrollBottomPercentage,
        time: new Date()
      }),
      PartitionKey: 'PARTITION_KEY' // Must be a string.
    };
    recordData.push(record);
  }, 100);
});

// snippet-end:[kinesis.JavaScript.kinesis-example_v3.addEventListener]
// snippet-start:[kinesis.JavaScript.kinesis-example_v3.putRecords]

// Helper function to upload data to Amazon Kinesis.
const uploadData = async () => {
  try {
    const data = await client.send(new PutRecordsCommand({
      Records: recordData,
      StreamName: 'STREAM_NAME'
    }));
    console.log('data', data);
    console.log("Kinesis updated", data);
  } catch (err) {
    console.log("Error", err);
  }
};
// Run uploadData every second if data exists.
setInterval(function() {
  if (!recordData.length) {
    return;
  }
  uploadData();
  // clear record data
  recordData = [];
}, 1000);
// snippet-end:[kinesis.JavaScript.kinesis-example_v3.putRecords]
// snippet-end:[kinesis.JavaScript.kinesis-example_v3.complete]
