* //es necesario hacer el constructor publico para que el dagger pueda inyectar el objeto
class SmartPhone @Inject constructor(val battery: Battery, val simCard: SIMCard, val memoryCard: MemoryCard) {
}
