public class Cistern extends Element {
    public void manufacturePipe(){
        System.out.println("manufacturePipe()");
        System.out.println("The Cistern has manufactured a pipe."); // Was this called by Game.AddPipe()?
    }
    public void manufacturePump(){
        System.out.println("manufacturePump()");
        System.out.println("The Cistern has manufactured a pump."); // Was this called by Game.AddPump()?
    }
}
