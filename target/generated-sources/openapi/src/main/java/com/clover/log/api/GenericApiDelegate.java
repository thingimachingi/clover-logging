package com.clover.log.api;

import java.util.List;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A delegate to be called by the {@link GenericApiController}}.
 * Implement this interface with a {@link org.springframework.stereotype.Service} annotated class.
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-05T13:37:41.275571200-07:00[America/Los_Angeles]")
public interface GenericApiDelegate {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /generic : write logs sent by the client
     *
     * @param requestBody TODO (required)
     * @return logged received and parsed successfully (status code 200)
     * @see GenericApi#generic
     */
    default ResponseEntity<Void> generic(List<Object> requestBody) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
