//snippet-sourcedescription:[UpdateTable.java demonstrates how to update an Amazon DynamoDB table.]
//snippet-keyword:[SDK for Java v2]
//snippet-keyword:[Code Sample]
//snippet-service:[Amazon DynamoDB]
//snippet-sourcetype:[full-example]
//snippet-sourcedate:[10/30/2020]
//snippet-sourceauthor:[scmacdon - aws]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.dynamodb;

// snippet-start:[dynamodb.java2.update_table.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.model.ProvisionedThroughput;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.UpdateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
// snippet-end:[dynamodb.java2.update_table.import]

public class UpdateTable {

    public static void main(String[] args) {
        final String USAGE = "\n" +
                "Usage:\n" +
                "    UpdateTable <tableName> <readCapacity> <writeCapacity>\n\n" +
                "Where:\n" +
                "    tableName - the Amazon DynamoDB table to update (for example, Music3).\n" +
                "    readCapacity  - the new read capacity of the table (for example, 16).\n" +
                "    writeCapacity - the new write capacity of the table (for example, 10).\n\n" +
                "Example:\n" +
                "    UpdateTable Music3 16 10\n";

        if (args.length != 3) {
            System.out.println(USAGE);
            System.exit(1);
        }

        String tableName = args[0];
        Long readCapacity = Long.parseLong(args[1]);
        Long writeCapacity = Long.parseLong(args[2]);

        Region region = Region.US_WEST_2;
        DynamoDbClient ddb = DynamoDbClient.builder().region(region).build();
        updateDynamoDBTable(ddb, tableName, readCapacity, writeCapacity);
        ddb.close();
    }

    // snippet-start:[dynamodb.java2.update_table.main]
    public static void updateDynamoDBTable(DynamoDbClient ddb,
                                           String tableName,
                                           Long readCapacity,
                                           Long writeCapacity) {

        System.out.format(
                "Updating %s with new provisioned throughput values\n",
                tableName);
        System.out.format("Read capacity : %d\n", readCapacity);
        System.out.format("Write capacity : %d\n", writeCapacity);

        ProvisionedThroughput tableThroughput = ProvisionedThroughput.builder()
                .readCapacityUnits(readCapacity)
                .writeCapacityUnits(writeCapacity)
                .build();

        UpdateTableRequest request = UpdateTableRequest.builder()
                .provisionedThroughput(tableThroughput)
                .tableName(tableName)
                .build();

        try {
            ddb.updateTable(request);
        } catch (DynamoDbException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        // snippet-end:[dynamodb.java2.update_table.main]
        System.out.println("Done!");
    }
}
