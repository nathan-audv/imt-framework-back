package imt.framework.back.imtframeworkback.presentation.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController implements PingResources{
    @Override
    public ResponseEntity<String> ping() {
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body("pong");
    }
}
