package com.clover.log.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-05T13:37:41.275571200-07:00[America/Los_Angeles]")
@Controller
@RequestMapping("${openapi.cloverTakeHomeAssignment.base-path:}")
public class GenericApiController implements GenericApi {

    private final GenericApiDelegate delegate;

    public GenericApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) GenericApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new GenericApiDelegate() {});
    }

    @Override
    public GenericApiDelegate getDelegate() {
        return delegate;
    }

}
