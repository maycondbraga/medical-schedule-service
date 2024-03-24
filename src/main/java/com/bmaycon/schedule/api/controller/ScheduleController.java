package com.bmaycon.schedule.api.controller;

import com.bmaycon.schedule.domain.dto.request.ScheduleRequest;
import com.bmaycon.schedule.domain.dto.response.ScheduleResponse;
import com.bmaycon.schedule.facade.ScheduleFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleFacade scheduleFacade;

    @PostMapping
    public ResponseEntity<ScheduleResponse> save(@Valid @RequestBody ScheduleRequest request) {
        ScheduleResponse response = scheduleFacade.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> findAll() {
        List<ScheduleResponse> responses = scheduleFacade.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponse> findById(@PathVariable Long id) {
        ScheduleResponse response = scheduleFacade.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> update(@PathVariable Long id, @Valid @RequestBody ScheduleRequest request) {
        ScheduleResponse response = scheduleFacade.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        scheduleFacade.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}