/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.gcloud.compute;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Objects;

/**
 * Base class for Google Compute Engine resource identities.
 */
public abstract class ResourceId implements Serializable {

  static final String REGEX =
      "(https?://(www|content).googleapis.com/compute/v1/)?projects/[^/]+/";
  private static final long serialVersionUID = -8028734746870421573L;

  private String project;

  ResourceId(String project) {
    this.project = project;
  }

  /**
   * Base URL for a resource URL.
   */
  private static final String BASE_URL = "https://www.googleapis.com/compute/v1/projects/";

  /**
   * Returns a fully qualified URL to the entity.
   */
  public String toUrl() {
    return BASE_URL + project;
  }

  /**
   * Returns the name of the project.
   */
  public final String project() {
    return project;
  }

  MoreObjects.ToStringHelper toStringHelper() {
    return MoreObjects.toStringHelper(this).add("project", project);
  }

  @Override
  public String toString() {
    return toStringHelper().toString();
  }

  final int baseHashCode() {
    return Objects.hash(project);
  }

  final boolean baseEquals(ResourceId resourceId) {
    return Objects.equals(toUrl(), resourceId.toUrl());
  }

  abstract ResourceId setProjectId(String projectId);
}
