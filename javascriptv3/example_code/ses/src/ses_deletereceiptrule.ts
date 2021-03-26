/* Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
SPDX-License-Identifier: Apache-2.0

ABOUT THIS NODE.JS EXAMPLE: This example works with AWS SDK for JavaScript version 3 (v3),
which is available at https://github.com/aws/aws-sdk-js-v3. This example is in the 'AWS SDK for JavaScript v3 Developer Guide' atic
https://docs.aws.amazon.com/sdk-for-javascript/v3/developer-guide/ses-examples-receipt-rules.html.

Purpose:
ses_deletereceiptrule.ts demonstrates how to delete an Amazon SES receipt rule.

Inputs (replace in code):
- REGION
- RULE_NAME
- RULE_SET_NAME

Running the code:
ts-node ses_deletereceiptrule.ts
 */

// snippet-start:[ses.JavaScript.rules.deleteReceiptRuleV3]
// Import required AWS SDK clients and commands for Node.js
const { SESClient, DeleteReceiptRuleCommand } = require("@aws-sdk/client-ses");

// Set the AWS Region
const REGION = "REGION"; //e.g. "us-east-1"

// Set the deleteReceiptRule params
var params = {
  RuleName: "RULE_NAME", // RULE_NAME
  RuleSetName: "RULE_SET_NAME", // RULE_SET_NAME
};

// Create SES service object
const ses = new SESClient({ region: REGION });

const run = async () => {
  try {
    const data = await ses.send(new DeleteReceiptRuleCommand(params));
    console.log("Receipt Rule Deleted");
  } catch (err) {
    console.error(err, err.stack);
  }
};
run();
// snippet-end:[ses.JavaScript.rules.deleteReceiptRuleV3]

