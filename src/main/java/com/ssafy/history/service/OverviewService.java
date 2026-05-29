package com.ssafy.history.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.history.dto.TableCountDto;
import com.ssafy.history.mapper.OverviewMapper;

@Service
public class OverviewService {
    private final OverviewMapper overviewMapper;

    public OverviewService(OverviewMapper overviewMapper) {
        this.overviewMapper = overviewMapper;
    }

    public List<TableCountDto> countCoreTables() {
        return overviewMapper.countCoreTables();
    }
}
