public abstract class Element {
    protected boolean occupied = false;
    protected boolean works = true;
    protected int waterLevel;
    protected int maxCapacity;
    protected boolean isClicked = false;
    protected Pipe[] connectedPipes;
    protected int maxConnectablePipes;
    protected boolean standable = false;
    public boolean isWorking(){
        System.out.println("isWorking()");
        return works;
    }
    public boolean isOccupied(){
        System.out.println("isOccupied()");
        return occupied;
    }
    public void incrementWater(){
        System.out.println("incrementWater()");
        waterLevel+=2;
    }
    public void decrementWater(){
        System.out.println("decrementWater()");
        waterLevel-=2;
    }
    public void setOccupied(boolean bool){
        System.out.println("setOccupied(boolean)");
        occupied = bool;
    }
    public int getMaxCapacity(){
        System.out.println("getMaxCapacity()");
        return maxCapacity;
    }
    public int getWaterLevel(){
        System.out.println("getWaterLevel()");
        return waterLevel;
    }
    public void Update(){ // How should we implement it?

    }
    public void setStandable(boolean bool){
        System.out.println("setStandable()");
        standable = bool;
    }
}
