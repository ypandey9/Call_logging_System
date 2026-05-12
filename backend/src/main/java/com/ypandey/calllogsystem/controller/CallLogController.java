package com.ypandey.calllogsystem.controller;

import com.ypandey.calllogsystem.dto.CallLogRequest;
import com.ypandey.calllogsystem.model.CallLog;
import com.ypandey.calllogsystem.service.CallLogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;

@RestController
@RequestMapping("/api/calls")
public class CallLogController {

    private final CallLogService service;

    public CallLogController(CallLogService service) {
        this.service = service;
    }

    // @GetMapping
    // public List<CallLog> getAllCalls() {
    //     return service.getAll();
    // }

    @GetMapping
    public List<CallLog> getMyCalls(Authentication auth) {
        return service.getCallsForUser(auth);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CallLog createCall(@Valid @RequestBody CallLogRequest request, Authentication auth) {
        return service.create(request, auth);
    }

    @DeleteMapping("/{id}")
public ResponseEntity<String> deleteCall(@PathVariable Long id, Authentication auth) {
    boolean deleted = service.delete(id, auth);

    if (deleted) {
        return ResponseEntity.ok("Call deleted successfully");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Call not found");
    }
}

}
