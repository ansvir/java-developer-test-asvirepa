package com.example.asteroid.util;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import java.util.Optional;

/**
 * Generic purpose service utility class that basically used to operate over actors on stage
 */
public final class ServiceUtil {

    /**
     * Method that uses linear search to find an {@link Actor} of type {@link T} on {@param Stage}
     * @param stage {@link Stage} where to find an {@link Actor}
     * @param clazz Wrapper of {@link T} type of and actor
     * @return {@link Optional<T>} of {@link T} or {@link Optional<T>.empty())} if no actor of type {@link T} found on {@param Stage}
     * @param <T> type of {@link Actor} on {@param Stage}
     */
    public static <T extends Actor> Optional<T> findActorOnStage(Stage stage, Class<T> clazz) {
        for (Actor a : stage.getActors()) {
            if (a.getClass().isAssignableFrom(clazz)) {
                return Optional.of(clazz.cast(a));
            }
        }
        return Optional.empty();
    }

    /**
     * Method that uses linear search to create an empty array of {@link Actor}'s of type {@link T}
     * and uses linear search to find them on {@param Stage} and then populates created array
     * @param stage {@link Stage} where to find an {@link Actor}
     * @param clazz Wrapper of {@link T} type of and actor
     * @return {@link Array<T>} {@link Actor}'s of {@link T} found on {@param Stage}
     * @param <T> type of {@link Actor} on {@param Stage}
     */
    public static <T extends Actor> Array<T> findAllActorsOnStage(Stage stage, Class<T> clazz) {
        Array<T> result = new Array<>();
        for (Actor a : stage.getActors()) {
            if (a.getClass().isAssignableFrom(clazz)) {
                result.add(clazz.cast(a));
            }
        }
        return result;
    }

}
