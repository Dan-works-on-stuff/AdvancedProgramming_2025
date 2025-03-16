public class Freighter extends Aircraft implements CargoCapable  {
    @Override
    public int getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public void setCargoCapacity(int cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }
    public Freighter(String Model, String name, int batteryLife, int cargoCapacity) {
        this.setModel(Model);
        this.setName(name);
        this.setBatteryLife(batteryLife);
        this.setCargoCapacity(cargoCapacity);
    }
}
