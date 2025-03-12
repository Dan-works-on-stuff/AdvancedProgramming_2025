public class Main {
    public static void main(String[] args) {
        Aircraft A1 = new Airliner("A1","vrabie",50,30);
        Aircraft A2 = new Airliner("A2","randunica",50,40);
        Drone D1 = new Drone("D1","cioara",10,4);
        Freighter F1 = new Freighter("F1","bufnita",50,80);
        CargoCapable[] Planes ={F1, D1};
    }
}