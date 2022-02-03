package com.serzh.dagger;

import dagger.Component;

import javax.inject.Singleton;

//a component, which is an interface used to generate the injector
@Singleton
@Component(modules = VehiclesModule.class)
public interface VehiclesComponent {

    Car buildCar();

}
