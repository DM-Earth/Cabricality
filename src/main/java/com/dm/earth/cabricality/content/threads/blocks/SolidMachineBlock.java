package com.dm.earth.cabricality.content.threads.blocks;

public class SolidMachineBlock extends AbstractMachineBlock {

    public SolidMachineBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean isWaterLoggable() {
        return false;
    }

    @Override
    protected boolean isFull() {
        return true;
    }

}
