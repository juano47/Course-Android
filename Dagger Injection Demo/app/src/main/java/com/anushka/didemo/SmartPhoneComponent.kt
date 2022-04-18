package com.anushka.didemo

import dagger.Component

//esta es la raiz del grafo de inyeccion de dependencias, se anotan con @Component
@Component(modules = [MemoryCardModule::class, NCBatteryModule::class])
interface SmartPhoneComponent {
    //esta es la funcion que se usa para inyectar dependencias
    fun getSmartPhone() : SmartPhone
}