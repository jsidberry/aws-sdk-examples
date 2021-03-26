//snippet-sourcedescription:[ListDataSets.java demonstrates how to list Amazon Forecast data sets.]
//snippet-keyword:[AWS SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon Forecast]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[11/04/2020]
//snippet-sourceauthor:[scmacdon-aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.forecast;
// snippet-start:[forecast.java2.list_datasets.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.forecast.ForecastClient;
import software.amazon.awssdk.services.forecast.model.DatasetSummary;
import software.amazon.awssdk.services.forecast.model.ListDatasetsRequest;
import software.amazon.awssdk.services.forecast.model.ListDatasetsResponse;
import software.amazon.awssdk.services.forecast.model.ForecastException;
import java.util.Iterator;
import java.util.List;
// snippet-end:[forecast.java2.list_datasets.import]

public class ListDataSets {

    public static void main(String[] args) {

        Region region = Region.US_WEST_2;
        ForecastClient forecast = ForecastClient.builder()
                .region(region)
                .build();

        listForecastDataSets(forecast);
        forecast.close();
    }

    // snippet-start:[forecast.java2.list_datasets.main]
    public static void listForecastDataSets(ForecastClient forecast) {

       try {
           ListDatasetsRequest group = ListDatasetsRequest.builder()
                .maxResults(10)
                .build();

           ListDatasetsResponse response = forecast.listDatasets(group);
           List<DatasetSummary> groups = response.datasets();
           Iterator<DatasetSummary> groupsIterator = groups.iterator();

           while(groupsIterator.hasNext()) {

            DatasetSummary myGroup = groupsIterator.next();
            System.out.println("The Data Set name is " +myGroup.datasetName()) ;
        }
       } catch (ForecastException e) {
           System.err.println(e.awsErrorDetails().errorMessage());
           System.exit(1);
       }
      // snippet-end:[forecast.java2.list_datasets.main]
    }
}
