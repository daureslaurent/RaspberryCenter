package ldaures.designapp.API.model;

/**
 * Created by triba on 29/06/2016.
 */
public class Raspberry {
    public String adr;
    public String name;

    @Override
    public String toString(){
        return (name+":"+adr);
    }
}
