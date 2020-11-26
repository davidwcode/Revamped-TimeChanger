package net.shatterpoint.timechanger.gui;

import net.minecraft.client.gui.GuiScreen;
import net.shatterpoint.timechanger.TimeChanger;

public class GuiTimeChanger extends GuiScreen {
    private TimeChanger mod;
    public GuiTimeChanger(TimeChanger mod) {
        this.mod = mod;
    }

    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiSlider(0, this.width / 2 - 105, this.height / 2 - 75, 210, 20, "Time: ", "", 18000, 41999, this.mod.getGuiTime(), false, true));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int x = super.width / 2;
        int y = super.height / 2;
        super.drawCenteredString(super.fontRendererObj, "Time Changer", x, y - 100 - super.fontRendererObj.FONT_HEIGHT / 2, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.mod.setTime(((GuiSlider)this.buttonList.get(0)).getValueInt());
    }




}
