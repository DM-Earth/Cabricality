package com.dm.earth.cabricality.content.threads.blocks;

public class CutoffMachineBlock extends AbstractMachineBlock {

    public CutoffMachineBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean isWaterLoggable() {
        return true;
    }

    @Override
    protected boolean isFull() {
        return true;
    }

}
