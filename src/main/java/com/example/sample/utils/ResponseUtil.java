package com.example.sample.utils;

import com.example.sample.dto.PaginationResult;
import com.example.sample.dto.ResultPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * This class represents a Utility class for creating http response
 */
public class ResponseUtil {
    /***
     * This method creates a PaginationResult response using the parameters provided
     *
     * @param data generic list of objects
     * @param responseKey string representing the key in response to be sent to client
     * @param totalElements number of total elements
     * @param totalPages number of total pages
     * @param currentPage current page number
     * @param <T> generic object
     *
     * @return
     */
    public static <T> PaginationResult createPaginationResult(List<T> data, String responseKey, long totalElements, long totalPages, long currentPage) {
        //Creating instance of page object which will be sent as part of response
        ResultPage resultPage = new ResultPage(totalElements, totalPages, currentPage);
        PaginationResult result = new PaginationResult();
        result.setPage(resultPage);

        //Setting the actual data in map to be sent as part of response
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put(responseKey, data);
        result.setData(dataMap);

        return result;
    }
}
