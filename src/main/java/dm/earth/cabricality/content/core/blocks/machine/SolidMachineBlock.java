package dm.earth.cabricality.content.core.blocks.machine;

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
