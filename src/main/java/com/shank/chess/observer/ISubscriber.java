package com.shank.chess.observer;

public interface ISubscriber<T> {
    void update(T event);
}
