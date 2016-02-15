package com.example.genguo.myapplication.daggerTest;

import dagger.Module;
import dagger.Provides;

/**
 * Created by genguo on 2/7/16.
 */
@Module
public class PrinterModule {
    @Provides
    Printer getPrinter(){return new KingoPrinter();}
}
