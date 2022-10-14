package com.di4.bootcamp.subscriptions.services.imp;

import com.di4.bootcamp.subscriptions.entities.Subscription;
import com.di4.bootcamp.subscriptions.exceptions.D4iBootcampException;
import com.di4.bootcamp.subscriptions.exceptions.InternalServerErrorException;
import com.di4.bootcamp.subscriptions.repositories.SubscriptionRepository;
import com.di4.bootcamp.subscriptions.dto.SubscriptionDto;
import com.di4.bootcamp.subscriptions.services.SubscriptionService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.di4.bootcamp.subscriptions.utils.constants.ExceptionConstants;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImp implements SubscriptionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImp.class);

    private final SubscriptionRepository subscriptionRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public SubscriptionServiceImp(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public List<SubscriptionDto> getAllSubscriptions() throws D4iBootcampException {

        return subscriptionRepository.findAll().stream()
                                     .map(subscription -> modelMapper.map(subscription,
                                             SubscriptionDto.class)).collect(Collectors.toList());
    }

    @Override
    public SubscriptionDto createSubscription(final SubscriptionDto subscriptionDto) throws D4iBootcampException {
        Subscription subscription = new Subscription();
        subscription.setType(subscriptionDto.getType());
        subscription.setPrice(subscriptionDto.getPrice());
        subscription.setStartDate(subscriptionDto.getStartDate());
        subscription.setEndDate(subscriptionDto.getEndDate());
        try {
            subscription = subscriptionRepository.save(subscription);
        } catch (final Exception e) {
            LOGGER.error(ExceptionConstants.INTERNET_SERVER_ERROR, e);
            throw new InternalServerErrorException(ExceptionConstants.INTERNET_SERVER_ERROR);
        }
        return modelMapper.map(subscription, SubscriptionDto.class);
    }

    @Override
    public SubscriptionDto updateSubscription(final Long subscriptionId, final SubscriptionDto subscriptionDto) throws D4iBootcampException {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).get();
        try {
            if(subscriptionDto.getType() != null) subscription.setType(subscriptionDto.getType());
            if(subscriptionDto.getPrice() != null) subscription.setPrice(subscriptionDto.getPrice());
            if(subscriptionDto.getStartDate() != null) subscription.setStartDate(subscriptionDto.getStartDate());
            if(subscriptionDto.getEndDate() != null) subscription.setEndDate(subscriptionDto.getEndDate());

            subscription = subscriptionRepository.save(subscription);
        } catch (final Exception e) {
            LOGGER.error(ExceptionConstants.INTERNET_SERVER_ERROR, e);
            throw new InternalServerErrorException(ExceptionConstants.INTERNET_SERVER_ERROR);
        }
        return modelMapper.map(subscription, SubscriptionDto.class);
    }

}
