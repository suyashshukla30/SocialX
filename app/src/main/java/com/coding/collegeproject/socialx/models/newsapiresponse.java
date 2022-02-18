package com.coding.collegeproject.socialx.models;

import java.util.List;

public class newsapiresponse {
    String status;
    int totalResults;
    List<healine> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<healine> getArticles() {
        return articles;
    }

    public void setArticles(List<healine> articles) {
        this.articles = articles;
    }
}
