package web;


import java.io.Serializable;

public class Request implements Serializable {
    String type;
    Object data;

    public Request(String type, Object data){
        this.type = type;
        this.data = data;
    }

    public Request(String type){
        this.type = type;
        data = null;
    }


    public String getType() {
        return type;
    }

    public Object getData() {
        return data;
    }
}
