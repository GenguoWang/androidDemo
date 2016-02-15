package com.example.genguo.myapplication.daggerTest;

import javax.inject.Inject;

/**
 * Created by genguo on 2/7/16.
 */
public class WorkStation {
    private Printer printer;
    @Inject public WorkStation(Printer printer){
        this.printer = printer;
    }
    public void work(){
        printer.print();
    }
}
