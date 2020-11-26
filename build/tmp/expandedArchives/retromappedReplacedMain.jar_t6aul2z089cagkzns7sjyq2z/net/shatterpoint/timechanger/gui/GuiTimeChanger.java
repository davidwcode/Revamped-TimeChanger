package net.shatterpoint.timechanger.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.shatterpoint.timechanger.TimeChanger;

public class GuiTimeChanger extends GuiScreen {
    private TimeChanger mod;
    public GuiTimeChanger(TimeChanger mod) {
        this.mod = mod;
    }

    public void func_73866_w_() {
        super.func_73866_w_();
        this.field_146292_n.add(new GuiSlider(0, this.field_146294_l / 2 - 105, this.field_146295_m / 2 - 75, 210, 20, "Time: ", "", 18000, 41999, this.mod.getGuiTime(), false, true));
    }

    public void func_73863_a(int mouseX, int mouseY, float partialTicks) {
        int x = super.field_146294_l / 2;
        int y = super.field_146295_m / 2;
        super.func_73732_a(super.field_146289_q, "Time Changer", x, y - 100 - super.field_146289_q.field_78288_b / 2, 16777215);
        super.func_73863_a(mouseX, mouseY, partialTicks);
        this.mod.setTime(((GuiSlider)this.field_146292_n.get(0)).getValueInt());
    }




}
