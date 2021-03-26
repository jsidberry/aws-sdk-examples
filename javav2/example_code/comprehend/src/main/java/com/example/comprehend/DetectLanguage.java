// snippet-sourcedescription:[DetectLanguage demonstrates how to detect the language of the text.]
//snippet-keyword:[AWS SDK for Java v2]
// snippet-service:[Amazon Comprehend]
// snippet-keyword:[Code Sample]
// snippet-sourcetype:[full-example]
// snippet-sourcedate:[11/04/2020]
// snippet-sourceauthor:[scmacdon - AWS]

/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/

package com.example.comprehend;

//snippet-start:[comprehend.java2.detect_language.import]
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.comprehend.ComprehendClient;
import software.amazon.awssdk.services.comprehend.model.ComprehendException;
import software.amazon.awssdk.services.comprehend.model.DetectDominantLanguageRequest;
import software.amazon.awssdk.services.comprehend.model.DetectDominantLanguageResponse;
import software.amazon.awssdk.services.comprehend.model.DominantLanguage;
import java.util.Iterator;
import java.util.List;
//snippet-end:[comprehend.java2.detect_language.import]


public class DetectLanguage {

    public static void main(String[] args) {

        //Specify French text - "It is raining today in Seattle"
        String text = "Il pleut aujourd'hui à Seattle";
        Region region = Region.US_EAST_1;

        ComprehendClient comClient = ComprehendClient.builder()
                .region(region)
                .build();

        System.out.println("Calling DetectDominantLanguage");
        detectTheDominantLanguage(comClient, text);
        comClient.close();
    }

    //snippet-start:[comprehend.java2.detect_language.main]
    public static void detectTheDominantLanguage(ComprehendClient comClient, String text){

       try {

            DetectDominantLanguageRequest request = DetectDominantLanguageRequest.builder()
                    .text(text)
                    .build();

            DetectDominantLanguageResponse resp = comClient.detectDominantLanguage(request);
            List<DominantLanguage> allLanList = resp.languages();
            Iterator<DominantLanguage> lanIterator = allLanList.iterator();

            while (lanIterator.hasNext()) {
                DominantLanguage lang = lanIterator.next();
                System.out.println("Language is " + lang.languageCode());
            }

           } catch (ComprehendException e) {
               System.err.println(e.awsErrorDetails().errorMessage());
               System.exit(1);
           }
        //snippet-end:[comprehend.java2.detect_language.main]
        }
    }
