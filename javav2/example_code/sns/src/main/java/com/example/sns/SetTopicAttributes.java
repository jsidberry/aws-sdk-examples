//snippet-sourcedescription:[SetTopicAttributes.java demonstrates how to set attributes for an Amazon Simple Notification Service (Amazon SNS) topic.]
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

//snippet-start:[sns.java2.SetTopicAttributes.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.SetTopicAttributesRequest;
import software.amazon.awssdk.services.sns.model.SetTopicAttributesResponse;
import software.amazon.awssdk.services.sns.model.SnsException;
//snippet-end:[sns.java2.SetTopicAttributes.import]

public class SetTopicAttributes {

    public static void main(String[] args) {
        final String USAGE = "\n" +

                "Usage: " +
                "SetTopicAttributes <attribute> <topicArn> <value>\n\n" +
                "Where:\n" +
                "  attribute - the attribute action to use. Valid parameters are: Policy | DisplayName | DeliveryPolicy .\n" +
                "  topicArn - The ARN of the topic. \n" +
                "  value - the value for the attribute.\n\n";

        if (args.length < 3) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String attribute = args[0];
        String topicArn = args[1];
        String value = args[2];

        SnsClient snsClient = SnsClient.builder()
                .region(Region.US_WEST_2)
                .build();

        setTopAttr(snsClient, attribute, topicArn, value);
        snsClient.close();

    }

    //snippet-start:[sns.java2.SetTopicAttributes.main]
    public static void setTopAttr(SnsClient snsClient, String attribute, String topicArn, String value) {

        try {

            SetTopicAttributesRequest request = SetTopicAttributesRequest.builder()
                .attributeName(attribute)
                .attributeValue(value)
                .topicArn(topicArn)
                .build();

            SetTopicAttributesResponse result = snsClient.setTopicAttributes(request);

            System.out.println("\n\nStatus was " + result.sdkHttpResponse().statusCode() + "\n\nTopic " + request.topicArn()
                + " updated " + request.attributeName() + " to " + request.attributeValue());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        //snippet-end:[sns.java2.SetTopicAttributes.main]
    }
}
