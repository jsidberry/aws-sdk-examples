/* Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
SPDX-License-Identifier: Apache-2.0

ABOUT THIS NODE.JS EXAMPLE: This example works with AWS SDK for JavaScript version 3 (v3),
which is available at https://github.com/aws/aws-sdk-js-v3.

Purpose:
transcribe_delete_job.ts demonstrates how to delete an Amazon Transcribe transcription job.

Inputs (replace in code):
- REGION
- JOB_NAME

Running the code:
ts-node transcribe_create_job.ts
 */
// snippet-start:[transcribe.JavaScript.jobs.deleteJobV3]
// Import the required AWS SDK clients and commands for Node.js
const {
  TranscribeClient,
  DeleteTranscriptionJobCommand
} = require("@aws-sdk/client-transcribe");

// Set the AWS Region
const REGION = "REGION"; // For example, "us-east-1"

// Set the parameters
const params = {
  TranscriptionJobName: "JOB_NAME", // Required. For example, 'transciption_demo'
};

// Create an Amazon Transcribe service client object
const client = new TranscribeClient({ region: REGION });

const run = async () => {
  try {
    const data = await client.send(new DeleteTranscriptionJobCommand(params));
    console.log("Success - deleted");
  } catch (err) {
    console.log("Error", err);
  }
};
run();

// snippet-end:[transcribe.JavaScript.jobs.deleteJobV3]
