/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.varb.schedule.buisness.logic.controllers.api;

import com.varb.schedule.buisness.models.dto.ErrorMessageDto;
import com.varb.schedule.buisness.models.dto.EventDurationPutDto;
import com.varb.schedule.buisness.models.dto.EventDurationResponseDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-08-06T12:35:06.180427+03:00[Europe/Minsk]")

@Validated
@Api(value = "eventDuration", description = "the eventDuration API")
public interface EventDurationApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "Удалить существующее время выполнения события для подразделения", nickname = "eventDurationDelete", notes = "", authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "eventDuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/eventDuration/{unitId}/{eventTypeId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> eventDurationDelete(@ApiParam(value = "",required=true) @PathVariable("unitId") Long unitId,@ApiParam(value = "",required=true) @PathVariable("eventTypeId") Long eventTypeId) {
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @ApiOperation(value = "Получить время выполнения событий для подразделения", nickname = "eventDurationGet", notes = "", response = EventDurationResponseDto.class, responseContainer = "List", authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "eventDuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = EventDurationResponseDto.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/eventDuration",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<EventDurationResponseDto>> eventDurationGet(@ApiParam(value = "") @Valid @RequestParam(value = "unitId", required = false) Optional<Long> unitId) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "null");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.valueOf(200));

    }


    @ApiOperation(value = "Добавить/Редактировать существующее время выполнения события для подразделения", nickname = "eventDurationPut", notes = "", response = EventDurationResponseDto.class, authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "eventDuration", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = EventDurationResponseDto.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/eventDuration/{unitId}/{eventTypeId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<EventDurationResponseDto> eventDurationPut(@ApiParam(value = "",required=true) @PathVariable("unitId") Long unitId,@ApiParam(value = "",required=true) @PathVariable("eventTypeId") Long eventTypeId,@ApiParam(value = ""  )  @Valid @RequestBody EventDurationPutDto eventDurationPutDto) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    ApiUtil.setExampleResponse(request, "application/json", "null");
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.valueOf(200));

    }

}
