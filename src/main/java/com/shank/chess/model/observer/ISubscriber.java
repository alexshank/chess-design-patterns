package com.shank.chess.model.observer;

public interface ISubscriber<T> {
    void update(T event);
}
