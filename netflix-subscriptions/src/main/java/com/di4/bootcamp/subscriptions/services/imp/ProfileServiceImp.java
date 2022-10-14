package com.di4.bootcamp.subscriptions.services.imp;

import com.di4.bootcamp.subscriptions.dto.ProfileDto;
import com.di4.bootcamp.subscriptions.dto.SubscriptionDto;
import com.di4.bootcamp.subscriptions.entities.Profile;
import com.di4.bootcamp.subscriptions.entities.Subscription;
import com.di4.bootcamp.subscriptions.exceptions.D4iBootcampException;
import com.di4.bootcamp.subscriptions.exceptions.InternalServerErrorException;
import com.di4.bootcamp.subscriptions.repositories.ProfileRepository;
import com.di4.bootcamp.subscriptions.repositories.SubscriptionRepository;
import com.di4.bootcamp.subscriptions.services.ProfileService;
import com.di4.bootcamp.subscriptions.utils.constants.ExceptionConstants;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImp implements ProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileServiceImp.class);
    private final ProfileRepository profileRepository;
    private final SubscriptionRepository subscriptionRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public ProfileServiceImp(ProfileRepository profileRepository, SubscriptionRepository subscriptionRepository) {
        this.profileRepository = profileRepository;
        this.subscriptionRepository = subscriptionRepository;
    }


    @Override
    public ProfileDto createProfileForSubscription(final Long subscriptionId, final ProfileDto profileDto) throws D4iBootcampException {
        Profile profile = new Profile();
        Subscription subscription = subscriptionRepository.findById(subscriptionId).get();
        profile.setName(profileDto.getName());
        profile.setAlias(profileDto.getAlias());
        profile.setAvatar(profileDto.getAvatar());
        profile.setSubscription(subscription);
        try {
            profile = profileRepository.save(profile);
        } catch (Exception e) {
            LOGGER.error(ExceptionConstants.INTERNET_SERVER_ERROR, e);
            throw new InternalServerErrorException(ExceptionConstants.INTERNET_SERVER_ERROR);
        }
        return modelMapper.map(profile, ProfileDto.class);
    }

    @Override
    public ProfileDto updateProfileFromSubscription(final Long profileId, final ProfileDto profileDto) throws D4iBootcampException {
        Profile profile = profileRepository.findById(profileId).get();
        try {
            if(ObjectUtils.isNotEmpty(profileDto.getName())) profile.setName(profileDto.getName());
            if(ObjectUtils.isNotEmpty(profileDto.getAlias())) profile.setAlias(profileDto.getAlias());
            if(ObjectUtils.isNotEmpty(profileDto.getAvatar())) profile.setAvatar(profileDto.getAvatar());

            profile = profileRepository.save(profile);
        } catch (final Exception e) {
            LOGGER.error(ExceptionConstants.INTERNET_SERVER_ERROR, e);
            throw new InternalServerErrorException(ExceptionConstants.INTERNET_SERVER_ERROR);
        }
        return modelMapper.map(profile, ProfileDto.class);
    }
}
