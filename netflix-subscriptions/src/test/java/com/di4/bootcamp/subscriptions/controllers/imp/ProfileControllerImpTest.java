package com.di4.bootcamp.subscriptions.controllers.imp;

import com.di4.bootcamp.subscriptions.dto.ProfileDto;
import com.di4.bootcamp.subscriptions.dto.SubscriptionDto;
import com.di4.bootcamp.subscriptions.entities.Subscription;
import com.di4.bootcamp.subscriptions.exceptions.D4iBootcampException;
import com.di4.bootcamp.subscriptions.repositories.SubscriptionRepository;
import com.di4.bootcamp.subscriptions.services.ProfileService;
import com.di4.bootcamp.subscriptions.services.SubscriptionService;
import com.di4.bootcamp.subscriptions.utils.constants.RestConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProfileControllerImpTest {

    static final String URL_SLASH = "/";
    static final String URL_PROFILES = "/profiles";
    static final Long PROFILE_ID = 1L;
    static final String PROFILE_NAME = "Joe Doe";
    static final String PROFILE_ALIAS = "Dummy Frog";
    static final String PROFILE_AVATAR = "/frog-sunglasses.png";
    static final String NEW_PROFILE_ALIAS = "Rocky Duck";
    static final String NEW_PROFILE_AVATAR = "/skull-glitter.png";
    static final Long SUBSCRIPTION_ID = 2L;
    static final String SUBSCRIPTION_TYPE = "EXPENSIVE";
    static final BigDecimal SUBSCRIPTION_PRICE = BigDecimal.valueOf(14.99);
    static final LocalDate SUBSCRIPTION_START = LocalDate.of(2022, 10, 15);
    static final LocalDate SUBSCRIPTION_END = LocalDate.of(2023, 10, 14);
    final String RESOURCE_URL = RestConstants.RESOURCE_SUBSCRIPTION + URL_SLASH + SUBSCRIPTION_ID + URL_PROFILES;

    @InjectMocks
    ProfileControllerImp profileController;

    @Mock
    ProfileService profileService;

    ObjectWriter objectWriter;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        mockMvc = MockMvcBuilders.standaloneSetup(profileController).build();
    }

    @Test
    public void createProfileForSubscription() throws Exception {

        ProfileDto dummyProfile = createDummyProfileDto(PROFILE_ALIAS, PROFILE_AVATAR);

        when(profileService.createProfileForSubscription(anyLong(), any())).thenReturn(dummyProfile);

        dummyProfile = profileService.createProfileForSubscription(SUBSCRIPTION_ID, dummyProfile);

        verify(profileService, times(1)).createProfileForSubscription(SUBSCRIPTION_ID, dummyProfile);

        mockMvc.perform(post(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
                        + RESOURCE_URL).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectWriter.writeValueAsString(dummyProfile)))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateProfileFromSubscription() throws Exception {
        final String URL_RESOURCE_ID = URL_SLASH + PROFILE_ID;

        ProfileDto dummyGivenProfile = new ProfileDto();
        dummyGivenProfile.setId(PROFILE_ID);
        dummyGivenProfile.setAlias(NEW_PROFILE_ALIAS);
        dummyGivenProfile.setAvatar(NEW_PROFILE_AVATAR);

        ProfileDto dummyUpdatedProfile = createDummyProfileDto(NEW_PROFILE_ALIAS, NEW_PROFILE_AVATAR);
        dummyUpdatedProfile.setId(PROFILE_ID);

        when(profileService.updateProfileFromSubscription(anyLong(), any())).thenReturn(dummyUpdatedProfile);

        profileController.updateProfileFromSubscription(PROFILE_ID, dummyGivenProfile);

        verify(profileService, times(1)).updateProfileFromSubscription(PROFILE_ID, dummyGivenProfile);

        dummyGivenProfile = profileService.updateProfileFromSubscription(PROFILE_ID, dummyGivenProfile);
        mockMvc.perform(patch(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
                        + RESOURCE_URL + URL_RESOURCE_ID).contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON).content(objectWriter.writeValueAsString(dummyGivenProfile)))
                .andExpect(status().isOk());
    }

    private ProfileDto createDummyProfileDto(String alias, String avatar) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setName(PROFILE_NAME);
        profileDto.setAlias(alias);
        profileDto.setAvatar(avatar);
        return profileDto;
    }
}