package com.di4.bootcamp.subscriptions.services.imp;

import com.di4.bootcamp.subscriptions.dto.SubscriptionDto;
import com.di4.bootcamp.subscriptions.entities.Subscription;
import com.di4.bootcamp.subscriptions.exceptions.D4iBootcampException;
import com.di4.bootcamp.subscriptions.repositories.SubscriptionRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class SubscriptionServiceImpTest {

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
    SubscriptionServiceImp subscriptionService;

    @Mock
    SubscriptionRepository subscriptionRepository;

    @Test
    public void getAllSubscriptions() throws D4iBootcampException {
        Subscription firstDummySubscription = createFirstDummySubscription(FIRST_SUBSCRIPTION_TYPE, FIRST_SUBSCRIPTION_END);
        Subscription secondDummySubscription = createSecondDummySubscription();
        List<Subscription> dummySubscriptionsList = new ArrayList<>();
        dummySubscriptionsList.add(firstDummySubscription);
        dummySubscriptionsList.add(secondDummySubscription);

        when(subscriptionRepository.findAll()).thenReturn(dummySubscriptionsList);

        List<SubscriptionDto> subscriptionsList = subscriptionService.getAllSubscriptions();

        verify(subscriptionRepository, times(1)).findAll();
        assertNotNull(subscriptionsList);
        assertEquals(2, subscriptionsList.size());
    }

    @Test
    public void createSubscription() throws D4iBootcampException {
        Subscription dummySubscription = createFirstDummySubscription(FIRST_SUBSCRIPTION_TYPE, FIRST_SUBSCRIPTION_END);

        SubscriptionDto dummySubscriptionDto = createDummySubscriptionDto();

        when(subscriptionRepository.save(any())).thenReturn(dummySubscription);

        SubscriptionDto subscription = subscriptionService.createSubscription(dummySubscriptionDto);

        verify(subscriptionRepository, times(1)).save(any());
    }

    @Test
    public void updateSubscription() throws D4iBootcampException {
        final String NEW_TYPE = "STANDARD";
        final LocalDate NEW_END_DATE = LocalDate.of(2024, 12, 31);

        SubscriptionDto dummyGivenSubscription = new SubscriptionDto();
        dummyGivenSubscription.setId(FIRST_SUBSCRIPTION_ID);
        dummyGivenSubscription.setType(NEW_TYPE);
        dummyGivenSubscription.setEndDate(NEW_END_DATE);

        Subscription dummyExistingSubscription = createFirstDummySubscription(FIRST_SUBSCRIPTION_TYPE, FIRST_SUBSCRIPTION_END);

        Subscription dummyUpdatedSubscription = createFirstDummySubscription(NEW_TYPE, NEW_END_DATE);

        ArgumentCaptor<Subscription> subscriptionCaptor = ArgumentCaptor.forClass(Subscription.class);

        when(subscriptionRepository.findById(FIRST_SUBSCRIPTION_ID)).thenReturn(Optional.of(dummyExistingSubscription));
        when(subscriptionRepository.save(subscriptionCaptor.capture())).thenReturn(dummyUpdatedSubscription);

        SubscriptionDto subscription = subscriptionService.updateSubscription(dummyGivenSubscription.getId(), dummyGivenSubscription);

        verify(subscriptionRepository, times(1)).findById(anyLong());
        verify(subscriptionRepository, times(1)).save(any());

        assertEquals(dummyGivenSubscription.getType(), subscriptionCaptor.getValue().getType());
        assertEquals(dummyGivenSubscription.getType(), subscription.getType());
        assertEquals(dummyGivenSubscription.getEndDate(), subscriptionCaptor.getValue().getEndDate());
        assertEquals(dummyGivenSubscription.getEndDate(), subscription.getEndDate());
    }

    private @NotNull Subscription createFirstDummySubscription(String type, LocalDate endDate) {
        Subscription firstDummySubscription = new Subscription();
        firstDummySubscription.setId(FIRST_SUBSCRIPTION_ID);
        firstDummySubscription.setType(type);
        firstDummySubscription.setPrice(FIRST_SUBSCRIPTION_PRICE);
        firstDummySubscription.setStartDate(FIRST_SUBSCRIPTION_START);
        firstDummySubscription.setEndDate(endDate);
        return firstDummySubscription;
    }

    private @NotNull Subscription createSecondDummySubscription() {
        Subscription secondDummySubscription = new Subscription();
        secondDummySubscription.setId(SECOND_SUBSCRIPTION_ID);
        secondDummySubscription.setType(SECOND_SUBSCRIPTION_TYPE);
        secondDummySubscription.setPrice(SECOND_SUBSCRIPTION_PRICE);
        secondDummySubscription.setStartDate(SECOND_SUBSCRIPTION_START);
        secondDummySubscription.setEndDate(SECOND_SUBSCRIPTION_END);
        return secondDummySubscription;
    }

    private @NotNull SubscriptionDto createDummySubscriptionDto() {
        SubscriptionDto dummySubscriptionDto = new SubscriptionDto();
        dummySubscriptionDto.setType(FIRST_SUBSCRIPTION_TYPE);
        dummySubscriptionDto.setPrice(FIRST_SUBSCRIPTION_PRICE);
        dummySubscriptionDto.setStartDate(FIRST_SUBSCRIPTION_START);
        dummySubscriptionDto.setEndDate(FIRST_SUBSCRIPTION_END);
        return dummySubscriptionDto;
    }
}