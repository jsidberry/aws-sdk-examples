/*
   Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
   SPDX-License-Identifier: Apache-2.0
*/
package com.example.entities;

public class WorkItem {

    private String id;
    private String name;
    private String archive;
    private String guide ;
    private String date;
    private String description;
    private String status;

    public void setArc (String id) {
        this.archive = archive;
    }

    public String getArc() {
        return archive;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setStatus (String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setGuide (String guide) {
        this.guide = guide;
    }

    public String getGuide() {
        return this.guide;
    }
}
