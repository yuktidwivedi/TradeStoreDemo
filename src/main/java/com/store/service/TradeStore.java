package com.store.service;
import com.store.exception.TradeValidationException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class TradeStore {
    private Map<String, Trade> tradeMap = new HashMap<>();

    public void addTrade(Trade trade) {
        validateMaturityDate(trade);
        String tradeId = trade.getTradeId();
        if (tradeMap.containsKey(tradeId)) {
            Trade existingTrade = tradeMap.get(tradeId);
            if (trade.getVersion() < existingTrade.getVersion()) {
                throw new TradeValidationException("Lower version trade received.");
            }
        }
        tradeMap.put(tradeId, trade);
    }

    public void validateMaturityDate( Trade trade) {
        LocalDate today = LocalDate.now();
        if (trade.getMaturityDate().isBefore(today)) {
            throw new TradeValidationException("Maturity date cannot be earlier than today.");
        }

    }

    public Trade getTradeById(String tradeId) {
        return tradeMap.get(tradeId);
    }
}
