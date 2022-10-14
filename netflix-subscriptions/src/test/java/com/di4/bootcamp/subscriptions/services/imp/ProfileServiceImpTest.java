package com.di4.bootcamp.subscriptions.services.imp;

import com.di4.bootcamp.subscriptions.dto.ProfileDto;
import com.di4.bootcamp.subscriptions.dto.SubscriptionDto;
import com.di4.bootcamp.subscriptions.entities.Profile;
import com.di4.bootcamp.subscriptions.entities.Subscription;
import com.di4.bootcamp.subscriptions.exceptions.D4iBootcampException;
import com.di4.bootcamp.subscriptions.repositories.ProfileRepository;
import com.di4.bootcamp.subscriptions.repositories.SubscriptionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProfileServiceImpTest {

    static final Long FIRST_PROFILE_ID = 1L;
    static final String FIRST_PROFILE_NAME = "Joe Doe";
    static final String FIRST_PROFILE_ALIAS = "Dummy Frog";
    static final String FIRST_PROFILE_AVATAR = "/frog-sunglasses.png";
    static final String NEW_PROFILE_ALIAS = "Rocky Duck";
    static final String NEW_PROFILE_AVATAR = "/skull-glitter.png";
    static final Long SUBSCRIPTION_ID = 2L;


    @InjectMocks
    ProfileServiceImp profileService;

    @Mock
    ProfileRepository profileRepository;

    @Mock
    SubscriptionRepository subscriptionRepository;


    @Test
    public void createProfileForSubscription() throws D4iBootcampException {
        Profile dummyProfile = createDummyProfile(FIRST_PROFILE_ALIAS, FIRST_PROFILE_AVATAR);

        ProfileDto dummyProfileDto = createDummyProfileDto();

        when(subscriptionRepository.findById(SUBSCRIPTION_ID)).thenReturn(Optional.of(createDummySubscription()));
        when(profileRepository.save(any())).thenReturn(dummyProfile);

        ProfileDto profile = profileService.createProfileForSubscription(SUBSCRIPTION_ID, dummyProfileDto);

        verify(profileRepository, times(1)).save(any());
    }

    @Test
    public void updateProfileFromSubscription() throws D4iBootcampException {
        ProfileDto dummyGivenProfile = new ProfileDto();
        dummyGivenProfile.setId(FIRST_PROFILE_ID);
        dummyGivenProfile.setAlias(NEW_PROFILE_ALIAS);
        dummyGivenProfile.setAvatar(NEW_PROFILE_AVATAR);

        Profile dummyExistingProfile = createDummyProfile(FIRST_PROFILE_ALIAS, FIRST_PROFILE_AVATAR);

        Profile dummyUpdatedProfile = createDummyProfile(NEW_PROFILE_ALIAS, NEW_PROFILE_AVATAR);

        ArgumentCaptor<Profile> profileCaptor = ArgumentCaptor.forClass(Profile.class);

        when(profileRepository.findById(FIRST_PROFILE_ID)).thenReturn(Optional.of(dummyExistingProfile));
        when(profileRepository.save(profileCaptor.capture())).thenReturn(dummyUpdatedProfile);

        ProfileDto profile = profileService.updateProfileFromSubscription(dummyGivenProfile.getId(), dummyGivenProfile);

        verify(profileRepository, times(1)).findById(anyLong());
        verify(profileRepository, times(1)).save(any());

        assertEquals(dummyGivenProfile.getAlias(), profileCaptor.getValue().getAlias());
        assertEquals(dummyGivenProfile.getAvatar(), profile.getAvatar());
        assertEquals(dummyGivenProfile.getAlias(), profileCaptor.getValue().getAlias());
        assertEquals(dummyGivenProfile.getAvatar(), profile.getAvatar());
    }

    private Profile createDummyProfile(String alias, String avatar) {
        Profile profile = new Profile();
        profile.setId(FIRST_PROFILE_ID);
        profile.setName(FIRST_PROFILE_NAME);
        profile.setAlias(alias);
        profile.setAvatar(avatar);
        profile.setSubscription(createDummySubscription());
        return profile;
    }

    private ProfileDto createDummyProfileDto() {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setName(FIRST_PROFILE_NAME);
        profileDto.setAlias(FIRST_PROFILE_ALIAS);
        profileDto.setAvatar(FIRST_PROFILE_AVATAR);
        profileDto.setSubscriptions(createDummySubscriptionDto());
        return profileDto;
    }

    private Subscription createDummySubscription() {
        Subscription dummySubscription = new Subscription();
        dummySubscription.setId(SUBSCRIPTION_ID);
        return dummySubscription;
    }

    private SubscriptionDto createDummySubscriptionDto() {
        SubscriptionDto dummySubscriptionDto = new SubscriptionDto();
        dummySubscriptionDto.setId(SUBSCRIPTION_ID);
        return dummySubscriptionDto;
    }
}