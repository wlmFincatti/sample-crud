package br.com.wlmfincatti.bot.adapter.entrypoint;

import br.com.wlmfincatti.bot.adapter.entrypoint.model.BotRequest;
import br.com.wlmfincatti.bot.adapter.entrypoint.model.BotResponse;
import br.com.wlmfincatti.bot.adapter.mapper.BotMapper;
import br.com.wlmfincatti.bot.core.domain.Bot;
import br.com.wlmfincatti.bot.core.usecase.CreateBot;
import br.com.wlmfincatti.bot.core.usecase.DeleteBotById;
import br.com.wlmfincatti.bot.core.usecase.FindBotById;
import br.com.wlmfincatti.bot.core.usecase.UpdateBot;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static br.com.wlmfincatti.bot.fixture.BotFixture.gimmeABot;
import static br.com.wlmfincatti.bot.fixture.BotRequestFixture.gimmeABotRequest;
import static br.com.wlmfincatti.bot.fixture.BotResponsetFixture.gimmeABotResponse;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BotControllerTest {

    private MockMvc mockMvc;

    @Mock
    private FindBotById findBotById;

    @Mock
    private DeleteBotById deleteBotById;

    @Mock
    private UpdateBot updateBot;

    @Mock
    private CreateBot createBot;

    @Mock
    private BotMapper botMapper;

    @BeforeEach
    void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BotController(
                findBotById,
                createBot,
                deleteBotById,
                updateBot,
                botMapper
        )).build();
    }

    @Test
    void getBotById() throws Exception {
        final UUID id = UUID.randomUUID();
        final Bot bot = gimmeABot(id);
        final BotResponse botResponse = gimmeABotResponse(id);
        when(findBotById.execute(id)).thenReturn(bot);
        when(botMapper.convertBotToBotResponse(bot)).thenReturn(botResponse);

        final MockHttpServletResponse result = mockMvc.perform(get("/bots/{id}", id.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(result.getContentAsString(),
                is(equalTo(String.format("{\"id\":\"%s\",\"name\":\"test\"}", id))));
        verify(findBotById, times(1)).execute(id);
        verify(botMapper, times(1)).convertBotToBotResponse(bot);
    }

    @Test
    void createBot() throws Exception {
        final UUID id = UUID.randomUUID();
        final BotRequest botRequest = gimmeABotRequest(id);
        final BotResponse botResponse = gimmeABotResponse(id);
        final Bot bot = gimmeABot(id);
        when(createBot.execute(bot)).thenReturn(bot);
        when(botMapper.convertBotRequestToBot(botRequest)).thenReturn(bot);
        when(botMapper.convertBotToBotResponse(bot)).thenReturn(botResponse);

        final MockHttpServletResponse result = mockMvc.perform(post("/bots")
                        .content(new ObjectMapper().writeValueAsString(botRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse();

        assertThat(result.getHeader("location"),
                is(equalTo(String.format("http://localhost/bots/%s", id))));
        assertThat(result.getContentAsString(),
                is(equalTo(String.format("{\"id\":\"%s\",\"name\":\"test\"}", id))));
        verify(createBot, times(1)).execute(bot);
        verify(botMapper, times(1)).convertBotRequestToBot(botRequest);
        verify(botMapper, times(1)).convertBotToBotResponse(bot);
    }

    @Test
    void deleteBot() throws Exception {
        final UUID id = UUID.randomUUID();
        doNothing().when(deleteBotById).execute(id);

        mockMvc.perform(delete("/bots/{id}", id.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(deleteBotById, times(1)).execute(id);
    }

    @Test
    void updateBot() throws Exception {
        final UUID id = UUID.randomUUID();
        final BotRequest botRequest = gimmeABotRequest(id);
        final Bot bot = gimmeABot(id);
        final BotResponse botResponse = gimmeABotResponse(id);
        when(updateBot.execute(id, bot)).thenReturn(bot);
        when(botMapper.convertBotRequestToBot(botRequest)).thenReturn(bot);
        when(botMapper.convertBotToBotResponse(bot)).thenReturn(botResponse);

        final MockHttpServletResponse result = mockMvc.perform(put("/bots/{id}", id.toString())
                        .content(new ObjectMapper().writeValueAsString(botRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(result.getContentAsString(),
                is(equalTo(String.format("{\"id\":\"%s\",\"name\":\"test\"}", id))));
        verify(updateBot, times(1)).execute(id, bot);
        verify(botMapper, times(1)).convertBotRequestToBot(botRequest);
        verify(botMapper, times(1)).convertBotToBotResponse(bot);
    }
}