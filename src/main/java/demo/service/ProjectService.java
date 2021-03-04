package demo.service;

import demo.model.Project;
import demo.model.response.ResponseResult;

public interface ProjectService {
    ResponseResult<Void> createProject(Project project);
}
