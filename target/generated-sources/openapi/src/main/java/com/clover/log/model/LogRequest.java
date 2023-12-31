package com.clover.log.model;

import java.net.URI;
import java.util.Objects;
import com.clover.log.model.LogInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * LogRequest
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-10-14T17:58:35.089-07:00[America/Los_Angeles]")
public class LogRequest {

  @JsonProperty("packageName")
  private String packageName;

  @JsonProperty("logs")
  @Valid
  private List<LogInfo> logs = new ArrayList<>();

  @JsonProperty("tag")
  private String tag;

  public LogRequest packageName(String packageName) {
    this.packageName = packageName;
    return this;
  }

  /**
   * Get packageName
   * @return packageName
  */
  @NotNull 
  @Schema(name = "packageName", requiredMode = Schema.RequiredMode.REQUIRED)
  public String getPackageName() {
    return packageName;
  }

  public void setPackageName(String packageName) {
    this.packageName = packageName;
  }

  public LogRequest logs(List<LogInfo> logs) {
    this.logs = logs;
    return this;
  }

  public LogRequest addLogsItem(LogInfo logsItem) {
    this.logs.add(logsItem);
    return this;
  }

  /**
   * Get logs
   * @return logs
  */
  @NotNull @Valid 
  @Schema(name = "logs", requiredMode = Schema.RequiredMode.REQUIRED)
  public List<LogInfo> getLogs() {
    return logs;
  }

  public void setLogs(List<LogInfo> logs) {
    this.logs = logs;
  }

  public LogRequest tag(String tag) {
    this.tag = tag;
    return this;
  }

  /**
   * Get tag
   * @return tag
  */
  
  @Schema(name = "tag", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LogRequest logRequest = (LogRequest) o;
    return Objects.equals(this.packageName, logRequest.packageName) &&
        Objects.equals(this.logs, logRequest.logs) &&
        Objects.equals(this.tag, logRequest.tag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(packageName, logs, tag);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LogRequest {\n");
    sb.append("    packageName: ").append(toIndentedString(packageName)).append("\n");
    sb.append("    logs: ").append(toIndentedString(logs)).append("\n");
    sb.append("    tag: ").append(toIndentedString(tag)).append("\n");
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

