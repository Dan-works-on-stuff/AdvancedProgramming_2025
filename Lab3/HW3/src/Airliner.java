public class Airliner extends Aircraft implements PassengerCapable{
    @Override
    public int getHumanCapacity() {
        return humanCapacity;
    }

    @Override
    public void setHumanCapacity(int humanCapacity) {
        this.humanCapacity = humanCapacity;
    }

    public Airliner(String Model, String name, int batteryLife, int humanCapacity) {
        this.setModel(Model);
        this.setName(name);
        this.setBatteryLife(batteryLife);
        this.setHumanCapacity(humanCapacity);
    }

}
