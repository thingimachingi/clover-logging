package com.clover.log.api;

import com.clover.log.api.impl.SpecificApiDelegateImpl;
import com.clover.log.model.LogRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-14T17:58:35.089-07:00[America/Los_Angeles]")
@Controller
@RequestMapping("${openapi.cloverTakeHomeAssignment.base-path:}")
public class SpecificApiController implements SpecificApi {

    private final SpecificApiDelegate delegate;

    public SpecificApiController(@Autowired(required = false) SpecificApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new SpecificApiDelegateImpl() {});
    }

    @Override
    public SpecificApiDelegate getDelegate() {
        return delegate;
    }

}
