package by.epam.logistics.state;

import by.epam.logistics.entity.Van;

public interface BaseState {
    void activateState(Van van);
}