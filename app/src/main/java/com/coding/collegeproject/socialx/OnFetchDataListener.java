package com.coding.collegeproject.socialx;

import com.coding.collegeproject.socialx.models.healine;

import java.util.List;

public interface OnFetchDataListener {
    void onFetchData(List<healine> list, String message);

    void onError(String message) ;
}
