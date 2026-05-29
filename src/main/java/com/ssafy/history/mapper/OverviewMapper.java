package com.ssafy.history.mapper;

import java.util.List;

import com.ssafy.history.dto.TableCountDto;

public interface OverviewMapper {
    List<TableCountDto> countCoreTables();
}
