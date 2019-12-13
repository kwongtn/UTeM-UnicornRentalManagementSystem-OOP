package model;
abstract class Model{
    private int uniqueID;

    protected Model(int uniqueID){
        this.uniqueID = uniqueID;
    }

    protected int getUniqueID() {
        return uniqueID;
    }

}