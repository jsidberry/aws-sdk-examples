// snippet-sourcedescription:[DeleteProgressStream.java demonstrates how to delete a progress stream.]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-service:[AWS Migration Hub]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[11-05-2020]
// snippet-sourceauthor:[scmacdon - AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.migrationhub;

// snippet-start:[migration.java2.delete_progress_stream.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.migrationhub.MigrationHubClient;
import software.amazon.awssdk.services.migrationhub.model.DeleteProgressUpdateStreamRequest;
import software.amazon.awssdk.services.migrationhub.model.MigrationHubException;
// snippet-end:[migration.java2.delete_progress_stream.import]

public class DeleteProgressStream {

    public static void main(String[] args) {

      final String USAGE = "\n" +
                "Usage:\n" +
                "    DeleteProgressStream <progressStream> \n\n" +
                "Where:\n" +
                "    progressStream - the name of a progress stream to delete. \n";

        if (args.length != 1) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String progressStream = args[0];
        Region region = Region.US_WEST_2;
        MigrationHubClient migrationClient = MigrationHubClient.builder()
                .region(region)
                .build();

       deleteStream(migrationClient,progressStream ) ;
       migrationClient.close();
    }

    // snippet-start:[migration.java2.delete_progress_stream.main]
    public static void deleteStream(MigrationHubClient migrationClient,String streamName ) {

        try {

        DeleteProgressUpdateStreamRequest deleteProgressUpdateStreamRequest = DeleteProgressUpdateStreamRequest.builder()
                .progressUpdateStreamName(streamName)
                .build();

        migrationClient.deleteProgressUpdateStream(deleteProgressUpdateStreamRequest);

        // Display the results
        System.out.println(streamName + " is deleted" );

    } catch(MigrationHubException e) {
        System.out.println(e.getMessage());
        System.exit(1);
    }
        // snippet-end:[migration.java2.delete_progress_stream.main]
  }
 }
