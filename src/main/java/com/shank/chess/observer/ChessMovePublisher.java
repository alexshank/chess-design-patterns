package com.shank.chess.observer;

import com.shank.chess.strategy.MoveCalculator;

import java.util.ArrayList;
import java.util.List;

public class ChessMovePublisher implements IPublisher<MoveCalculator> {
    private List<ISubscriber<MoveCalculator>> subscriberList = new ArrayList<>();

    @Override
    public void subscribe(ISubscriber<MoveCalculator> subscriber) {
        this.subscriberList.add(subscriber);
    }

    @Override
    public void unsubscribe(ISubscriber<MoveCalculator> subscriber) {
        this.subscriberList.remove(subscriber);
    }

    @Override
    public void notifySubscribers(MoveCalculator moveCalculator) {
        subscriberList.forEach(s -> s.update(moveCalculator));
    }
}
