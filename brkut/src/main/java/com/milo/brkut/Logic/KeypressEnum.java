package com.milo.brkut.Logic;

/**
 *
 * @author milo
 */
public enum KeypressEnum {
    LEFT(0), RIGHT(1), SPACE(2);
    private int value;
    private KeypressEnum(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
};
