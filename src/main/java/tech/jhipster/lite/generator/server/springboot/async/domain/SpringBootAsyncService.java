package tech.jhipster.lite.generator.server.springboot.async.domain;

import tech.jhipster.lite.generator.project.domain.Project;

public interface SpringBootAsyncService {
  void init(Project project);

  void addJavaFiles(Project project);
  void addProperties(Project project);
}
