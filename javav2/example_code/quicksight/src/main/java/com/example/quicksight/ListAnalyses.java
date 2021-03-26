//snippet-sourcedescription:[ListAnalyses.java demonstrates how to list Amazon QuickSight analyses.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon QuickSight]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[1/14/2021]
//snippet-sourceauthor:[scmacdon - aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.quicksight;

// snippet-start:[quicksight.java2.list_analyses.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.quicksight.QuickSightClient;
import software.amazon.awssdk.services.quicksight.model.ListAnalysesRequest;
import software.amazon.awssdk.services.quicksight.model.ListAnalysesResponse;
import software.amazon.awssdk.services.quicksight.model.AnalysisSummary;
import software.amazon.awssdk.services.quicksight.model.QuickSightException;
import java.util.List;
// snippet-end:[quicksight.java2.list_analyses.import]

public class ListAnalyses {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage: AddQueueTags <account>\n\n" +
                "Where:\n" +
                "  account - the ID of the AWS account.\n\n";

         if (args.length != 1) {
             System.out.println(USAGE);
             System.exit(1);
         }

        String account = args[0];
        QuickSightClient qsClient = QuickSightClient.builder()
                .region(Region.US_EAST_1)
                .build();

        listAllAnAnalyses(qsClient, account );
        qsClient.close();
    }

    // snippet-start:[quicksight.java2.list_analyses.main]
    public static void listAllAnAnalyses(QuickSightClient qsClient, String account ) {

        try {
            ListAnalysesRequest analysesRequest = ListAnalysesRequest.builder()
                    .awsAccountId(account)
                    .maxResults(20)
                    .build();

            ListAnalysesResponse res  = qsClient.listAnalyses(analysesRequest);
            List<AnalysisSummary> analysisList = res.analysisSummaryList();

            for (AnalysisSummary analysis: analysisList) {
                System.out.println("Analysis name: "+analysis.name());
                System.out.println("Analysis status: "+analysis.status());
                System.out.println("Analysis status: "+analysis.analysisId());
              }

        } catch (QuickSightException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    // snippet-end:[quicksight.java2.list_analyses.main]
}
