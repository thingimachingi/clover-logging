package com.clover.log.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-05T13:37:41.275571200-07:00[America/Los_Angeles]")
@Controller
@RequestMapping("${openapi.cloverTakeHomeAssignment.base-path:}")
public class SpecificApiController implements SpecificApi {

    private final SpecificApiDelegate delegate;

    public SpecificApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) SpecificApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new SpecificApiDelegate() {});
    }

    @Override
    public SpecificApiDelegate getDelegate() {
        return delegate;
    }

}
