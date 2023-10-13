package com.clover.log.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clover.log.api.impl.GenericApiDelegateImpl;

import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-12T20:15:44.473510300-07:00[America/Los_Angeles]")
@Controller
@RequestMapping("${openapi.cloverTakeHomeAssignment.base-path:}")
public class GenericApiController implements GenericApi {

    private final GenericApiDelegate delegate;

    public GenericApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) GenericApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new GenericApiDelegateImpl() {});
    }

    @Override
    public GenericApiDelegate getDelegate() {
        return delegate;
    }

}
