//snippet-sourcedescription:[StartCrawler.java demonstrates how to start an AWS Glue crawler.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-keyword:[AWS Glue]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/04/2020]
//snippet-sourceauthor:[scmacdon AWS]
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.glue;

//snippet-start:[glue.java2.start_crawler.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.glue.GlueClient;
import software.amazon.awssdk.services.glue.model.GlueException;
import software.amazon.awssdk.services.glue.model.StartCrawlerRequest ;
//snippet-end:[glue.java2.start_crawler.import]

public class StartCrawler {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    StartCrawler <crawlerName>\n\n" +
                "Where:\n" +
                "    crawlerName - the name of the crawler. \n";

        if (args.length != 1) {
             System.out.println(USAGE);
              System.exit(1);
         }

        String crawlerName = args[0];
        Region region = Region.US_EAST_1;
        GlueClient glueClient = GlueClient.builder()
                .region(region)
                .build();

        startSpecificCrawler(glueClient, crawlerName);
        glueClient.close();
    }

    //snippet-start:[glue.java2.start_crawler.main]
    public static void startSpecificCrawler(GlueClient glueClient, String crawlerName) {

        try {
            StartCrawlerRequest crawlerRequest = StartCrawlerRequest.builder()
                .name(crawlerName)
                .build();

            glueClient.startCrawler(crawlerRequest);

        } catch (GlueException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    //snippet-end:[glue.java2.start_crawler.main]
 }
