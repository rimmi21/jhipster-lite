package tech.jhipster.light.generator.server.javatool.error.infrastructure.primary.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static tech.jhipster.light.TestUtils.assertFileExist;
import static tech.jhipster.light.common.domain.FileUtils.getPath;
import static tech.jhipster.light.generator.project.domain.Constants.MAIN_JAVA;
import static tech.jhipster.light.generator.project.domain.Constants.TEST_JAVA;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.TestUtils;
import tech.jhipster.light.common.domain.FileUtils;
import tech.jhipster.light.error.domain.GeneratorException;
import tech.jhipster.light.generator.project.infrastructure.primary.dto.ProjectDTO;

@IntegrationTest
@AutoConfigureMockMvc
class ErrorResourceIT {

  @Autowired
  MockMvc mockMvc;

  @Test
  void shouldInit() throws Exception {
    ProjectDTO projectDTO = TestUtils.readFileToObject("json/chips.json", ProjectDTO.class);
    if (projectDTO == null) {
      throw new GeneratorException("Error when reading file");
    }
    projectDTO.folder(FileUtils.tmpDirForTest());

    mockMvc
      .perform(
        post("/api/servers/java/error").contentType(MediaType.APPLICATION_JSON).content(TestUtils.convertObjectToJsonBytes(projectDTO))
      )
      .andExpect(status().isOk());

    String pathMain = getPath(projectDTO.getFolder(), MAIN_JAVA, "tech/jhipster/chips/error/domain");
    assertFileExist(getPath(pathMain, "Assert.java"));
    assertFileExist(getPath(pathMain, "MissingMandatoryValueException.java"));
    assertFileExist(getPath(pathMain, "UnauthorizedValueException.java"));

    String pathTest = getPath(projectDTO.getFolder(), TEST_JAVA, "tech/jhipster/chips/error/domain");
    assertFileExist(getPath(pathTest, "AssertTest.java"));
    assertFileExist(getPath(pathTest, "MissingMandatoryValueExceptionTest.java"));
    assertFileExist(getPath(pathTest, "UnauthorizedValueExceptionTest.java"));
  }
}