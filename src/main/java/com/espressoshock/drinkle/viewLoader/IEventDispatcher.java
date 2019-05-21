package com.espressoshock.drinkle.viewLoader;

public interface IEventDispatcher {

    void register(IEventObserver eo);
    void unregister(IEventObserver eo);
    void dispatchViewChangeRequest(ViewMetadata view);
}
