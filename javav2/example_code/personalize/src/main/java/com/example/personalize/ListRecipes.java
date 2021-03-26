//snippet-sourcedescription:[ListRecipes.java demonstrates how to list Amazon Personalize recipes.]
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

//snippet-start:[personalize.java2.list_recipes.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.personalize.PersonalizeClient;
import software.amazon.awssdk.services.personalize.model.ListRecipesRequest;
import software.amazon.awssdk.services.personalize.model.ListRecipesResponse;
import software.amazon.awssdk.services.personalize.model.PersonalizeException;
import software.amazon.awssdk.services.personalize.model.RecipeSummary;
//snippet-end:[personalize.java2.list_recipes.import]

import java.util.List;

public class ListRecipes {

    public static void main(String[] args) {

        Region region = Region.US_EAST_1;
        PersonalizeClient personalizeClient = PersonalizeClient.builder()
                .region(region)
                .build();

        listAllRecipes(personalizeClient);
        personalizeClient.close();
    }

    //snippet-start:[personalize.java2.list_recipes.main]
    public static void listAllRecipes(PersonalizeClient personalizeClient) {

        try {
            ListRecipesRequest recipesRequest = ListRecipesRequest.builder()
                .maxResults(15)
                .build();

            ListRecipesResponse response = personalizeClient.listRecipes(recipesRequest);
            List<RecipeSummary> recipes = response.recipes();

            for (RecipeSummary recipe: recipes) {
                System.out.println("The recipe ARN is: "+recipe.recipeArn());
                System.out.println("The recipe name is: "+recipe.name());
            }
        } catch (PersonalizeException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }
    //snippet-end:[personalize.java2.list_recipes.main]
  }
