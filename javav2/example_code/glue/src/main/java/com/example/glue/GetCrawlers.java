//snippet-sourcedescription:[GetCrawlers.java demonstrates how to get AWS Glue crawlers.]
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

//snippet-start:[glue.java2.get_crawlers.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.glue.GlueClient;
import software.amazon.awssdk.services.glue.model.GetCrawlersRequest;
import software.amazon.awssdk.services.glue.model.GetCrawlersResponse;
import software.amazon.awssdk.services.glue.model.Crawler;
import software.amazon.awssdk.services.glue.model.GlueException;
import java.util.List;
//snippet-end:[glue.java2.get_crawlers.import]

public class GetCrawlers {

    public static void main(String[] args) {

        Region region = Region.US_EAST_1;
        GlueClient glueClient = GlueClient.builder()
                .region(region)
                .build();

        getAllCrawlers(glueClient);
        glueClient.close();
    }

    //snippet-start:[glue.java2.get_crawlers.main]
    public static void getAllCrawlers(GlueClient glueClient){

        try {
            GetCrawlersRequest crawlersRequest = GetCrawlersRequest.builder()
                .maxResults(10)
                .build();

            GetCrawlersResponse response = glueClient.getCrawlers(crawlersRequest);
            List<Crawler> crawlers = response.crawlers();

            for (Crawler crawler: crawlers) {
                System.out.println("The crawler name is : "+crawler.name());
            }

        } catch (GlueException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    //snippet-end:[glue.java2.get_crawlers.main]
}
