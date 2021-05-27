package com.example.sample.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/***
 * This class is an extension of Result class.
 * In addition to returning the data and status of request execution,
 * It helps in returning the page data in the response for paginated requests.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaginationResult extends Result {
    /***
     * This field represents the page details for the requested page
     */
    ResultPage page;
}
