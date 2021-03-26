# AWS Lambda Java code examples

This README discusses how to run and test the Java code examples for AWS Lambda.

## Running the AWS Lambda Java files

**IMPORTANT**

The Java examples perform AWS operations for the account and AWS Region for which you've specified credentials, and you may incur AWS service charges by running them. See the [AWS Pricing page](https://aws.amazon.com/pricing/) for details about the charges you can expect for a given service and operation.

Some of these examples perform *destructive* operations on AWS resources. For example, you can delete a Lambda function. **Be very careful** when running an operation that deletes or modifies AWS resources in your account. It's best to create separate test-only resources when experimenting with these examples.

To run these examples, you can setup your development environment to use Apache Maven or Gradle to configure and build AWS SDK for Java projects. For more information, 
see [Get started with the AWS SDK for Java 2.x](https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html).


 ## Testing the AWS Lambda files

You can test the Java code examples for  AWS Lambda by running a test file named **LambdaTest**. This file uses JUnit 5 to run the JUnit tests and is located in the **src/test/java** folder. For more information, see [https://junit.org/junit5/](https://junit.org/junit5/).

You can run the JUnit tests from a Java IDE, such as IntelliJ, or from the command line by using Maven. As each test is run, you can view messages that inform you if the various tests succeed or fail. For example, the following message informs you that Test 3 passed.

	Test 3 passed

**WARNING**: _Running these JUnit tests manipulates real Amazon resources and may incur charges on your account._

 ### Properties file
Before running the  AWS Lambda JUnit tests, you must define values in the **config.properties** file located in the **resources** folder. This file contains values that are required to run the JUnit tests. For example, you define an function name used for various tests. 
If you do not define all values, the JUnit tests fail.

Define these values to successfully run the JUnit tests:

- **completeFunctionName** - A fully qualified function name (for example, arn:aws:lambda:us-east-1:000008047983:function:myLambda).   
- **functionName** – The name of a new function name (for example, myLambda).
- **filePath** - the path to the ZIP or JAR where the code is located.
- **role** - The role ARN that has Lambda permissions.
- **handler** - The fully qualifed method name (for example, example.Handler::handleRequest).

**Note**: The **CreateFunction** test requires a ZIP or JAR that represents the code of the Lambda function. If you do not have a ZIP or JAR, please refer to the following document:
 
 https://github.com/aws-doc-sdk-examples/tree/master/javav2/usecases/creating_workflows_stepfunctions

### Command line
To run the JUnit tests from the command line, you can use the following command.

		mvn test

You will see output from the JUnit tests, as shown here.

	[INFO] -------------------------------------------------------
	[INFO]  T E S T S
	[INFO] -------------------------------------------------------
	[INFO] Running LambdaTest
	Test 1 passed
	Test 2 passed
	...
	Done!
	[INFO] Results:
	[INFO]
	[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
	[INFO]
	INFO] --------------------------------------------
	[INFO] BUILD SUCCESS
	[INFO]--------------------------------------------
	[INFO] Total time:  12.003 s
	[INFO] Finished at: 2020-02-10T14:25:08-05:00
	[INFO] --------------------------------------------

### Unsuccessful tests

If you do not define the correct values in the properties file, your JUnit tests are not successful. You will see an error message such as the following. You need to double-check the values that you set in the properties file and run the tests again.

	[INFO]
	[INFO] --------------------------------------
	[INFO] BUILD FAILURE
	[INFO] --------------------------------------
	[INFO] Total time:  19.038 s
	[INFO] Finished at: 2020-02-10T14:41:51-05:00
	[INFO] ---------------------------------------
	[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:2.22.1:test (default-test) on project S3J2Project:  There are test failures.
	[ERROR];
