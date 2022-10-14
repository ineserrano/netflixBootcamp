package com.di4.bootcamp.subscriptions.controllers;

import com.di4.bootcamp.subscriptions.dto.SubscriptionDto;
import com.di4.bootcamp.subscriptions.exceptions.D4iBootcampException;
import com.di4.bootcamp.subscriptions.responses.D4iBootcampResponse;
import com.di4.bootcamp.subscriptions.utils.constants.RestConstants;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SubscriptionController {

    public D4iBootcampResponse<List<SubscriptionDto>> getAllSubscriptions() throws D4iBootcampException;
    public D4iBootcampResponse<SubscriptionDto> createSubscription(SubscriptionDto subscriptionDto) throws D4iBootcampException;
    public D4iBootcampResponse<SubscriptionDto> updateSubscription(Long subscriptionId, SubscriptionDto subscriptionDto) throws D4iBootcampException;
    }
