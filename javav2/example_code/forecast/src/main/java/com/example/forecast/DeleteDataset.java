//snippet-sourcedescription:[DeleteDataset.java demonstrates how to delete a data set that belongs to the Amazon Forecast service.]
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

// snippet-start:[forecast.java2.delete_forecast_dataset.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.forecast.ForecastClient;
import software.amazon.awssdk.services.forecast.model.DeleteDatasetRequest;
import software.amazon.awssdk.services.forecast.model.ForecastException;
// snippet-end:[forecast.java2.delete_forecast_dataset.import]

public class DeleteDataset {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    DeleteDataset <datasetARN> \n\n" +
                "Where:\n" +
                "    name - the ARN of the data set to delete. \n\n" ;

         if (args.length != 1) {
              System.out.println(USAGE);
              System.exit(1);
           }

        String myDataSetARN = args[0];
        Region region = Region.US_WEST_2;
        ForecastClient forecast = ForecastClient.builder()
                .region(region)
                .build();

        deleteForecastDataSet(forecast, myDataSetARN);
        forecast.close();
    }

    // snippet-start:[forecast.java2.delete_forecast_dataset.main]
    public static void deleteForecastDataSet(ForecastClient forecast, String myDataSetARN) {

        try {
            DeleteDatasetRequest deleteRequest = DeleteDatasetRequest.builder()
                    .datasetArn(myDataSetARN)
                    .build();

            forecast.deleteDataset(deleteRequest);
            System.out.println("The Data Set was deleted") ;

        } catch (ForecastException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
        // snippet-end:[forecast.java2.delete_forecast_dataset.main]
    }
}
