package com.example.letstrip.test;

import com.example.letstrip.test.model.TestReq;
import com.example.letstrip.test.model.TestRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public ResponseEntity<?> testApi(@RequestBody TestReq testReq) {
        TestRes testRes = new TestRes(testReq.getTestString());
        return ResponseEntity.ok().body(testRes);
    }
}
