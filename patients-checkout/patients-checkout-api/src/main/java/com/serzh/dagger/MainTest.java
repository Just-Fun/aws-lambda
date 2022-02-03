package com.serzh.dagger;

public class MainTest {

    public static void main(String[] args) {
        VehiclesComponent component = DaggerVehiclesComponent.create();
        Car car = component.buildCar();
        Engine engine = car.getEngine();
        String engineName = engine.getName();
        System.out.println(engineName);
    }
}
