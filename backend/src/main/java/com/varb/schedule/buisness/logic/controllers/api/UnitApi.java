/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.varb.schedule.buisness.logic.controllers.api;

import com.varb.schedule.buisness.models.dto.ErrorMessageDto;
import java.time.LocalDate;
import com.varb.schedule.buisness.models.dto.UnitPostDto;
import com.varb.schedule.buisness.models.dto.UnitPutDto;
import com.varb.schedule.buisness.models.dto.UnitResponseDto;
import com.varb.schedule.buisness.models.dto.UnitResponseTreeDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-15T13:05:40.298965+03:00[Europe/Minsk]")

@Validated
@Api(value = "unit", description = "the unit API")
public interface UnitApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "Удалить существующее подразделение", nickname = "unitDelete", notes = "", authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "unit", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/unit/{unitId}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> unitDelete(@ApiParam(value = "",required=true) @PathVariable("unitId") Long unitId) {
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @ApiOperation(value = "Список подразделений", nickname = "unitGet", notes = "", response = UnitResponseDto.class, responseContainer = "List", authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "unit", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Array of unit", response = UnitResponseDto.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/unit",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<UnitResponseDto>> unitGet(@ApiParam(value = "") @Valid @RequestParam(value = "calendarId", required = false) Optional<Long> calendarId) {
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


    @ApiOperation(value = "Древовидный список подразделений со списком событий", nickname = "unitGetTree", notes = "", response = UnitResponseTreeDto.class, responseContainer = "List", authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "unit", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Array of unit", response = UnitResponseTreeDto.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/unit/tree",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    default ResponseEntity<List<UnitResponseTreeDto>> unitGetTree(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "calendarId", required = true) Long calendarId,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "dateFrom", required = true) LocalDate dateFrom,@ApiParam(value = "") @Valid @RequestParam(value = "dateTo", required = false) Optional<LocalDate> dateTo) {
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


    @ApiOperation(value = "Создать подразделение", nickname = "unitPost", notes = "", response = UnitResponseDto.class, authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "unit", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = UnitResponseDto.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/unit",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<UnitResponseDto> unitPost(@ApiParam(value = ""  )  @Valid @RequestBody UnitPostDto unitPostDto) {
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


    @ApiOperation(value = "Редактировать существующее подразделение", nickname = "unitPut", notes = "", response = UnitResponseDto.class, authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "unit", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = UnitResponseDto.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/unit/{unitId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<UnitResponseDto> unitPut(@ApiParam(value = "",required=true) @PathVariable("unitId") Long unitId,@ApiParam(value = ""  )  @Valid @RequestBody UnitPutDto unitPutDto) {
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
