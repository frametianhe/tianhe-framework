package com.tianhe.framework.disruptor.study.procon;

public class Trade {  
	
    private String id;//交易ID  
    private double price;//交易金额  
      
    public Trade() {  
    }  
    public Trade(String id, double price) {  
        super();  
        this.id = id;  
        this.price = price;  
    }  
    public String getId() {  
        return id;  
    }  
    public void setId(String id) {  
        this.id = id;  
    }  
    public double getPrice() {  
        return price;  
    }  
    public void setPrice(double price) {  
        this.price = price;  
    }  
}  