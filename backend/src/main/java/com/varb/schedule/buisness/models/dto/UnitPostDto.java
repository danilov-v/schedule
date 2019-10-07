package com.varb.schedule.buisness.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * UnitPostDto
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2019-10-07T17:01:51.686219+03:00[Europe/Minsk]")

public class UnitPostDto   {
  @JsonProperty("title")
  private String title;

  @JsonProperty("flag")
  private String flag;

  @JsonProperty("exists")
  private Boolean exists;

  @JsonProperty("parentId")
  private Long parentId;

  public UnitPostDto title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Название подразделения
   * @return title
  */
  @ApiModelProperty(required = true, value = "Название подразделения")
  @NotNull

@Size(min=2) 
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public UnitPostDto flag(String flag) {
    this.flag = flag;
    return this;
  }

  /**
   * Путь к изображению флага
   * @return flag
  */
  @ApiModelProperty(required = true, value = "Путь к изображению флага")
  @NotNull


  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public UnitPostDto exists(Boolean exists) {
    this.exists = exists;
    return this;
  }

  /**
   * Планируемое подразделение? (true/false)
   * @return exists
  */
  @ApiModelProperty(required = true, value = "Планируемое подразделение? (true/false)")
  @NotNull


  public Boolean getExists() {
    return exists;
  }

  public void setExists(Boolean exists) {
    this.exists = exists;
  }

  public UnitPostDto parentId(Long parentId) {
    this.parentId = parentId;
    return this;
  }

  /**
   * Ссылка на подразделение верхнего уровня (для подразделения первого уровня не указывается!) 
   * @return parentId
  */
  @ApiModelProperty(value = "Ссылка на подразделение верхнего уровня (для подразделения первого уровня не указывается!) ")


  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UnitPostDto unitPost = (UnitPostDto) o;
    return Objects.equals(this.title, unitPost.title) &&
        Objects.equals(this.flag, unitPost.flag) &&
        Objects.equals(this.exists, unitPost.exists) &&
        Objects.equals(this.parentId, unitPost.parentId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, flag, exists, parentId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UnitPostDto {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    flag: ").append(toIndentedString(flag)).append("\n");
    sb.append("    exists: ").append(toIndentedString(exists)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
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

