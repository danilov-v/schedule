package com.varb.schedule.buisness.logic.controllers.apiimpl;

import com.varb.schedule.buisness.logic.controllers.ApiController;
import com.varb.schedule.buisness.logic.controllers.api.CalendarApi;
import com.varb.schedule.buisness.logic.service.CalendarService;
import com.varb.schedule.buisness.models.business.PrivilegeEnum;
import com.varb.schedule.buisness.models.dto.CalendarDto;
import com.varb.schedule.buisness.models.dto.CalendarReqDto;
import com.varb.schedule.buisness.models.dto.CalendarResponseDto;
import com.varb.schedule.config.modelmapper.ModelMapperCustomize;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;

import javax.validation.Valid;
import java.util.List;

@ApiController
@RequiredArgsConstructor
public class CalendarApiImpl implements CalendarApi {
    private final CalendarService calendarService;
    private final ModelMapperCustomize modelMapper;

    @Secured(PrivilegeEnum.Code.READ_WRITE)
    @Override
    public ResponseEntity<Void> calendarDelete(Long calendarId) {
        calendarService.delete(calendarId);
        return ResponseEntity.ok().build();
    }

    @Secured(PrivilegeEnum.Code.READ)
    @Override
    public ResponseEntity<List<CalendarResponseDto>> calendarGet() {
        return ResponseEntity.ok(
                modelMapper.mapToList(calendarService.findAll(), CalendarResponseDto.class));
    }

    @Secured(PrivilegeEnum.Code.READ)
    @Override
    public ResponseEntity<CalendarResponseDto> calendarGetById(Long calendarId) {
        return ResponseEntity.ok(
                modelMapper.map(calendarService.findById(calendarId), CalendarResponseDto.class));
    }

    @Secured(PrivilegeEnum.Code.READ_WRITE)
    @Override
    public ResponseEntity<CalendarResponseDto> calendarPost(@Valid CalendarReqDto calendarReqDto) {
        return ResponseEntity.ok(
                modelMapper.map(calendarService.add(calendarReqDto), CalendarResponseDto.class));
    }

    @Secured(PrivilegeEnum.Code.READ_WRITE)
    @Override
    public ResponseEntity<CalendarResponseDto> calendarPatch(Long calendarId, @Valid CalendarDto calendarDto) {
        return ResponseEntity.ok(
                modelMapper.map(calendarService.update(calendarId, calendarDto), CalendarResponseDto.class));
    }

}
