package tech.jhipster.light.generator.project.infrastructure.primary.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static tech.jhipster.light.generator.project.domain.DefaultConfig.BASE_NAME;

import java.util.Map;
import org.junit.jupiter.api.Test;
import tech.jhipster.light.UnitTest;
import tech.jhipster.light.error.domain.MissingMandatoryValueException;
import tech.jhipster.light.generator.project.domain.Project;

@UnitTest
class ProjectDTOTest {

  @Test
  void shouldBuild() {
    ProjectDTO projectDTO = buildProjectDTO();

    assertThat(projectDTO.getFolder()).isEqualTo("/tmp/chips");
    assertThat(projectDTO.getGeneratorJhipster()).containsEntry(BASE_NAME, "chips");
  }

  @Test
  void shouldToProject() {
    ProjectDTO projectDTO = buildProjectDTO();

    Project project = ProjectDTO.toProject(projectDTO);

    assertThat(project).isNotNull();
    assertThat(project.getFolder()).isEqualTo("/tmp/chips");
    assertThat(project.getConfig()).containsEntry(BASE_NAME, "chips");
  }

  @Test
  void shouldNotToProjectWithNull() {
    assertThatThrownBy(() -> ProjectDTO.toProject(null))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("projectDTO");
  }

  @Test
  void shouldToProjectWithNullFolder() {
    ProjectDTO projectDTO = buildProjectDTO().folder(null);

    assertThatThrownBy(() -> ProjectDTO.toProject(projectDTO))
      .isExactlyInstanceOf(MissingMandatoryValueException.class)
      .hasMessageContaining("folder");
  }

  private ProjectDTO buildProjectDTO() {
    return new ProjectDTO().folder("/tmp/chips").generatorJhipster(Map.of(BASE_NAME, "chips"));
  }
}