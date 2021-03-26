//snippet-sourcedescription:[GetStages.java demonstrates how to get information about stages.]
//snippet-keyword:[SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon API Gateway]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[01/21/2021]
//snippet-sourceauthor:[scmacdon - aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.gateway;

// snippet-start:[apigateway.java2.get_stages.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.apigateway.ApiGatewayClient;
import software.amazon.awssdk.services.apigateway.model.*;
import java.util.List;
// snippet-end:[apigateway.java2.get_stages.import]

/**
 * To run this AWS code example, ensure that you have setup your development environment, including your AWS credentials.
 *
 * For information, see this documentation topic:
 *
 *https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html
 */

public class GetStages {
    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    GetStages <restApiId> \n\n" +
                "Where:\n" +
                "    restApiId - The string identifier of an existing RestApi. (for example, xxxx99ewyg).\n" ;

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String restApiId =  args[0];
        Region region = Region.US_EAST_1;
        ApiGatewayClient apiGateway = ApiGatewayClient.builder()
                .region(region)
                .build();

        getAllStages(apiGateway, restApiId);
        apiGateway.close();
    }

    // snippet-start:[apigateway.java2.get_stages.main]
    public static void getAllStages(ApiGatewayClient apiGateway, String restApiId) {

        try {

            GetStagesRequest stagesRequest = GetStagesRequest.builder()
                    .restApiId(restApiId)
                    .build();

            GetStagesResponse response = apiGateway.getStages(stagesRequest);
            List<Stage> stages = response.item();
            for (Stage stage: stages) {
                System.out.println("Stage name is: "+stage.stageName());
            }

            int yt = 0;
        } catch (ApiGatewayException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }

    }
    // snippet-end:[apigateway.java2.get_stages.main]
}

