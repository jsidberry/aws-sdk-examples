// snippet-comment:[These are tags for the AWS doc team's sample catalog. Do not remove.]
// snippet-sourcedescription:[GetEventSelectors.java demonstrates how to get event selectors for a given trail.]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-service:[AWS CloudTrail]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[11/03/2020]
// snippet-sourceauthor:[AWS - scmacdon]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.cloudtrail;

//snippet-start:[cloudtrail.java2.get_event_selectors.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudtrail.CloudTrailClient;
import software.amazon.awssdk.services.cloudtrail.model.CloudTrailException;
import software.amazon.awssdk.services.cloudtrail.model.EventSelector;
import software.amazon.awssdk.services.cloudtrail.model.GetEventSelectorsRequest;
import software.amazon.awssdk.services.cloudtrail.model.GetEventSelectorsResponse;
import java.util.List;
//snippet-end:[cloudtrail.java2.get_event_selectors.import]

public class GetEventSelectors {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    GetEventSelectors <trailName>  \n\n" +
                "Where:\n" +
                "    trailName - the name of the trail. \n" ;

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String trailName = args[0];
        Region region = Region.US_EAST_1;
        CloudTrailClient cloudTrailClient = CloudTrailClient.builder()
                .region(region)
                .build();

        getSelectors(cloudTrailClient, trailName);
        cloudTrailClient.close();
    }

    //snippet-start:[cloudtrail.java2.get_event_selectors.main]
    public static void getSelectors(CloudTrailClient cloudTrailClientClient, String trailName) {

       try {
            GetEventSelectorsRequest selectorsRequest = GetEventSelectorsRequest.builder()
                .trailName(trailName)
                .build();

            GetEventSelectorsResponse selectorsResponse = cloudTrailClientClient. getEventSelectors(selectorsRequest);
            List<EventSelector> selectors = selectorsResponse.eventSelectors();

            for (EventSelector selector: selectors) {
                System.out.println("The type is  "+selector.readWriteTypeAsString());
            }
       } catch (CloudTrailException e) {
           System.err.println(e.getMessage());
           System.exit(1);
       }
    }
    //snippet-end:[cloudtrail.java2.get_event_selectors.main]
 }
