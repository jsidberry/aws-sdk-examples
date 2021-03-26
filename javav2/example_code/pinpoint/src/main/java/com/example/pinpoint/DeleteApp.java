//snippet-sourcedescription:[DeleteApp.java demonstrates how to delete an application in the Amazon Pinpoint dashboard.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon Pinpoint]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/05/2020]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.pinpoint;

//snippet-start:[pinpoint.java2.deleteapp.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.pinpoint.PinpointClient;
import software.amazon.awssdk.services.pinpoint.model.DeleteAppRequest;
import software.amazon.awssdk.services.pinpoint.model.DeleteAppResponse;
import software.amazon.awssdk.services.pinpoint.model.PinpointException;
//snippet-end:[pinpoint.java2.deleteapp.import]

public class DeleteApp {
    public static void main(String[] args) {
        final String USAGE = "\n" +
                "Usage: " +
                "DeleteApp <appId>\n\n" +
                "Where:\n" +
                "  appId - the ID of the application to delete.\n\n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String appId = args[0];
        System.out.println("Deleting an application with ID: " + appId);

        PinpointClient pinpoint = PinpointClient.builder()
                .region(Region.US_EAST_1)
                .build();

        deletePinApp(pinpoint, appId) ;
        System.out.println("Done");
        pinpoint.close();
    }

    //snippet-start:[pinpoint.java2.deleteapp.main]
    public static void deletePinApp(PinpointClient pinpoint, String appId ) {

        try {

            DeleteAppRequest appRequest = DeleteAppRequest.builder()
                    .applicationId(appId)
                    .build();

            DeleteAppResponse result = pinpoint.deleteApp(appRequest);
            String appName = result.applicationResponse().name();
            System.out.println("Application " + appName + " has been deleted.");

        } catch (PinpointException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }

        //snippet-end:[pinpoint.java2.deleteapp.main]
    }
}

