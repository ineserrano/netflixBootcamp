package com.di4.bootcamp.subscriptions.services;

import com.di4.bootcamp.subscriptions.dto.ProfileDto;
import com.di4.bootcamp.subscriptions.exceptions.D4iBootcampException;

public interface ProfileService {

    public ProfileDto createProfileForSubscription(Long subscriptionId, ProfileDto profileDto) throws D4iBootcampException;

    public ProfileDto updateProfileFromSubscription(Long profileId, ProfileDto profileDto) throws D4iBootcampException;
}
