//snippet-sourcedescription:[StartExecution.java demonstrates how to start a state machine execution for AWS Step Functions.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[AWS Step Functions]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[01/28/2021]
//snippet-sourceauthor:[scmacdon-AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.stepfunctions;

// snippet-start:[stepfunctions.java2.start_execute.import]
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sfn.SfnClient;
import software.amazon.awssdk.services.sfn.model.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;
// snippet-end:[stepfunctions.java2.start_execute.import]

public class StartExecution {

       public static void main(String[] args) {
            final String USAGE = "\n" +
                    "Usage:\n" +
                    "    StartExecution <stateMachineArn> <jsonFile>\n\n" +
                    "Where:\n" +
                    "    stateMachineArn - the ARN of the state machine.\n\n" +
                    "    jsonFile - A JSON file that contains the values to pass to the worflow.\n" ;

            if (args.length != 2) {
                System.out.println(USAGE);
                System.exit(1);
            }

            String stateMachineArn = args[0];
            String jsonFile = args[1];
            Region region = Region.US_EAST_1;
            SfnClient sfnClient = SfnClient.builder()
                    .region(region)
                    .build();

           String exeArn = startWorkflow(sfnClient,stateMachineArn, jsonFile);
           System.out.println("The execution ARN is" +exeArn);
           sfnClient.close();
        }

        // snippet-start:[stepfunctions.java2.start_execute.main]
        public static String startWorkflow(SfnClient sfnClient, String stateMachineArn, String jsonFile) {

            String json = getJSONString(jsonFile);

            // Specify the name of the execution by using a GUID value.
            UUID uuid = UUID.randomUUID();
            String uuidValue = uuid.toString();
            try {

                StartExecutionRequest executionRequest = StartExecutionRequest.builder()
                        .input(json)
                        .stateMachineArn(stateMachineArn)
                        .name(uuidValue)
                        .build();

                StartExecutionResponse response = sfnClient.startExecution(executionRequest);
                return response.executionArn();


            } catch (SfnException e) {
                System.err.println(e.awsErrorDetails().errorMessage());
                System.exit(1);
            }
            return "";
        }

    private static String getJSONString(String path) {

        try {
            JSONParser parser = new JSONParser();
            JSONObject data = (JSONObject) parser.parse(new FileReader(path));//path to the JSON file.
            String json = data.toJSONString();
            return json;
        } catch (IOException |  org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
        return "";
   }
    // snippet-end:[stepfunctions.java2.start_execute.main]
 }

