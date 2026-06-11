package com.codedgarcia.expenses.manager.dashboard.controller;

import com.codedgarcia.expenses.manager.dashboard.dto.SummaryResponse;
import com.codedgarcia.expenses.manager.dashboard.service.DashboardService;
import com.codedgarcia.expenses.manager.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<SummaryResponse> getSummary(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(dashboardService.getSummary(user.getId()));
    }
}
