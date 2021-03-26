# Amazon Kinesis Data Streams and Data Analytics examples

## Purpose

Shows how to use the AWS SDK for Python (Boto3) with Amazon Kinesis and version 2 of
the Amazon Kinesis Data Analytics API to create an application that reads data from
an input stream, uses SQL code to transform the data, and writes it to an output
stream.

* Create and manage Kinesis streams.
* Create and manage Kinesis Data Analytics applications.
* Create an AWS Identity and Access Management (IAM) role and policy that lets 
an application read from an input stream and write to an output stream.
* Add input and output streams to an application.
* Upload SQL code that runs in an application and transforms data from an input
stream to data in an output stream.
* Run a data generator that puts records into an input stream.
* Read transformed records from an output stream.

## Prerequisites

- You must have an AWS account, and have your default credentials and AWS Region
  configured as described in the [AWS Tools and SDKs Shared Configuration and
  Credentials Reference Guide](https://docs.aws.amazon.com/credref/latest/refdocs/creds-config-files.html).
- Python 3.8.5 or later
- Boto3 1.15.4 or later
- PyTest 5.3.5 or later (to run unit tests)

## Cautions

- As an AWS best practice, grant this code least privilege, or only the 
  permissions required to perform a task. For more information, see 
  [Grant Least Privilege](https://docs.aws.amazon.com/IAM/latest/UserGuide/best-practices.html#grant-least-privilege) 
  in the *AWS Identity and Access Management 
  User Guide*.
- This code has not been tested in all AWS Regions. Some AWS services are 
  available only in specific Regions. For more information, see the 
  [AWS Region Table](https://aws.amazon.com/about-aws/global-infrastructure/regional-product-services/)
  on the AWS website.
- Running this code might result in charges to your AWS account.


## Running the code

**Data Analytics usage**

Run the Kinesis Data Analytics usage demonstration at a command prompt with 
the following command:

```
python kinesisanalyticsv2_demo.py
``` 

**Data generators**

Run a data generator to put data into a Kinesis stream by running it at a 
command prompt, similar to the following:

```
python dg_anomaly.py
```

### Example structure

The examples are divided into the following files:

**kinesisanalyticsv2_demo.py**

Shows how to use Kinesis and version 2 of the Kinesis Data Analytics API to create 
an application that reads from an input stream, runs SQL code to transform the data,
and writes to an output stream.  

* Creates input and output streams.
* Creates a role and policy that lets Kinesis Data Analytics read from the input
stream and write to the output stream.
* Runs a data generator to put heart rate data into the input stream.
* Uses Kinesis Data Analytics to discover an input mapping schema based on the data
in the stream.
* Creates a Kinesis Data Analytics application.
* Adds input and output streams to the application.
* Uploads SQL code to the application.
* Runs the application and reads data from the output stream.
* Cleans up all resources created during the demo. 

**analyticsv2/analytics_application.py**

A class that encapsulates Kinesis Data Analytics API v2 application functions. 

**analyticsv2/example.sql**

SQL code that is uploaded to the demo application. This code assigns
anomaly scores to the heart rate data in the input stream and writes the results
to the output stream.

**streams/dg_\*.py**

Data generators that can be run at a command prompt to put data into a stream. These
generators are used by example applications in the [Amazon Kinesis Data Analytics 
for SQL Applications Developer Guide](https://docs.aws.amazon.com/kinesisanalytics/latest/dev/what-is.html).

**streams/kinesis_stream.py**

A class that encapsulates Kinesis stream functions.

## Running the tests

The unit tests in this module use the botocore Stubber. This captures requests before 
they are sent to AWS, and returns a mocked response. To run all of the tests, 
run the following in your [GitHub root]/python/example_code/kinesis folder.

```    
python -m pytest
```

## Additional information

- [Boto3 Amazon Kinesis service reference](https://boto3.amazonaws.com/v1/documentation/api/latest/reference/services/kinesis.html)
- [Boto3 Amazon Kinesis Data Analytics v2 service reference](https://boto3.amazonaws.com/v1/documentation/api/latest/reference/services/kinesisanalyticsv2.html)
- [Amazon Kinesis Data Streams Developer Guide](https://docs.aws.amazon.com/streams/latest/dev/introduction.html)
- [Amazon Kinesis Data Analytics for SQL Applications Developer Guide](https://docs.aws.amazon.com/kinesisanalytics/latest/dev/what-is.html)
---
Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.

SPDX-License-Identifier: Apache-2.0
