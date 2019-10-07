/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.varb.schedule.buisness.logic.controllers.api;

import com.varb.schedule.buisness.models.dto.ErrorMessageDto;
import com.varb.schedule.buisness.models.dto.PeriodPostDto;
import com.varb.schedule.buisness.models.dto.PeriodPutDto;
import com.varb.schedule.buisness.models.dto.PeriodResponseDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-07T17:01:51.686219+03:00[Europe/Minsk]")

@Validated
@Api(value = "period", description = "the period API")
public interface PeriodApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "Удалить существующий период", nickname = "periodDelete", notes = "", authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "period", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/period/{periodId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> periodDelete(@ApiParam(value = "",required=true) @PathVariable("periodId") Long periodId) {
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @ApiOperation(value = "Список периодов", nickname = "periodGet", notes = "", response = PeriodResponseDto.class, responseContainer = "List", authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "period", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Array of event", response = PeriodResponseDto.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/period",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<PeriodResponseDto>> periodGet() {
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


    @ApiOperation(value = "Добавить период", nickname = "periodPost", notes = "", response = PeriodResponseDto.class, authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "period", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = PeriodResponseDto.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/period",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<PeriodResponseDto> periodPost(@ApiParam(value = ""  )  @Valid @RequestBody PeriodPostDto periodPostDto) {
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


    @ApiOperation(value = "Редактировать существующие периоды", nickname = "periodPut", notes = "", response = PeriodResponseDto.class, authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "period", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = PeriodResponseDto.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/period/{periodId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<PeriodResponseDto> periodPut(@ApiParam(value = "",required=true) @PathVariable("periodId") Long periodId,@ApiParam(value = ""  )  @Valid @RequestBody PeriodPutDto periodPutDto) {
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
