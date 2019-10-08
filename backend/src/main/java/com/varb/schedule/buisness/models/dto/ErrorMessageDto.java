package com.varb.schedule.buisness.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Структура для отправки сообщения об ошибке. userMessage содержит сообщение для пользователя.
 */
@ApiModel(description = "Структура для отправки сообщения об ошибке. userMessage содержит сообщение для пользователя.")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-08T17:10:57.427864+03:00[Europe/Minsk]")

public class ErrorMessageDto   {
  @JsonProperty("code")
  private String code;

  @JsonProperty("devMessage")
  private String devMessage;

  @JsonProperty("userMessage")
  private String userMessage;

  public ErrorMessageDto code(String code) {
    this.code = code;
    return this;
  }

  /**
   * Внутренний код ошибки
   * @return code
  */
  @ApiModelProperty(required = true, value = "Внутренний код ошибки")
  @NotNull


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ErrorMessageDto devMessage(String devMessage) {
    this.devMessage = devMessage;
    return this;
  }

  /**
   * Сообщение для разработчика
   * @return devMessage
  */
  @ApiModelProperty(required = true, value = "Сообщение для разработчика")
  @NotNull


  public String getDevMessage() {
    return devMessage;
  }

  public void setDevMessage(String devMessage) {
    this.devMessage = devMessage;
  }

  public ErrorMessageDto userMessage(String userMessage) {
    this.userMessage = userMessage;
    return this;
  }

  /**
   * Сообщение для пользователя
   * @return userMessage
  */
  @ApiModelProperty(value = "Сообщение для пользователя")


  public String getUserMessage() {
    return userMessage;
  }

  public void setUserMessage(String userMessage) {
    this.userMessage = userMessage;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorMessageDto errorMessage = (ErrorMessageDto) o;
    return Objects.equals(this.code, errorMessage.code) &&
        Objects.equals(this.devMessage, errorMessage.devMessage) &&
        Objects.equals(this.userMessage, errorMessage.userMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, devMessage, userMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorMessageDto {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    devMessage: ").append(toIndentedString(devMessage)).append("\n");
    sb.append("    userMessage: ").append(toIndentedString(userMessage)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

