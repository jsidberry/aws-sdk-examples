/* Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
SPDX-License-Identifier: Apache-2.0

ABOUT THIS NODE.JS EXAMPLE: This example works with AWS SDK for JavaScript version 3 (v3),
which is available at https://github.com/aws/aws-sdk-js-v3. This example is in the 'AWS SDK for JavaScript v3 Developer Guide' at
https://docs.aws.amazon.com/sdk-for-javascript/v3/developer-guide/iam-examples-account-aliases.html.

Purpose:
iam_listaccountaliases.ts demonstrates how to retrieve information about the aliases for an AWS account.

Inputs :
- REGION

Running the code:
ts-node iam_listaccountaliases.ts
 */

// snippet-start:[iam.JavaScript.alias.listAccountAliasesV3]
// Import required AWS SDK clients and commands for Node.js
const { IAMClient, ListAccountAliasesCommand } = require("@aws-sdk/client-iam");

// Set the AWS Region
const REGION = "REGION"; //e.g. "us-east-1"

// Set the parameters
const params = { MaxItems: 5 };

// Create IAM service object
const iam = new IAMClient({ region: REGION });

const run = async () => {
  try {
    const data = await iam.send(new ListAccountAliasesCommand(params));
    console.log("Success", data);
  } catch (err) {
    console.log("Error", err);
  }
};
run();
// snippet-end:[iam.JavaScript.alias.listAccountAliasesV3]

