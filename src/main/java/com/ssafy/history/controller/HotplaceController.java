package com.ssafy.history.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.history.dto.HotplaceDto;
import com.ssafy.history.dto.HotplaceImageDto;
import com.ssafy.history.service.HotplaceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Hotplaces", description = "사용자 등록 역사 명소 조회")
@RestController
@RequestMapping("/api/hotplaces")
public class HotplaceController {
    private final HotplaceService hotplaceService;

    public HotplaceController(HotplaceService hotplaceService) {
        this.hotplaceService = hotplaceService;
    }

    @Operation(summary = "Hotplace 목록 조회")
    @GetMapping
    public List<HotplaceDto> findHotplaces(
            @Parameter(description = "제목/내용/주소 검색어") @RequestParam(required = false) String keyword,
            @Parameter(description = "PUBLIC, PRIVATE, PENDING, BLOCKED") @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "20") Integer limit,
            @RequestParam(defaultValue = "0") Integer offset) {
        return hotplaceService.findHotplaces(keyword, status, limit, offset);
    }

    @Operation(summary = "Hotplace 단건 조회")
    @GetMapping("/{hotplaceId}")
    public ResponseEntity<HotplaceDto> findHotplaceById(@PathVariable long hotplaceId) {
        return ResponseEntity.of(Optional.ofNullable(hotplaceService.findHotplaceById(hotplaceId)));
    }

    @Operation(summary = "Hotplace 이미지 조회")
    @GetMapping("/{hotplaceId}/images")
    public List<HotplaceImageDto> findImagesByHotplaceId(@PathVariable long hotplaceId) {
        return hotplaceService.findImagesByHotplaceId(hotplaceId);
    }
}
