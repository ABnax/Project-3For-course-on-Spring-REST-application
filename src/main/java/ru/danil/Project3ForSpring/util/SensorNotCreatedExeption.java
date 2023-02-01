package ru.danil.Project3ForSpring.util;

public class SensorNotCreatedExeption extends RuntimeException{
    public SensorNotCreatedExeption (String msg) {
        super(msg);
    }
}
