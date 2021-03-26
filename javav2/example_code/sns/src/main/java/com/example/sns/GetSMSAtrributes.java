//snippet-sourcedescription:[GetSMSAtrributes.java demonstrates how to retrieve the default SMS type for Amazon Simple Notification Service (Amazon SNS).]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon Simple Notification Service]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/06/2020]
//snippet-sourceauthor:[scmacdon- AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.sns;

//snippet-start:[sns.java2.GetSMSAtrributes.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.GetSubscriptionAttributesRequest;
import software.amazon.awssdk.services.sns.model.GetSubscriptionAttributesResponse;
import software.amazon.awssdk.services.sns.model.SnsException;
import java.util.Iterator;
import java.util.Map;
//snippet-end:[sns.java2.GetSMSAtrributes.import]

public class GetSMSAtrributes {
    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage: " +
                "GetSMSAtrributes <topicArn>\n\n" +
                "Where:\n" +
                "  topicArn - the ARN of the topic from which to retrieve attributes.\n\n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String topicArn = args[0];
        SnsClient snsClient = SnsClient.builder()
                .region(Region.US_WEST_2)
                .build();

        getSNSAttrutes(snsClient, topicArn);
        snsClient.close();
    }

    //snippet-start:[sns.java2.GetSMSAtrributes.main]
    public static void getSNSAttrutes(SnsClient snsClient,String topicArn ) {

        try {
            GetSubscriptionAttributesRequest request = GetSubscriptionAttributesRequest.builder()
                    .subscriptionArn(topicArn)
                    .build();

            // Get the Subscription attributes
            GetSubscriptionAttributesResponse res = snsClient.getSubscriptionAttributes(request);
            Map<String, String> map = res.attributes();

            // Iterate through the map
            Iterator iter = map.entrySet().iterator();

            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                System.out.println("[Key] : " + entry.getKey() + " [Value] : " + entry.getValue());
            }

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }

        System.out.println("\n\nStatus was good");
        //snippet-end:[sns.java2.GetSMSAtrributes.main]
    }
}