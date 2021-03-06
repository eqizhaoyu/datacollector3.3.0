/*
 * Copyright 2017 StreamSets Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsets.pipeline.stage.destination.hdfs.metadataexecutor;

import com.streamsets.pipeline.api.Label;
import com.streamsets.pipeline.lib.event.EventCreator;

public enum TaskType implements Label {
  CREATE_EMPTY_FILE("Create Empty File", HdfsMetadataExecutorEvents.FILE_CREATED),
  CHANGE_EXISTING_FILE("Change Metadata on Existing File", HdfsMetadataExecutorEvents.FILE_CHANGED),
  REMOVE_FILE("Remove File or Directory", HdfsMetadataExecutorEvents.FILE_REMOVED),
  ;

  private final String label;
  private final EventCreator eventCreator;

  TaskType(String label, EventCreator eventCreator) {
    this.label = label;
    this.eventCreator = eventCreator;
  }

  @Override
  public String getLabel() {
    return label;
  }

  public EventCreator getEventCreator() {
    return eventCreator;
  }

  public boolean isOneOf(TaskType ...types) {
    if(types == null) {
      return false;
    }

    for(TaskType t : types) {
      if(this == t) {
        return true;
      }
    }

    return false;
  }

}
