package com.shank.chess.model.observer;

public interface IPublisher<T> {
    void subscribe(ISubscriber<T> subscriber);
    void unsubscribe(ISubscriber<T> subscriber);
    void notifySubscribers(T event);
}
