// snippet-sourcedescription:[DescribeVoicesSample Produces a list of all voices available for use when requesting speech synthesis with Amazon Polly..]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-service:[Amazon Polly]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[11/05/2020]
// snippet-sourceauthor:[scmacdon AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.polly;

// snippet-start:[polly.java2.describe_voice.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.polly.PollyClient;
import software.amazon.awssdk.services.polly.model.DescribeVoicesRequest;
import software.amazon.awssdk.services.polly.model.DescribeVoicesResponse;
import software.amazon.awssdk.services.polly.model.PollyException;
import software.amazon.awssdk.services.polly.model.Voice;
import java.util.Collection;
import java.util.Iterator;
// snippet-end:[polly.java2.describe_voice.import]


public class DescribeVoicesSample {

    public static void main(String args[]) {

        PollyClient polly = PollyClient.builder()
                .region(Region.US_WEST_2)
                .build();

        describeVoice(polly) ;
        polly.close();
    }

    // snippet-start:[polly.java2.describe_voice.main]
    public static void describeVoice(PollyClient polly) {

       try {
        DescribeVoicesRequest voicesRequest = DescribeVoicesRequest.builder()
                .languageCode("en-US")
                .build();

         DescribeVoicesResponse enUsVoicesResult = polly.describeVoices(voicesRequest);
         Collection<Voice> voices = enUsVoicesResult.voices();
         Iterator<Voice> iterator = voices.iterator();

           // Get each voice
           while (iterator.hasNext()) {
               Voice myVoice = iterator.next();
               System.out.println("The ID of the voice is " +myVoice.id());
               System.out.println("The gender of the voice is " + myVoice.gender());
           }

        } catch (PollyException e) {
            System.err.println("Exception caught: " + e);
           System.exit(1);
        }
        // snippet-end:[polly.java2.describe_voice.main]
    }
}
