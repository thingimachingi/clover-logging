package com.clover.log.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * LogInfo
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-14T17:58:35.089-07:00[America/Los_Angeles]")
public class LogInfo {

  @JsonProperty("versionNumber")
  private Integer versionNumber;

  @JsonProperty("className")
  private String className;

  @JsonProperty("methodName")
  private String methodName;

  @JsonProperty("eventTime")
  private Long eventTime;

  @JsonProperty("serialNumber")
  private String serialNumber;

  @JsonProperty("requestUuid")
  private String requestUuid;

  public LogInfo versionNumber(Integer versionNumber) {
    this.versionNumber = versionNumber;
    return this;
  }

  /**
   * Get versionNumber
   * @return versionNumber
  */
  
  @Schema(name = "versionNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Integer getVersionNumber() {
    return versionNumber;
  }

  public void setVersionNumber(Integer versionNumber) {
    this.versionNumber = versionNumber;
  }

  public LogInfo className(String className) {
    this.className = className;
    return this;
  }

  /**
   * Get className
   * @return className
  */
  
  @Schema(name = "className", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public LogInfo methodName(String methodName) {
    this.methodName = methodName;
    return this;
  }

  /**
   * Get methodName
   * @return methodName
  */
  
  @Schema(name = "methodName", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public LogInfo eventTime(Long eventTime) {
    this.eventTime = eventTime;
    return this;
  }

  /**
   * Get eventTime
   * @return eventTime
  */
  
  @Schema(name = "eventTime", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public Long getEventTime() {
    return eventTime;
  }

  public void setEventTime(Long eventTime) {
    this.eventTime = eventTime;
  }

  public LogInfo serialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
    return this;
  }

  /**
   * Get serialNumber
   * @return serialNumber
  */
  
  @Schema(name = "serialNumber", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public LogInfo requestUuid(String requestUuid) {
    this.requestUuid = requestUuid;
    return this;
  }

  /**
   * Get requestUuid
   * @return requestUuid
  */
  
  @Schema(name = "requestUuid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getRequestUuid() {
    return requestUuid;
  }

  public void setRequestUuid(String requestUuid) {
    this.requestUuid = requestUuid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogInfo logInfo = (LogInfo) o;
    return Objects.equals(this.versionNumber, logInfo.versionNumber) &&
        Objects.equals(this.className, logInfo.className) &&
        Objects.equals(this.methodName, logInfo.methodName) &&
        Objects.equals(this.eventTime, logInfo.eventTime) &&
        Objects.equals(this.serialNumber, logInfo.serialNumber) &&
        Objects.equals(this.requestUuid, logInfo.requestUuid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(versionNumber, className, methodName, eventTime, serialNumber, requestUuid);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogInfo {\n");
    sb.append("    versionNumber: ").append(toIndentedString(versionNumber)).append("\n");
    sb.append("    className: ").append(toIndentedString(className)).append("\n");
    sb.append("    methodName: ").append(toIndentedString(methodName)).append("\n");
    sb.append("    eventTime: ").append(toIndentedString(eventTime)).append("\n");
    sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
    sb.append("    requestUuid: ").append(toIndentedString(requestUuid)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

