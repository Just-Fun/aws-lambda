package com.serzh.dagger;

import dagger.Provides;
import dagger.Module;

import javax.inject.Singleton;

//Dagger's @Module annotation makes the container aware of a class
// in a very similar fashion as any of Spring's stereotype annotations (for example, @Service, @Controller…).
//Likewise, @Provides and @Component are almost equivalent to Spring's @Bean and @Lookup respectively.

//a module, which is a class that provides or builds the objects' dependencies
@Module
public class VehiclesModule {

    @Provides
    public Engine provideEngine() {
        return new Engine();
    }

    @Provides
    @Singleton
    public Brand provideBrand() {
        return new Brand("Baeldung");
    }

/*    @Provides
    public Car provideCar(Engine engine, Brand brand) {
        return new Car(engine, brand);
    }*/
}
