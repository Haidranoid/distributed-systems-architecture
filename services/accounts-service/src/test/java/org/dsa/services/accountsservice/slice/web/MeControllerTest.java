package org.dsa.services.accountsservice.slice.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dsa.core.sharedstarter.common.constants.Role;
import org.dsa.core.sharedstarter.testing.annotations.WebSliceEnvironment;
import org.dsa.services.accountsservice.common.fixtures.AccountDtoFixtures;
import org.dsa.services.accountsservice.controller.MeController;
import org.dsa.services.accountsservice.controller.advice.GlobalControllerAdvice;
import org.dsa.services.accountsservice.service.MeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

@WebSliceEnvironment(MeController.class)
@Import(GlobalControllerAdvice.class)
public class MeControllerTest {

  @Autowired private MockMvc mvc;
  @Autowired private ObjectMapper objectMapper;

  @MockitoBean private MeService meService;

  @Test
  @DisplayName("GET /api/v1/accounts/me returns 200 when user has a session")
  public void me_whenIsThereASessionActive_shouldReturn202() throws Exception {
    var session = AccountDtoFixtures.meAccountResponseDto(1L);

    when(meService.me()).thenReturn(session);

    mvc.perform(get("/api/v1/accounts/me"))
        .andExpect(status().isAccepted())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.username").value("ronald"))
        .andExpect(jsonPath("$.role").value(Role.USER.name()));
  }

  @Test
  @DisplayName("PATCH /api/v1/accounts/me/change-password returns 200 when user has a session")
  public void meChangePassword_whenAccountExists_shouldReturn200() throws Exception {
    var changePasswordDto = AccountDtoFixtures.changePasswordAccountDto();

    mvc.perform(
            patch("/api/v1/accounts/me/change-password")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(changePasswordDto)))
        .andExpect(status().isNoContent());

    // verify(meService)
    //       .changePassword(any());
  }
}
