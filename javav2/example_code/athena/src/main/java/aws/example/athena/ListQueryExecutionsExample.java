//snippet-sourcedescription:[ListQueryExecutionsExample.java demonstrates how to obtain a list of query execution Id values.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-keyword:[Amazon Athena]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/02/2020]
//snippet-sourceauthor:[scmacdon - aws]
/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

//snippet-start:[athena.java2.ListNamedQueryExecutionsExample.complete]
//snippet-start:[athena.java.ListNamedQueryExecutionsExample.complete]
package aws.example.athena;

//snippet-start:[athena.java2.ListNamedQueryExecutionsExample.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.athena.AthenaClient;
import software.amazon.awssdk.services.athena.model.AthenaException;
import software.amazon.awssdk.services.athena.model.ListQueryExecutionsRequest;
import software.amazon.awssdk.services.athena.model.ListQueryExecutionsResponse;
import software.amazon.awssdk.services.athena.paginators.ListQueryExecutionsIterable;
import java.util.List;
//snippet-end:[athena.java2.ListNamedQueryExecutionsExample.import]

public class ListQueryExecutionsExample {

    public static void main(String[] args) throws Exception {

       AthenaClient athenaClient = AthenaClient.builder()
                .region(Region.US_WEST_2)
                .build();

        listQueryIds(athenaClient);
        athenaClient.close();
    }

    //snippet-start:[athena.java2.ListNamedQueryExecutionsExample.main]
    public static void listQueryIds(AthenaClient athenaClient) {

       try {
             ListQueryExecutionsRequest listQueryExecutionsRequest = ListQueryExecutionsRequest.builder().build();
             ListQueryExecutionsIterable listQueryExecutionResponses = athenaClient.listQueryExecutionsPaginator(listQueryExecutionsRequest);

            for (ListQueryExecutionsResponse listQueryExecutionResponse : listQueryExecutionResponses) {
                List<String> queryExecutionIds = listQueryExecutionResponse.queryExecutionIds();
                System.out.println("\n" +queryExecutionIds);
          }
    } catch (AthenaException e) {
        e.printStackTrace();
        System.exit(1);
    }
    //snippet-end:[athena.java2.ListNamedQueryExecutionsExample.main]
  }
}
//snippet-end:[athena.java.ListNamedQueryExecutionsExample.complete]
//snippet-end:[athena.java2.ListNamedQueryExecutionsExample.complete]