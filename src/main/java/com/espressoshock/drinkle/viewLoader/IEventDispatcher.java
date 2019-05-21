package com.espressoshock.drinkle.viewLoader;

public interface IEventDispatcher {

    public void register(IEventObserver eo);
    public void unregister(IEventObserver eo);
    public void dispatchViewChangeRequest(ViewMetadata view);
}
