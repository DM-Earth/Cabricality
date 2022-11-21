package com.dm.earth.cabricality.content.threads.blocks;

public class ComplexMachineBlock extends AbstractMachineBlock {

    public ComplexMachineBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean isWaterLoggable() {
        return true;
    }

    @Override
    protected boolean isFull() {
        return false;
    }
    
}
