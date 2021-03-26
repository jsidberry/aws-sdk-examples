//snippet-sourcedescription:[ListOptOut.java demonstrates how to list the phone numbers for which the users have selected to no longer receive future text messages.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon Simple Notification Service (Amazon SNS)]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/06/2020]
//snippet-sourceauthor:[scmacdon- AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.sns;

//snippet-start:[sns.java2.ListOptOut.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.ListPhoneNumbersOptedOutRequest;
import software.amazon.awssdk.services.sns.model.ListPhoneNumbersOptedOutResponse;
import software.amazon.awssdk.services.sns.model.SnsException;
//snippet-end:[sns.java2.ListOptOut.import]

public class ListOptOut {
    public static void main(String[] args) {

        SnsClient snsClient = SnsClient.builder()
                .region(Region.US_EAST_1)
                .build();

        listOpts(snsClient);
        snsClient.close();
    }

    //snippet-start:[sns.java2.ListOptOut.main]
    public static void listOpts( SnsClient snsClient) {

        try {

            ListPhoneNumbersOptedOutRequest request = ListPhoneNumbersOptedOutRequest.builder().build();
            ListPhoneNumbersOptedOutResponse result = snsClient.listPhoneNumbersOptedOut(request);
            System.out.println("Status was " + result.sdkHttpResponse().statusCode() + "\n\nPhone Numbers: \n\n" + result.phoneNumbers());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
       //snippet-end:[sns.java2.ListOptOut.main]
    }
}
