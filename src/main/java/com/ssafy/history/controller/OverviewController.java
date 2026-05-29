package com.ssafy.history.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.dto.TableCountDto;
import com.ssafy.history.service.OverviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Overview", description = "DB 적재 상태 확인")
@RestController
@RequestMapping("/api/overview")
public class OverviewController {
    private final OverviewService overviewService;

    public OverviewController(OverviewService overviewService) {
        this.overviewService = overviewService;
    }

    @Operation(summary = "핵심 테이블 row 수 조회")
    @GetMapping("/counts")
    public List<TableCountDto> countCoreTables() {
        return overviewService.countCoreTables();
    }
}
