package com.wdeath.tc.item;

public class StackItem {

    public final int id;
    public final int max;
    private int num;

    public StackItem(int id){
        this.id = id;
        num = 0;
        max = Item.items[id].getMaxStack();
    }

    public void add(StackItem item){
        this.num += item.num;
        if(this.num >= max){
            item.num = max - this.num;
            this.num = max;
        }else{
            item.num = 0;
        }
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isEmpty(){
        return num == 0;
    }
}
