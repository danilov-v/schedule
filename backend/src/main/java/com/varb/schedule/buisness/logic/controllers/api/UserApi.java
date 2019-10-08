/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.varb.schedule.buisness.logic.controllers.api;

import com.varb.schedule.buisness.models.dto.ErrorMessageDto;
import com.varb.schedule.buisness.models.dto.UserPostDto;
import com.varb.schedule.buisness.models.dto.UserPutDto;
import com.varb.schedule.buisness.models.dto.UserResponseDto;
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
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-08T17:10:57.427864+03:00[Europe/Minsk]")

@Validated
@Api(value = "user", description = "the user API")
public interface UserApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @ApiOperation(value = "Регистрация нового пользователя (Пока что не реализовано!)", nickname = "register", notes = "", response = UserResponseDto.class, authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = UserResponseDto.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/user/register",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    default ResponseEntity<UserResponseDto> register(@ApiParam(value = ""  )  @Valid @RequestBody UserPostDto userPostDto) {
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


    @ApiOperation(value = "Удалить существующего пользователя (Пока что не реализовано!)", nickname = "userDelete", notes = "", authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/user/{login}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    default ResponseEntity<Void> userDelete(@ApiParam(value = "",required=true) @PathVariable("login") String login) {
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @ApiOperation(value = "Редактировать существующего пользователя (Пока что не реализовано!)", nickname = "userPut", notes = "", response = UserResponseDto.class, authorizations = {
        @Authorization(value = "JWT")
    }, tags={ "user", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = UserResponseDto.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorMessageDto.class),
        @ApiResponse(code = 500, message = "Internal server error", response = ErrorMessageDto.class) })
    @RequestMapping(value = "/user/{login}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    default ResponseEntity<UserResponseDto> userPut(@ApiParam(value = "",required=true) @PathVariable("login") String login,@ApiParam(value = ""  )  @Valid @RequestBody UserPutDto userPutDto) {
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
