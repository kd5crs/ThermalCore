package cofh.thermal.core.client.gui.device;

import cofh.core.client.gui.element.ElementScaled;
import cofh.core.util.helpers.GuiHelper;
import cofh.thermal.core.client.gui.ThermalGuiHelper;
import cofh.thermal.core.inventory.container.device.DeviceSoilInfuserContainer;
import cofh.thermal.lib.client.gui.AugmentableScreen;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import static cofh.core.util.helpers.GuiHelper.*;
import static cofh.lib.util.constants.ModIds.ID_THERMAL;
import static cofh.lib.util.helpers.StringHelper.format;
import static cofh.lib.util.helpers.StringHelper.localize;

public class DeviceSoilInfuserScreen extends AugmentableScreen<DeviceSoilInfuserContainer> {

    public static final String TEX_PATH = ID_THERMAL + ":textures/gui/container/soil_infuser.png";
    public static final ResourceLocation TEXTURE = new ResourceLocation(TEX_PATH);

    public DeviceSoilInfuserScreen(DeviceSoilInfuserContainer container, Inventory inv, Component titleIn) {

        super(container, inv, container.tile, titleIn);
        texture = TEXTURE;
        info = generatePanelInfo("info.thermal.device_soil_infuser");
    }

    @Override
    public void init() {

        super.init();

        if (tile.getEnergyStorage() != null && tile.getEnergyStorage().getMaxEnergyStored() > 0) {
            addPanel(ThermalGuiHelper.createDefaultEnergyUserPanel(this, tile));
            addElement(GuiHelper.setClearable(createDefaultEnergyStorage(this, 8, 8, tile.getEnergyStorage()), tile, 0));
        }
        addElement(new ElementScaled(this, 44, 34)
                .setQuantity(() -> tile.getScaledProgress(16))
                .setSize(16, 16)
                .setTexture(SCALE_FLAME_GREEN, 32, 16));
    }

    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {

        String radius = format(1 + 2L * menu.tile.getRadius());

        fontRenderer().draw(matrixStack, localize("info.cofh.area") + ": " + radius + " x " + radius, 70, 39, 0x404040);

        super.renderLabels(matrixStack, mouseX, mouseY);
    }

}
