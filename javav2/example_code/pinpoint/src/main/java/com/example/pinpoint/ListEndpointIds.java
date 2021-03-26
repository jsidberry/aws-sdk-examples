//snippet-sourcedescription:[ListEndpointIds.java demonstrates how to retrieve information about all the endpoints that are associated with a specific user ID.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon Pinpoint]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[12/05/2020]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package com.example.pinpoint;

//snippet-start:[pinpoint.java2.list_endpoints.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.pinpoint.PinpointClient;
import software.amazon.awssdk.services.pinpoint.model.EndpointResponse;
import software.amazon.awssdk.services.pinpoint.model.GetUserEndpointsRequest;
import software.amazon.awssdk.services.pinpoint.model.GetUserEndpointsResponse;
import software.amazon.awssdk.services.pinpoint.model.PinpointException;
import java.util.List;
//snippet-end:[pinpoint.java2.list_endpoints.import]


public class ListEndpointIds {

    public static void main(String[] args) {
        final String USAGE = "\n" +
                "Usage: ListEndpointIds <applicationId> <userId>\n\n" +

                "Where:\n" +
                "  applicationId - The ID of the Amazon Pinpoint application that has the endpoint.\n" +
                "  userId - The user id applicable to the endpoints";

        if (args.length != 2) {
            System.out.println(USAGE);
           System.exit(1);
        }

        String applicationId = args[0];
        String userId = args[1];
        PinpointClient pinpoint = PinpointClient.builder()
                .region(Region.US_EAST_1)
                .build();

        listAllEndpoints(pinpoint, applicationId, userId );
        pinpoint.close();
    }

    //snippet-start:[pinpoint.java2.list_endpoints.main]
    public static void listAllEndpoints(PinpointClient pinpoint,
                                        String applicationId,
                                       String userId ) {

        try {
            GetUserEndpointsRequest endpointsRequest = GetUserEndpointsRequest.builder()
                    .userId(userId)
                    .applicationId(applicationId)
                    .build();

            GetUserEndpointsResponse response = pinpoint.getUserEndpoints(endpointsRequest);
            List<EndpointResponse> endpoints = response.endpointsResponse().item();

            // Display the results
            for (EndpointResponse endpoint: endpoints) {
                System.out.println("The channel type is: "+endpoint.channelType());
                System.out.println("The address is  "+endpoint.address());
            }

        } catch ( PinpointException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    //snippet-end:[pinpoint.java2.list_endpoints.main]
}
