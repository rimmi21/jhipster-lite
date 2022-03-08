package tech.jhipster.lite.generator.githubactions.application;

import static tech.jhipster.lite.TestUtils.tmpProject;
import static tech.jhipster.lite.generator.githubactions.application.GithubActionsAssertFiles.assertFilesYml;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.jhipster.lite.IntegrationTest;
import tech.jhipster.lite.generator.project.domain.Project;

@IntegrationTest
class GithubActionsApplicationServiceIT {

  @Autowired
  GithubActionsApplicationService githubActionsApplicationService;

  @Test
  void shouldInit() {
    Project project = tmpProject();

    githubActionsApplicationService.init(project);

    assertFilesYml(project);
  }
}