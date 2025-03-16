public abstract class Aircraft{
    protected int humanCapacity;
    protected int cargoCapacity;
    protected String model;
    protected String name;
    protected int batteryLife;
    public String getModel(){
        return model;
    }
    public void setModel(String model){
        this.model = model;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBatteryLife(){
        return batteryLife;
    }
    public void setBatteryLife(int batteryLife){
        this.batteryLife = batteryLife;
    }
}
