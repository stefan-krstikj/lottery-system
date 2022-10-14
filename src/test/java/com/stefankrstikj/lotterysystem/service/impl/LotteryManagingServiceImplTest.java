package com.stefankrstikj.lotterysystem.service.impl;

import com.stefankrstikj.lotterysystem.mapper.LotteryBallotMapper;
import com.stefankrstikj.lotterysystem.mapper.LotteryMapper;
import com.stefankrstikj.lotterysystem.model.Lottery;
import com.stefankrstikj.lotterysystem.model.LotteryBallot;
import com.stefankrstikj.lotterysystem.model.response.LotteryBallotResponse;
import com.stefankrstikj.lotterysystem.model.response.LotteryResponse;
import com.stefankrstikj.lotterysystem.service.LotteryBallotService;
import com.stefankrstikj.lotterysystem.service.LotteryManagingService;
import com.stefankrstikj.lotterysystem.service.LotteryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.stefankrstikj.lotterysystem.data.LotteryDummyData.*;
import static com.stefankrstikj.lotterysystem.data.UserDummyData.createDummyUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class LotteryManagingServiceImplTest {
    private LotteryManagingService lotteryManagingService;

    @Mock
    private LotteryService lotteryService;

    @Mock
    private LotteryBallotService lotteryBallotService;

    @Mock
    private LotteryMapper lotteryMapper;

    @Mock
    private LotteryBallotMapper lotteryBallotMapper;

    @BeforeEach
    void setUp() {
        lotteryManagingService = new LotteryManagingServiceImpl(
                lotteryService,
                lotteryBallotService,
                lotteryMapper,
                lotteryBallotMapper
        );
    }

    @AfterEach
    void tearDown() {
        lotteryManagingService = null;
    }

    private void loadDummyLoggedInUser() {
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        when(authentication.getPrincipal()).thenReturn(createDummyUser());
    }

    @Test
    void createLotteryBallotCreatesBallot() {
        loadDummyLoggedInUser();
        when(lotteryService.getOngoingLottery()).thenReturn(createDummyLotteryWithId());
        when(lotteryBallotService.create(any())).thenReturn(Mockito.mock(LotteryBallot.class));
        when(lotteryBallotMapper.entityToResponse(any())).thenReturn(createDummyLotteryBallotResponse());

        LotteryBallotResponse actual = lotteryManagingService.createLotteryBallot();

        assertNotNull(actual);
        assertNotNull(actual.getDate());
        assertNotNull(actual.getLotteryId());
        assertNotNull(actual.getUuid());
    }

    @Test
    void getLotteryBallotByUUIDReturnsBallot() {
        LotteryBallotResponse lotteryBallotResponse = createDummyLotteryBallotResponse();
        when(lotteryBallotService.findByUUID(BALLOT_UUID)).thenReturn(createDummyLotteryBallot());
        when(lotteryBallotMapper.entityToResponse(any())).thenReturn(lotteryBallotResponse);

        LotteryBallotResponse actual = lotteryManagingService.getLotteryBallotByUUID(BALLOT_UUID);

        assertNotNull(actual);
        assertNotNull(actual.getDate());
        assertNotNull(actual.getLotteryId());
        assertNotNull(actual.getUuid());
    }

    @Test
    void getAllBallotsReturnsAllUserBallots() {
        // given
        loadDummyLoggedInUser();
        UUID secondUUID = UUID.randomUUID();

        LotteryBallot ballotFirst = createDummyLotteryBallot();
        LotteryBallot ballotSecond = createDummyLotteryBallot();
        ballotSecond.setUuid(secondUUID);

        LotteryBallotResponse responseFirst = createDummyLotteryBallotResponse();
        LotteryBallotResponse responseSecond = createDummyLotteryBallotResponse();
        responseSecond.setUuid(secondUUID);

        when(lotteryBallotService.findAllByUser(any())).thenReturn(List.of(ballotFirst, ballotSecond));
        when(lotteryBallotMapper.entityToResponse(ballotFirst)).thenReturn(responseFirst);
        when(lotteryBallotMapper.entityToResponse(ballotSecond)).thenReturn(responseSecond);

        // when
        List<LotteryBallotResponse> actual = lotteryManagingService.getAllBallots();

        // then
        assertEquals(2, actual.size());
        assertEquals(responseFirst.getUuid(), actual.get(0).getUuid());
        assertEquals(responseSecond.getUuid(), actual.get(1).getUuid());
    }

    @Test
    void drawWinnerDrawsWinnerFromList() {
        // given
        Lottery ongoingLottery = createDummyLotteryWithId();
        LotteryBallot ballot1 = createDummyLotteryBallot();
        ballot1.setUuid(UUID.randomUUID());

        LotteryBallot ballot2 = createDummyLotteryBallot();
        ballot2.setUuid(UUID.randomUUID());

        ongoingLottery.setBallots(Set.of(
                ballot1,
                ballot2));
        when(lotteryService.getOngoingLottery()).thenReturn(ongoingLottery);
        when(lotteryService.save(any())).thenReturn(ongoingLottery);

        // when
        Optional<LotteryBallot> actual = lotteryManagingService.drawWinner();

        // then
        assertTrue(actual.isPresent());
        assertNotNull(actual.get().getUuid());
    }

    @Test
    void drawWinnerDrawsNullFromEmptyList() {
        Lottery ongoingLottery = createDummyLotteryWithId();
        when(lotteryService.getOngoingLottery()).thenReturn(ongoingLottery);
        when(lotteryService.save(any())).thenReturn(ongoingLottery);

        Optional<LotteryBallot> actual = lotteryManagingService.drawWinner();

        assertTrue(actual.isEmpty());
    }

    @Test
    void startNewLotteryStartsLottery() {
        when(lotteryService.isLotteryActive()).thenReturn(false);
        when(lotteryService.save(any())).thenReturn(createDummyLotteryWithId());
        when(lotteryMapper.entityToResponse(any())).thenReturn(createDummyLotteryResponse());

        LotteryResponse actual = lotteryManagingService.startNewLottery();

        assertNotNull(actual);
        assertNotNull(actual.getId());
        assertNotNull(actual.getDate());
    }

    @Test
    void startNewLotteryFailsForOngoingActiveLottery() {
        when(lotteryService.isLotteryActive()).thenReturn(true);

        LotteryResponse actual = lotteryManagingService.startNewLottery();

        assertNull(actual);
    }

    @Test
    void getLotteryForDateReturnsLottery() {
        when(lotteryService.getLotteryForDate(DATE)).thenReturn(createDummyLotteryWithId());
        when(lotteryMapper.entityToResponse(any())).thenReturn(createDummyLotteryResponse());

        LotteryResponse actual = lotteryManagingService.getLotteryForDate(DATE);

        assertNotNull(actual);
        assertNotNull(actual.getDate());
        assertNotNull(actual.getId());
    }

    @Test
    void getOngoingLotteryReturnsLottery() {
        when(lotteryService.getOngoingLottery()).thenReturn(createDummyLotteryWithId());
        when(lotteryMapper.entityToResponse(any())).thenReturn(createDummyLotteryResponse());

        LotteryResponse actual = lotteryManagingService.getLotteryForDate(DATE);

        assertNotNull(actual);
        assertNotNull(actual.getDate());
        assertNotNull(actual.getId());
    }
}
