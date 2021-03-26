//snippet-sourcedescription:[DescribeCampaign.java demonstrates how to describe an Amazon Personalize campaign.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon Personalize]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/05/2020]
//snippet-sourceauthor:[scmacdon - AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.personalize;

//snippet-start:[personalize.java2.describe_campaign.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.personalize.PersonalizeClient;
import software.amazon.awssdk.services.personalize.model.DescribeCampaignRequest;
import software.amazon.awssdk.services.personalize.model.DescribeCampaignResponse;
import software.amazon.awssdk.services.personalize.model.Campaign;
import software.amazon.awssdk.services.personalize.model.PersonalizeException;
//snippet-end:[personalize.java2.describe_campaign.import]

public class DescribeCampaign {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    DescribeCampaign <campaignArn>\n\n" +
                "Where:\n" +
                "    campaignArn - The ARN of the campaign.\n\n" ;

        if (args.length < 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        /* Read the name from command args */
        String campaignArn = args[0];

        Region region = Region.US_EAST_1;
        PersonalizeClient personalizeClient = PersonalizeClient.builder()
                .region(region)
                .build();

        describeSpecificCampaign(personalizeClient, campaignArn);
        personalizeClient.close();
    }

    //snippet-start:[personalize.java2.describe_campaign.main]
    public static void describeSpecificCampaign(PersonalizeClient personalizeClient, String campaignArn) {

      try {
        DescribeCampaignRequest campaignRequest = DescribeCampaignRequest.builder()
                .campaignArn(campaignArn)
                .build();

        DescribeCampaignResponse campaignResponse = personalizeClient.describeCampaign(campaignRequest);
        Campaign myCampaign = campaignResponse.campaign();
        System.out.println("The Campaign name is "+myCampaign.name());
        System.out.println("The Campaign status is "+myCampaign.status());

    } catch (PersonalizeException e) {
        System.err.println(e.awsErrorDetails().errorMessage());
        System.exit(1);
    }
  }
    //snippet-end:[personalize.java2.describe_campaign.main]
}
