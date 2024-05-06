package com.store.test;

import com.store.exception.TradeValidationException;
import com.store.service.Trade;
import com.store.service.TradeStore;

import java.time.LocalDate;

import com.store.service.TradeStore;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class TradeStoreTest {



    @Test
    public void givenLowerVersionTrade_whenAddTrade_thenExceptionThrown() {
        TradeStore tradeStore = new TradeStore();
        Trade existingTrade = new Trade("T1", 1, "IBM", LocalDate.now().plusDays(1));
        tradeStore.addTrade(existingTrade);

        Trade lowerVersionTrade = new Trade("T1", 0, "IBM", LocalDate.now().plusDays(2));
        assertThrows(TradeValidationException.class, () -> tradeStore.addTrade(lowerVersionTrade));
    }

    @Test
    public void givenSameVersionTrade_whenAddTrade_thenExistingRecordOverridden() {
        TradeStore tradeStore = new TradeStore();
        Trade existingTrade = new Trade("T1", 1, "IBM", LocalDate.now().plusDays(1));
        tradeStore.addTrade(existingTrade);

        Trade sameVersionTrade = new Trade("T1", 1, "IBM", LocalDate.now().plusDays(2));
        tradeStore.addTrade(sameVersionTrade);

        assertEquals(sameVersionTrade, tradeStore.getTradeById("T1"));
    }

    @Test
    public void givenHigherVersionTrade_whenAddTrade_thenExistingRecordOverridden() {
        TradeStore tradeStore = new TradeStore();
        Trade existingTrade = new Trade("T1", 1, "IBM", LocalDate.now().plusDays(1));
        tradeStore.addTrade(existingTrade);

        Trade higherVersionTrade = new Trade("T1", 2, "IBM", LocalDate.now().plusDays(2));
        tradeStore.addTrade(higherVersionTrade);

        assertEquals(higherVersionTrade, tradeStore.getTradeById("T1"));
    }

    @Test
    public void givenTradeWithPastMaturityDate_whenAddTrade_thenExceptionThrown() {
        TradeStore tradeStore = new TradeStore();
        Trade tradeWithPastMaturityDate = new Trade("T1", 1, "IBM", LocalDate.now().minusDays(1));

        assertThrows(TradeValidationException.class, () -> tradeStore.addTrade(tradeWithPastMaturityDate));
    }

    @Test
    public void givenNonExistingTradeId_whenGetTradeById_thenNullReturned() {
        TradeStore tradeStore = new TradeStore();
        assertNull(tradeStore.getTradeById("T1"));
    }

    @Test
    public void givenExistingTradeId_whenGetTradeById_thenCorrectTradeReturned() {
        TradeStore tradeStore = new TradeStore();
        Trade existingTrade = new Trade("T1", 1, "IBM", LocalDate.now().plusDays(1));
        tradeStore.addTrade(existingTrade);

        assertEquals(existingTrade, tradeStore.getTradeById("T1"));
    }
}
