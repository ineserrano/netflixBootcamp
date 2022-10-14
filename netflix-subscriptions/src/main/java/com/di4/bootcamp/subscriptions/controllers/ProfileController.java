package com.di4.bootcamp.subscriptions.controllers;

import com.di4.bootcamp.subscriptions.dto.ProfileDto;
import com.di4.bootcamp.subscriptions.dto.SubscriptionDto;
import com.di4.bootcamp.subscriptions.exceptions.D4iBootcampException;
import com.di4.bootcamp.subscriptions.responses.D4iBootcampResponse;

import java.util.List;

public interface ProfileController {

    public D4iBootcampResponse<ProfileDto> createProfileForSubscription(Long subscriptionId, ProfileDto profileDto) throws D4iBootcampException;

    public D4iBootcampResponse<ProfileDto> updateProfileFromSubscription(Long profileId, ProfileDto profileDto) throws D4iBootcampException;
}
