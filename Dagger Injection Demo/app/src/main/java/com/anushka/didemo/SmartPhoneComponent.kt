package com.anushka.didemo

import dagger.Component
import javax.inject.Singleton

@Singleton
//esta es la raiz del grafo de inyeccion de dependencias, se anotan con @Component
@Component(modules = [MemoryCardModule::class, NCBatteryModule::class])
interface SmartPhoneComponent {
    //esta es la funcion que se usa para inyectar dependencias
    //fun getSmartPhone() : SmartPhone

    //mejor forma de hacerlo, el nombre de la funcion es arbitrario
    fun inject(mainActivity: MainActivity)
    //hacemos un inject por cada activity o fragment donde se usa el componente
}