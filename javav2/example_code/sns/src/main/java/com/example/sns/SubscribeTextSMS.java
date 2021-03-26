//snippet-sourcedescription:[SubscribeTextSMS.java demonstrates how to subscribe to an Amazon Simple Notification Service (Amazon SNS) text endpoint.]
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

//snippet-start:[sns.java2.SubscribeTextSMS.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.SnsException;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;
//snippet-end:[sns.java2.SubscribeTextSMS.import]

public class SubscribeTextSMS {

    public static void main(String[] args) {
        final String USAGE = "\n" +
                "Usage: " +
                "SubscribeTextSMS <topicArn> <phoneNumber>\n\n" +
                "Where:\n" +
                "  topicArn - the ARN of the topic to subscribe.\n\n" +
                "  phoneNumber - a mobile phone number that receives notifications (for example, +1XXX5550100).\n\n";

        if (args.length < 2) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String topicArn = args[0];
        String phoneNumber = args[1];

        SnsClient snsClient = SnsClient.builder()
                .region(Region.US_WEST_2)
                .build();

        subTextSNS(snsClient, topicArn, phoneNumber);
        snsClient.close();
    }

    //snippet-start:[sns.java2.SubscribeTextSMS.main]
    public static void subTextSNS( SnsClient snsClient, String topicArn, String phoneNumber) {

        try {

            SubscribeRequest request = SubscribeRequest.builder()
                .protocol("sms")
                .endpoint(phoneNumber)
                .returnSubscriptionArn(true)
                .topicArn(topicArn)
                .build();

            SubscribeResponse result = snsClient.subscribe(request);
            System.out.println("Subscription ARN: " + result.subscriptionArn() + "\n\n Status was " + result.sdkHttpResponse().statusCode());

    } catch (SnsException e) {
        System.err.println(e.awsErrorDetails().errorMessage());
        System.exit(1);
    }
        //snippet-end:[sns.java2.SubscribeTextSMS.main]
    }
}
