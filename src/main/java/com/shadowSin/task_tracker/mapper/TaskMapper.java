package com.shadowSin.task_tracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.shadowSin.task_tracker.dto.TaskRequest;
import com.shadowSin.task_tracker.dto.TaskResponse;
import com.shadowSin.task_tracker.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {


public TaskResponse taskIntoResponse(Task task);
@Mapping(target =  "id" , ignore = true)
public Task requestIntoTask(TaskRequest taskRequest);
    
}
