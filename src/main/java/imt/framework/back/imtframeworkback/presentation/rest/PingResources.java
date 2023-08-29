package imt.framework.back.imtframeworkback.presentation.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/ping")
public interface PingResources {
    @GetMapping
    public ResponseEntity<String> ping();
}
