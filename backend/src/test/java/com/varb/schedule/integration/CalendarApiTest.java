package com.varb.schedule.integration;

import com.varb.schedule.buisness.logic.controllers.apiimpl.CalendarApiImpl;
import com.varb.schedule.buisness.logic.repository.CalendarRepository;
import com.varb.schedule.buisness.logic.repository.EventRepository;
import com.varb.schedule.buisness.logic.repository.UnitRepository;
import com.varb.schedule.buisness.models.dto.CalendarDto;
import com.varb.schedule.buisness.models.dto.CalendarReqDto;
import com.varb.schedule.buisness.models.dto.CalendarResponseDto;
import com.varb.schedule.buisness.models.entity.Calendar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testing {@link CalendarApiImpl}
 */
public class CalendarApiTest extends AbstractIntegrationTest {

    private static final String BASE_URL = "/api/calendar";

    @Autowired
    private CalendarRepository repository;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private EventRepository eventRepository;

    @Test
    @DisplayName("Получение списка календарей")
    @Sql("/db/scripts/calendar/InsertCalendarData.sql")
    public void testGetCalendarList() throws Exception {
        var entities = repository.findAll();

        //validate data has been initialized correctly
        assertTrue(entities.size() > 0);

        MvcResult mvcResult = mockMvc.perform(get(BASE_URL)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        var dtoList = asListOfObjects(mvcResult, CalendarResponseDto.class);
        assertEquals(entities.size(), dtoList.size());

        for (var entity : entities) {
            var dto = dtoList.stream()
                    .filter(d -> d.getCalendarId().equals(entity.getCalendarId()))
                    .findFirst().get();

            //assertion according to the initialization data of sql scripts above
            assertAll("Has to return initialized before the test data",
                    //first entity
                    () -> assertEquals(entity.getCalendarId(), dto.getCalendarId()),
                    () -> assertEquals(entity.getName(), dto.getName()),
                    () -> assertEquals(entity.isAstronomical(), dto.getIsAstronomical()),
                    () -> assertEquals(entity.getShift(), dto.getShift().intValue())
            );
        }
    }

    @Test
    @DisplayName("Получение конкретного календаря")
    @Sql("/db/scripts/calendar/InsertCalendarData.sql")
    public void testGetCalendar() throws Exception {
        var entities = repository.findAll();

        //validate data has been initialized correctly
        assertTrue(entities.size() > 0);
        var entity = entities.get(0);

        final MvcResult mvcResult = mockMvc.perform(get(BASE_URL+ "/" +entity.getCalendarId())
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        final String responseStr = mvcResult.getResponse().getContentAsString();

        var dto = asObject(responseStr, CalendarResponseDto.class);

        //assertion according to the initialization data of sql scripts above
        assertAll("Has to return initialized before the test data",
                //first entity
                () -> assertEquals(entity.getCalendarId(), dto.getCalendarId()),
                () -> assertEquals(entity.getName(), dto.getName()),
                () -> assertEquals(entity.isAstronomical(), dto.getIsAstronomical()),
                () -> assertEquals(entity.getShift(), dto.getShift().intValue())
        );
    }

    @Test
    @DisplayName("Создание нового календаря")
    public void testPostCalendar() throws Exception {
        var actualDto = new CalendarReqDto()
                .isAstronomical(false)
                .name("test calendar")
                .shift(5)
                .dateFrom(LocalDate.of(2019,9,1))
                .dateTo(LocalDate.of(2019,9,6));

        mockMvc.perform(post(BASE_URL)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(actualDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.calendarId").isNumber())
                .andExpect(jsonPath("$.name").value(actualDto.getName()))
                .andExpect(jsonPath("$.shift").value(actualDto.getShift()))
                .andExpect(jsonPath("$.isAstronomical").value(actualDto.getIsAstronomical()))
                .andExpect(jsonPath("$.dateFrom").value(actualDto.getDateFrom().toString()))
                .andExpect(jsonPath("$.dateTo").value(actualDto.getDateTo().toString()))
        ;

        //second level validation
        var entities = repository.findAll();
        assertEquals(1, entities.size());
        var entity = entities.get(0);
        assertAll("Assertion of just added period",
                () -> assertNotNull(entity.getCalendarId()),
                () -> assertEquals(actualDto.getName(), entity.getName()),
                () -> assertEquals(actualDto.getShift().intValue(), entity.getShift()),
                () -> assertEquals(actualDto.getIsAstronomical(), entity.isAstronomical())
        );
    }

    @Test
    @DisplayName("Изменение существующего календаря")
    @Sql("/db/scripts/calendar/InsertCalendarData.sql")
    public void testpatchCalendar() throws Exception {

        var entitiesBeforeUpdate = repository.findAll();
        //validate data has been initialized correctly
        assertTrue(entitiesBeforeUpdate.size() > 0);

        var entityBeforeUpdate = entitiesBeforeUpdate.get(0);
        entityManager.detach(entityBeforeUpdate);

        var actualDto = new CalendarDto()
                .name("newName")
                .shift(12);

        mockMvc.perform(patch(BASE_URL + "/" + entityBeforeUpdate.getCalendarId())
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(asJsonString(actualDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.calendarId").value(entityBeforeUpdate.getCalendarId()))
                .andExpect(jsonPath("$.name").value(actualDto.getName()))
                .andExpect(jsonPath("$.shift").value(actualDto.getShift()))
                .andExpect(jsonPath("$.isAstronomical").value(entityBeforeUpdate.isAstronomical()));

        var entityAfterUpdate = repository.findById(entityBeforeUpdate.getCalendarId()).get();
        //second level validation
        assertAll("Assertion of just updated entityBeforeUpdate",
                () -> assertEquals(entityBeforeUpdate.getCalendarId(), entityAfterUpdate.getCalendarId()),
                () -> assertEquals(actualDto.getName(), entityAfterUpdate.getName()),
                () -> assertEquals(actualDto.getShift().intValue(), entityAfterUpdate.getShift()),
                () -> assertEquals(entityBeforeUpdate.isAstronomical(), entityAfterUpdate.isAstronomical())
        );
    }

    @Test
    @DisplayName("Удаление существующего календаря")
    @Sql({
            "/db/scripts/calendar/InsertCalendarData.sql",
            "/db/scripts/calendar/CalendarTestRequiredData.sql"
    })
    public void testDeleteEvent() throws Exception {
        long calendarIdToDelete = 2L;
        var entitiesFromDb = repository.findAll();
        //validate data has been initialized correctly
        assertTrue(entitiesFromDb.size() > 0);

        int rowsNum = entitiesFromDb.size();
        Optional<Calendar> calendarToDeleteOpt = entitiesFromDb.stream().filter(calendar -> calendar.getCalendarId().equals(calendarIdToDelete)).findFirst();
        assertTrue(calendarToDeleteOpt.isPresent());
        Calendar calendarToDelete = calendarToDeleteOpt.get();

        mockMvc.perform(delete(BASE_URL + "/" + calendarToDelete.getCalendarId())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        //assert after deleting one event
        var afterDeleteList = repository.findAll();
        assertEquals(rowsNum - 1, afterDeleteList.size());
        assertFalse(afterDeleteList.contains(calendarToDelete));

        assertAll("Assertion of cascade delete",
                () -> assertEquals(0, eventRepository.findAll().stream().filter(e -> e.getCalendarId().equals(calendarIdToDelete)).count()),
                () -> assertEquals(0, unitRepository.findAll().stream().filter(e -> e.getCalendarId().equals(calendarIdToDelete)).count())
        );
    }
}
