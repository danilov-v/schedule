package com.varb.schedule.buisness.logic.service;

import com.varb.schedule.buisness.logic.repository.UnitRepository;
import com.varb.schedule.buisness.models.dto.UnitPostDto;
import com.varb.schedule.buisness.models.dto.UnitPutDto;
import com.varb.schedule.buisness.models.entity.Unit;
import com.varb.schedule.config.modelmapper.ModelMapperCustomize;
import com.varb.schedule.exception.WebApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UnitService extends AbstractService<Unit, Long> {
    private final UnitRepository unitRepository;
    private final ModelMapperCustomize modelMapper;

    public Unit add(UnitPostDto unitPost) {
        Unit unit = modelMapper.map(unitPost, Unit.class);
        checkParent(unit);
        return save(unit);
    }

    public Unit update(Long unitId, UnitPutDto unitPut) {
        Unit unit = findById(unitId);
        modelMapper.map(unitPut, unit);
        checkParent(unit);
        return unit;
    }

    public Set<Unit> getAllExtended(Long calendarId, LocalDate dateFrom, @Nullable LocalDate dateTo) {
        return unitRepository.findAllWithChilds(calendarId, dateFrom, dateTo);
    }

    public List<Unit> findAll(@Nullable Long calendarId) {
        if (calendarId != null) {
            //units for specified calendarId
            return unitRepository.findAllByCalendarId(calendarId);
        }

        //all units
        return findAll();
    }

    private void checkParent(Unit unit) {
        Long parentId = unit.getParentId();

        if (parentId == null)
            return;

        if (parentId==0L) {
            unit.setParentId(null);
            return;
        }

        if(parentId.equals(unit.getUnitId()))
            throw new WebApiException("Невозможно установить parentId равный unitId");

        Unit parentUnit = checkExists(parentId);

        //check that calendars are the same
        if (!parentUnit.getCalendarId().equals(unit.getCalendarId())) {
            throw new WebApiException(
                    "Календарь, в котором создано родительское подразделение отличается от текущего: unit.calendarId = "
                            +unit.getCalendarId() +", parentUnit.calendarId = " + parentUnit.getCalendarId());
        }

//        if (parent.getUnitLevel() >= unit.getUnitLevel())
//            throw new ServiceException("unitLevel должен быть больше чем у родительской сущности!");
    }

    @Override
    protected String notFindMessage(Long unitId) {
        return "Не найдено подразделение (unitId=" + unitId + ")";
    }
}
