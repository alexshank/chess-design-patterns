package com.shank.chess.observer;

// PATTERN this is the observer pattern's subscriber
public interface ISubscriber<T> {
    void update(T event);
}
