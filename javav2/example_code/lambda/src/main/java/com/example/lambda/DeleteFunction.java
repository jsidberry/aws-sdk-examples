// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[DeleteFunction.java demonstrates how to delete an AWS Lambda function by using the LambdaClient object]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-keyword:[AWS Lambda]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[05/11/2020]
// snippet-sourceauthor:[AWS-scmacdon]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

// snippet-start:[lambda.java2.DeleteFunction.complete]
package com.example.lambda;

// snippet-start:[lambda.java2.delete.import]
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.model.DeleteFunctionRequest;
import software.amazon.awssdk.services.lambda.model.LambdaException;
// snippet-end:[lambda.java2.delete.import]

public class DeleteFunction {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    DeleteFunction <functionName> \n\n" +
                "Where:\n" +
                "    functionName - the name of the Lambda function \n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String functionName = args[0];
        Region region = Region.US_EAST_1;
        LambdaClient awsLambda = LambdaClient.builder()
                .region(region)
                .build();

        deleteLambdaFunction(awsLambda, functionName);
        awsLambda.close();
    }

    // snippet-start:[lambda.java2.delete.main]
    public static void deleteLambdaFunction(LambdaClient awsLambda, String functionName ) {
        try {
            //Setup an DeleteFunctionRequest
            DeleteFunctionRequest request = DeleteFunctionRequest.builder()
                    .functionName(functionName)
                    .build();

            awsLambda.deleteFunction(request);
            System.out.println("The "+functionName +" function was deleted");

        } catch(LambdaException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        // snippet-end:[lambda.java2.delete.main]
    }
}
// snippet-end:[lambda.java2.DeleteFunction.complete]
