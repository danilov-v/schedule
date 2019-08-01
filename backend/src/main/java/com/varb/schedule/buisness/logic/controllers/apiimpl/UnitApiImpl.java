package com.varb.schedule.buisness.logic.controllers.apiimpl;

import com.varb.schedule.buisness.logic.controllers.api.UnitApi;
import com.varb.schedule.buisness.logic.service.UnitService;
import com.varb.schedule.buisness.models.dto.UnitPostDto;
import com.varb.schedule.buisness.models.dto.UnitPutDto;
import com.varb.schedule.buisness.models.dto.UnitResponseDto;
import com.varb.schedule.buisness.models.dto.UnitResponseTreeDto;
import com.varb.schedule.buisness.models.entity.Unit;
import com.varb.schedule.buisness.models.mappers.UnitMapper;
import com.varb.schedule.config.modelmapper.ModelMapperCustomize;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UnitApiImpl implements UnitApi {
    private final UnitService unitService;
    private final ModelMapperCustomize modelMapper;
    private final UnitMapper unitMapper;

    @Override
    public ResponseEntity<List<UnitResponseDto>> unitGet() {
        return ResponseEntity.ok(
                modelMapper.mapList(unitService.getAll(), UnitResponseDto.class));
    }

    @Override
    public ResponseEntity<List<UnitResponseTreeDto>> unitGetTree(@NotNull @Valid LocalDate dateFrom, @Valid Optional<LocalDate> dateTo) {
        return ResponseEntity.ok(
                unitMapper.convertToThree(unitService.getAllWithEvents(dateFrom, dateTo.orElse(null))));
    }

    @Override
    public ResponseEntity<UnitResponseDto> unitPost(@Valid UnitPostDto unitPostDto) {
        return ResponseEntity.ok(
                modelMapper.map(unitService.add(unitPostDto),
                        UnitResponseDto.class));
    }

    @Override
    public ResponseEntity<Void> unitDelete(Long unitId) {
        unitService.delete(unitId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UnitResponseDto> unitPut(Long unitId, @Valid UnitPutDto unitPutDto) {
        Unit unit = unitService.update(unitId, unitPutDto);
        return ResponseEntity.ok(
                modelMapper.map(unit, UnitResponseDto.class));
    }
}
