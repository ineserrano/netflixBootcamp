package com.di4.bootcamp.subscriptions.controllers.imp;

import com.di4.bootcamp.subscriptions.dto.ProfileDto;
import com.di4.bootcamp.subscriptions.dto.SubscriptionDto;
import com.di4.bootcamp.subscriptions.entities.Subscription;
import com.di4.bootcamp.subscriptions.repositories.SubscriptionRepository;
import com.di4.bootcamp.subscriptions.services.ProfileService;
import com.di4.bootcamp.subscriptions.services.SubscriptionService;
import com.di4.bootcamp.subscriptions.utils.constants.RestConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SubscriptionControllerImpTest {

    static final Long FIRST_SUBSCRIPTION_ID = 1L;
    static final String FIRST_SUBSCRIPTION_TYPE = "AFFORDABLE";
    static final BigDecimal FIRST_SUBSCRIPTION_PRICE = BigDecimal.valueOf(5.99);
    static final LocalDate FIRST_SUBSCRIPTION_START = LocalDate.of(2022, 10, 14);
    static final LocalDate FIRST_SUBSCRIPTION_END = LocalDate.of(2023, 10, 13);
    static final Long SECOND_SUBSCRIPTION_ID = 2L;
    static final String SECOND_SUBSCRIPTION_TYPE = "EXPENSIVE";
    static final BigDecimal SECOND_SUBSCRIPTION_PRICE = BigDecimal.valueOf(14.99);
    static final LocalDate SECOND_SUBSCRIPTION_START = LocalDate.of(2022, 10, 15);
    static final LocalDate SECOND_SUBSCRIPTION_END = LocalDate.of(2023, 10, 14);

    @InjectMocks
    SubscriptionControllerImp subscriptionController;

    @Mock
    SubscriptionService subscriptionService;


    ObjectWriter objectWriter;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc = MockMvcBuilders.standaloneSetup(subscriptionController).build();
    }

    @Test
    public void getAllSubscriptions() throws Exception {
        SubscriptionDto dummyFirstSubscription = createFirstDummySubscriptionDto();
        SubscriptionDto dummySecondSubscription = createSecondDummySubscriptionDto();
        List<SubscriptionDto> dummySubscriptionsList = new ArrayList<>();
        dummySubscriptionsList.add(dummyFirstSubscription);
        dummySubscriptionsList.add(dummySecondSubscription);

        when(subscriptionService.getAllSubscriptions()).thenReturn(dummySubscriptionsList);

        subscriptionController.getAllSubscriptions();

        verify(subscriptionService, times(1)).getAllSubscriptions();

        mockMvc.perform(get(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
                + RestConstants.RESOURCE_SUBSCRIPTION)).andExpect(status().isOk());
    }

    @Test
    public void createSubscription() throws Exception{
        SubscriptionDto dummySubscription = createDummySubscriptionDto(FIRST_SUBSCRIPTION_TYPE, FIRST_SUBSCRIPTION_END);

        when(subscriptionService.createSubscription(any())).thenReturn(dummySubscription);

        dummySubscription = subscriptionService.createSubscription(dummySubscription);

        verify(subscriptionService, times(1)).createSubscription(dummySubscription);

        mockMvc.perform(post(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
                        + RestConstants.RESOURCE_SUBSCRIPTION).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(dummySubscription)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateSubscription() throws Exception{
        final String URL_RESOURCE_ID = "/" + FIRST_SUBSCRIPTION_ID;
        final String NEW_TYPE = "STANDARD";
        final LocalDate NEW_END_DATE = LocalDate.of(2024, 12, 31);

        SubscriptionDto dummyGivenSubscription = new SubscriptionDto();
        dummyGivenSubscription.setId(FIRST_SUBSCRIPTION_ID);
        dummyGivenSubscription.setType(NEW_TYPE);
        dummyGivenSubscription.setEndDate(NEW_END_DATE);

        SubscriptionDto dummyUpdatedSubscription = createDummySubscriptionDto(NEW_TYPE, NEW_END_DATE);
        dummyUpdatedSubscription.setId(FIRST_SUBSCRIPTION_ID);

        when(subscriptionService.updateSubscription(anyLong(), any())).thenReturn(dummyUpdatedSubscription);

        subscriptionController.updateSubscription(FIRST_SUBSCRIPTION_ID, dummyGivenSubscription);

        verify(subscriptionService, times(1)).updateSubscription(FIRST_SUBSCRIPTION_ID, dummyGivenSubscription);

        dummyGivenSubscription = subscriptionService.updateSubscription(FIRST_SUBSCRIPTION_ID, dummyGivenSubscription);
        mockMvc.perform(patch(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
                        + RestConstants.RESOURCE_SUBSCRIPTION + URL_RESOURCE_ID).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON).content(objectWriter.writeValueAsString(dummyGivenSubscription)))
                .andExpect(status().isOk());
    }

    private SubscriptionDto createDummySubscriptionDto(String type, LocalDate endDate) {
        SubscriptionDto dummySubscriptionDto = new SubscriptionDto();
        dummySubscriptionDto.setType(type);
        dummySubscriptionDto.setPrice(FIRST_SUBSCRIPTION_PRICE);
        dummySubscriptionDto.setStartDate(FIRST_SUBSCRIPTION_START);
        dummySubscriptionDto.setEndDate(endDate);
        return dummySubscriptionDto;
    }

    private SubscriptionDto createFirstDummySubscriptionDto() {
        SubscriptionDto firstDummySubscription = new SubscriptionDto();
        firstDummySubscription.setId(FIRST_SUBSCRIPTION_ID);
        firstDummySubscription.setType(FIRST_SUBSCRIPTION_TYPE);
        firstDummySubscription.setPrice(FIRST_SUBSCRIPTION_PRICE);
        firstDummySubscription.setStartDate(FIRST_SUBSCRIPTION_START);
        firstDummySubscription.setEndDate(FIRST_SUBSCRIPTION_END);
        return firstDummySubscription;
    }

    private SubscriptionDto createSecondDummySubscriptionDto() {
        SubscriptionDto secondDummySubscription = new SubscriptionDto();
        secondDummySubscription.setId(SECOND_SUBSCRIPTION_ID);
        secondDummySubscription.setType(SECOND_SUBSCRIPTION_TYPE);
        secondDummySubscription.setPrice(SECOND_SUBSCRIPTION_PRICE);
        secondDummySubscription.setStartDate(SECOND_SUBSCRIPTION_START);
        secondDummySubscription.setEndDate(SECOND_SUBSCRIPTION_END);
        return secondDummySubscription;
    }
}