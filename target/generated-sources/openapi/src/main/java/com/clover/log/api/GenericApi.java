/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.clover.log.api;

import java.util.List;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-05T13:37:41.275571200-07:00[America/Los_Angeles]")
@Validated
@Api(value = "generic", description = "the generic API")
public interface GenericApi {

    default GenericApiDelegate getDelegate() {
        return new GenericApiDelegate() {};
    }

    /**
     * POST /generic : write logs sent by the client
     *
     * @param requestBody TODO (required)
     * @return logged received and parsed successfully (status code 200)
     */
    @ApiOperation(value = "write logs sent by the client", nickname = "generic", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "logged received and parsed successfully") })
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/generic",
        consumes = { "application/json" }
    )
    default ResponseEntity<Void> generic(@ApiParam(value = "TODO", required = true) @Valid @RequestBody List<Object> requestBody) {
        return getDelegate().generic(requestBody);
    }

}
