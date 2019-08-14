package com.varb.schedule.buisness.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * PeriodResponseDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-08-14T11:33:21.219802+03:00[Europe/Minsk]")

public class PeriodResponseDto   {
  @JsonProperty("title")
  private String title;

  @JsonProperty("periodId")
  private Long periodId;

  public PeriodResponseDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Название периода
   * @return title
  */
  @ApiModelProperty(required = true, value = "Название периода")
  @NotNull


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public PeriodResponseDto periodId(Long periodId) {
    this.periodId = periodId;
    return this;
  }

  /**
   * Get periodId
   * @return periodId
  */
  @ApiModelProperty(value = "")


  public Long getPeriodId() {
    return periodId;
  }

  public void setPeriodId(Long periodId) {
    this.periodId = periodId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PeriodResponseDto periodResponse = (PeriodResponseDto) o;
    return Objects.equals(this.title, periodResponse.title) &&
        Objects.equals(this.periodId, periodResponse.periodId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, periodId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PeriodResponseDto {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    periodId: ").append(toIndentedString(periodId)).append("\n");
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

