package com.varb.schedule.buisness.logic.service;

import com.varb.schedule.buisness.models.dto.EventTypeDto;
import com.varb.schedule.buisness.models.dto.EventTypeReqDto;
import com.varb.schedule.buisness.models.entity.EventType;
import com.varb.schedule.config.modelmapper.ModelMapperCustomize;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EventTypeService extends AbstractService<EventType, Long> {
    private final ModelMapperCustomize modelMapper;

    public EventType update(Long typeId, EventTypeDto eventTypePut) {
        EventType eventType = findById(typeId);
        modelMapper.map(eventTypePut, eventType);
        return eventType;
    }

    public EventType add(EventTypeReqDto eventTypePost) {
        EventType eventType = modelMapper.map(eventTypePost, EventType.class);
        return save(eventType);
    }

    @Override
    protected String notFindMessage(Long typeId) {
        return "Не найден тип события(typeId=" + typeId + ")";
    }
}
