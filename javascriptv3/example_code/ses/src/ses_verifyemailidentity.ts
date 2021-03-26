/* Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
SPDX-License-Identifier: Apache-2.0

ABOUT THIS NODE.JS EXAMPLE: This example works with AWS SDK for JavaScript version 3 (v3),
which is available at https://github.com/aws/aws-sdk-js-v3. This example is in the 'AWS SDK for JavaScript v3 Developer Guide' at
https://docs.aws.amazon.com/sdk-for-javascript/v3/developer-guide/ses-examples-managing-identities.html.

Purpose:
ses_verifyemailidentity.ts demonstrates how to send an Amazon SES verification email.

Inputs (replace in code):
- REGION
- ADDRESS@DOMAIN.EXT

Running the code:
ts-node ses_verifyemailidentity.ts

 */
// snippet-start:[ses.JavaScript.identities.verifyEmailIdentityV3]
// Import required AWS SDK clients and commands for Node.js
const {
  SESClient,
  VerifyEmailIdentityCommand
} = require("@aws-sdk/client-ses");

// Set the AWS Region
const REGION = "REGION"; //e.g. "us-east-1"

// Set the parameters
const params = { EmailAddress: "ADDRESS@DOMAIN.EXT" }; //ADDRESS@DOMAIN.EXT; e.g., name@example.com

// Create SES service object
const ses = new SESClient({ region: REGION });

const run = async () => {
  try {
    const data = await ses.send(new VerifyEmailIdentityCommand(params));
    console.log("Email verification initiated");
  } catch (err) {
    console.error(err, err.stack);
  }
};
run();
// snippet-end:[ses.JavaScript.identities.verifyEmailIdentityV3]

