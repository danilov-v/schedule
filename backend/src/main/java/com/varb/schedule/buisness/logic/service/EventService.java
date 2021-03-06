package com.varb.schedule.buisness.logic.service;

import com.varb.schedule.buisness.logic.repository.EventRepository;
import com.varb.schedule.buisness.models.dto.EventDto;
import com.varb.schedule.buisness.models.dto.EventReqDto;
import com.varb.schedule.buisness.models.entity.Calendar;
import com.varb.schedule.buisness.models.entity.Event;
import com.varb.schedule.buisness.models.entity.Unit;
import com.varb.schedule.config.modelmapper.ModelMapperCustomize;
import com.varb.schedule.exception.WebApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class EventService extends AbstractService<Event, Long> {
    private final EventRepository eventRepository;
    private final ModelMapperCustomize modelMapper;
    private final EventTypeService eventTypeService;
    private final UnitService unitService;
    private final EventDurationService eventDurationService;
    private final ValidationService validationService;
    private final CalendarService calendarService;

    public static final String INTERSECTION_OF_EVENTS = "INTERSECTION_OF_EVENTS";
    private static final String DATES_INTERSECTION_MESSAGE = "Данное событие пересекается с уже существующим. " +
            "Проверьте даты его начала и окончания.";


    public Event add(EventReqDto eventPostDto) {
        Event event = modelMapper.map(eventPostDto, Event.class);
        event = save(event);
        manipulationAfterSave(event);
        return event;
    }

    public Event update(Long eventId, EventDto eventPut) {
        Event event = findById(eventId);
        modelMapper.map(eventPut, event);
        manipulationAfterSave(event);
        return event;
    }

    public List<Event> findByCalendarId(Long calendarId) {
        return eventRepository.findByCalendarId(calendarId);
    }

    public List<Event> findRecent(Long calendarId, int count) {
        LocalDate relativeCurrentDate = getRelativeCurrentDate(calendarId);
        return eventRepository.findRecent(calendarId, relativeCurrentDate, PageRequest.of(0, count));
    }

    /**
     * Необходимые проверки и обновления после сохранения события
     */
    private void manipulationAfterSave(Event event) {
        checkConsistencyAfterSave(event);
        eventDurationService.updateEventDuration(event);
        calendarService.updateCalendarTimeFrameAfterSaveEvent(event.getCalendarId(), event.getDateFrom(), event.getDateTo());
    }

    /**
     * Проверка согласованности данных
     */
    private void checkConsistencyAfterSave(Event event) {
        eventTypeService.checkExists(event.getEventTypeId());
        checkCalendarId(event);
        validationService.checkDates(event.getDateFrom(), event.getDateTo());
        checkIntersection(event);
    }

    /**
     * Получение относительного текущего времени.
     * @return возвращает текущую дату,         если события в календаре создаются относительно Автрономического времени
     * <br> возвращает текущую дату со сдвигом, если события в календаре создаются относительно Оперативного времени
     */
    private LocalDate getRelativeCurrentDate(Long calendarId){
        Calendar calendar = calendarService.findById(calendarId);

        LocalDate relativeCurrentDate;
        if (calendar.isAstronomical())
            relativeCurrentDate = LocalDate.now();
        else
            relativeCurrentDate = LocalDate.now().plusDays(calendar.getShift());

        return relativeCurrentDate;
    }


    private void checkIntersection(Event event) {
        List<Event> eventList = eventRepository.findIntersection(
                event.getCalendarId(), event.getDateFrom(), event.getDateTo(), event.getUnitId(), event.getEventId());

        if (!eventList.isEmpty()) {
            String ids = eventList.stream().map(e -> e.getEventId().toString()).collect(Collectors.joining(","));
            throw new WebApiException(
                    "Event you want to add has intersections with other events with ids: [" + ids + "])",
                    DATES_INTERSECTION_MESSAGE,
                    INTERSECTION_OF_EVENTS);
        }
    }

    private void checkCalendarId(Event event) {
        Unit unit = unitService.findById(event.getUnit().getUnitId());
        if (!unit.getCalendarId().equals(event.getCalendarId())) {
            throw new WebApiException("Невозможно добавить событие в подразделение из другого календаря: "
            +"event.calendarId = " +event.getCalendarId() +", unit.calendarId = " +unit.getCalendarId());
        }

    }

    @Override
    protected String notFindMessage(Long eventId) {
        return "Не существует события (eventId=" + eventId + ")";
    }
}
