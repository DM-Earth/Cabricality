package dm.earth.cabricality.content.core.blocks.machine;

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
