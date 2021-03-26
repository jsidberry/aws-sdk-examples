//snippet-sourcedescription:[DeleteForecast.java demonstrates how to delete a forecast that belongs to the Amazon Forecast service.]
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

// snippet-start:[forecast.java2.delete_forecast.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.forecast.ForecastClient;
import software.amazon.awssdk.services.forecast.model.DeleteForecastRequest;
import software.amazon.awssdk.services.forecast.model.ForecastException;
// snippet-end:[forecast.java2.delete_forecast.import]

public class DeleteForecast {

    public static void main(String[] args) {

        final String USAGE = "\n" +
                "Usage:\n" +
                "    DeleteForecast <forecastArn> \n\n" +
                "Where:\n" +
                "    forecastArn - the ARN that belongs to the forecast to delete. \n\n" +
                "Example:\n" +
                "    DeleteForecast arn:aws:forecast:us-west-2:xxxxxe33:forecast/MyForecast\n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String forecastArn = args[0];
        Region region = Region.US_WEST_2;
        ForecastClient forecast = ForecastClient.builder()
                .region(region)
                .build();

        delForecast(forecast, forecastArn) ;
        forecast.close();
    }

    // snippet-start:[forecast.java2.delete_forecast.main]
    public static void delForecast(ForecastClient forecast, String forecastArn) {

        try {
            DeleteForecastRequest forecastRequest = DeleteForecastRequest.builder()
                    .forecastArn(forecastArn)
                    .build() ;

            forecast.deleteForecast(forecastRequest);
            System.out.println("The forecast was successfully deleted");
      } catch (ForecastException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    // snippet-end:[forecast.java2.delete_forecast.main]
}
