package fa.dfa;


import fa.State;

public class DFAState extends State {
    
    public boolean isStart, isFinal;

    public String getName() {
        return super.getName();
    }
    
    public void setName(String name) {
        super.name = name;
    }

    public void setStart(boolean bool) {
        this.isStart = bool;
    }

    public boolean getStart() {
        return isStart;
    }

    public void setFinal(boolean bool) {
        this.isFinal = bool;
    }

    public boolean getFinal() {
        return isFinal;
    }

    public String toString() {
        return super.toString();
    }

}