package com.anushka.didemo

import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class NCBatteryModule {
    //al ser una clase abstracta debe ser anotada con @Binds sino debe ser anotada con @Provides
    @Binds
    abstract fun bindsNCBattery(nickelCadmiumBattery: NickelCadmiumBattery):Battery
}