package com.dm.earth.cabricality.content.core.blocks;

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
