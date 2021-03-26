# TypeScript environment for Amazon Pinpoint examples
This is a workspace where you can find working AWS SDK for JavaScript version 3 (v3) Amazon Pinpoint examples. 

The [AWS SDK for JavaScript v3](https://github.com/aws/aws-sdk-js-v3) is available. 

The [AWS SDK for JavaScript v3 API Reference Guide](https://docs.aws.amazon.com/AWSJavaScriptSDK/v3/latest/clients/client-pinpoint/index.html) contains the API operations for the AWS SDK for JavaScript v3 Amazon Pinpoint client module.

Amazon Pinpoint is a flexible and scalable outbound and inbound marketing communications service. You can connect with customers over channels like email, SMS, push, or voice.

**NOTE:** The AWS SDK for JavaScript v3 is written in TypeScript so, for consistency, these examples are also in TypeScript. TypeScript extends of JavaScript so these examples can also be run as JavaScript. For more information, see [TypeScript homepage](https://www.typescriptlang.org/).

# Getting started

1. Clone the [AWS SDK Code Samples repo](https://github.com/awsdocs/aws-doc-sdk-examples) to your local environment. See [the Github documentation](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository) for instructions.

2. Install the dependencies listed in the package.json.

**Note**: These include the client module for the AWS services required in these example, 
which is *@aws-sdk/client-pinpoint*.

```
npm install ts-node -g # If using JavaScript, enter 'npm install node -g' instead
cd javascriptv3/example_code/pinpoint
npm install
```
3. If you're using JavaScript, change the sample file extension from ```.ts``` to ```.js```.

4. In your text editor, update user variables specified in the ```Inputs``` section of the sample file.

5. Run sample code:
```
cd src
ts-node [example name].ts // e.g., ts-node pinpoint-create-cluster.ts
```
