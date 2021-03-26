/* Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
SPDX-License-Identifier: Apache-2.0
ABOUT THIS NODE.JS EXAMPLE: This example works with AWS SDK for JavaScript version 3 (v3),
which is pending release.  The preview version of the SDK is available
at https://github.com/aws/aws-sdk-js-v3. This example is in the 'AWS SDK for JavaScript v3 Developer Guide' at
https://docs.aws.amazon.com/sdk-for-javascript/v3/developer-guide/s3-example-creating-buckets.html.
Purpose:
s3_listbuckets.ts demonstrates how to list all the buckets in an AWS account.
Inputs (replace in code):
- REGION
- BUCKET_NAME
Running the code:
s3_getbucketwebsite s3_listobjects.ts
*/
// snippet-start:[s3.JavaScript.buckets.listBucketsV3]
// Import required AWS SDK clients and commands for Node.js
const { S3Client, ListBucketsCommand } = require("@aws-sdk/client-s3");

// Set the AWS region
const REGION = "REGION"; //e.g. "us-east-1"

// Create S3 service object
const s3 = new S3Client({ region: REGION });

const run = async () => {
  try {
    const data = await s3.send(new ListBucketsCommand({}));
    console.log("Success", data.Buckets);
  } catch (err) {
    console.log("Error", err);
  }
};
run();
// snippet-end:[s3.JavaScript.buckets.listBucketsV3]

