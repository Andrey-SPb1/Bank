package com.bank.controller;

import com.bank.profiling.ProfilingAspect;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProfilingAspect profilingAspect;

    @PutMapping("/audit/status/{flag}")
    @ResponseStatus(HttpStatus.OK)
    public void setAuditFlag(@PathVariable Boolean flag) {
        profilingAspect.setFlag(flag);
    }
    @GetMapping("/audit/status")
    public Boolean getAuditFlag() {
        return profilingAspect.isFlag();
    }

}
