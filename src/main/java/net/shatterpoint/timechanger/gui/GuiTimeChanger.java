package net.shatterpoint.timechanger.gui;

import net.minecraft.client.gui.GuiScreen;
import net.shatterpoint.timechanger.TimeChanger;

import java.io.IOException;

public class GuiTimeChanger extends GuiScreen {
    private TimeChanger mod;
    public GuiTimeChanger(TimeChanger mod) {
        this.mod = mod;
    }

    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiSlider(0, this.width / 2 - 105, this.height / 2 - 75, 210, 20, "Time: ", "", 18000, 41999, this.mod.getGuiTime(), false, true, this.mod));
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        int x = super.width / 2;
        int y = super.height / 2;
        super.drawCenteredString(super.fontRendererObj, "Time Changer", x, y - 100 - super.fontRendererObj.FONT_HEIGHT / 2, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.mod.setTime(((GuiSlider)this.buttonList.get(0)).getValueInt());
    }

    public boolean doesGuiPauseGame()
    {
        return false;
    }

    public void onGuiClosed() {
        this.mod.saveSettings();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        if (keyCode == this.mod.keyTimeChange.getKeyCode()) {
            this.mc.displayGuiScreen(null);

            if (this.mc.currentScreen == null)
            {
                this.mc.setIngameFocus();
            }
        }
    }



}
