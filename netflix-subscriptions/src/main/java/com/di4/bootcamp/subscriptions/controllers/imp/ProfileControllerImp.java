package com.di4.bootcamp.subscriptions.controllers.imp;

import com.di4.bootcamp.subscriptions.controllers.ProfileController;
import com.di4.bootcamp.subscriptions.dto.ProfileDto;
import com.di4.bootcamp.subscriptions.dto.SubscriptionDto;
import com.di4.bootcamp.subscriptions.exceptions.D4iBootcampException;
import com.di4.bootcamp.subscriptions.responses.D4iBootcampResponse;
import com.di4.bootcamp.subscriptions.utils.constants.CommonConstants;
import com.di4.bootcamp.subscriptions.utils.constants.RestConstants;
import com.di4.bootcamp.subscriptions.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1
        + RestConstants.RESOURCE_PROFILE)
public class ProfileControllerImp implements ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileControllerImp(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public D4iBootcampResponse<ProfileDto> createProfileForSubscription(@PathVariable Long subscriptionId, @RequestBody ProfileDto profileDto) throws D4iBootcampException {
        return new D4iBootcampResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.CREATED), CommonConstants.CREATED,
                profileService.createProfileForSubscription(subscriptionId, profileDto));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping(value = RestConstants.RESOURCE_PROFILE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public D4iBootcampResponse<ProfileDto> updateProfileFromSubscription(@PathVariable Long profileId, @RequestBody ProfileDto profileDto) throws D4iBootcampException {
        return new D4iBootcampResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.UPDATED,
                profileService.updateProfileFromSubscription(profileId, profileDto));
    }
}
