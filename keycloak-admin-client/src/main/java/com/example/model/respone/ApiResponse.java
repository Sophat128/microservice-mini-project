package com.example.model.respone;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)

    String message ;
    @JsonInclude(JsonInclude.Include.NON_NULL)

    String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T payload;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer page;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer size;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer totalElements;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer totalPages;

    public ApiResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }

    public ApiResponse(String message, String status, T payload) {
        this.message = message;
        this.status = status;
        this.payload = payload;
    }
}