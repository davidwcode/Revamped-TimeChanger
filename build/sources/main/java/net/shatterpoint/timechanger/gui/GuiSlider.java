package net.shatterpoint.timechanger.gui;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import org.lwjgl.opengl.GL11;
import net.shatterpoint.timechanger.TimeChanger;

public class GuiSlider extends GuiButtonExt {
    public double sliderValue;
    public String dispString;
    public boolean dragging;
    public boolean showDecimal;
    public double minValue;
    public double maxValue;
    public int precision;
    public GuiSlider_ISlider parent;
    public String suffix;
    public boolean drawString;
    private TimeChanger mod;


    public GuiSlider(int id, int xPos, int yPos, int width, int height, String prefix, String suf, double minVal, double maxVal, double currentVal, boolean showDec, boolean drawStr, TimeChanger mod) {
        this(id, xPos, yPos, width, height, prefix, suf, minVal, maxVal, currentVal, showDec, drawStr, mod, null);
    }

    public GuiSlider(int id, int xPos, int yPos, int width, int height, String prefix, String suf, double minVal, double maxVal, double currentVal, boolean showDec, boolean drawStr, TimeChanger mod, GuiSlider_ISlider par) {
        super(id, xPos, yPos, width, height, prefix);
        this.sliderValue = 1.0D;
        this.dispString = "";
        this.dragging = false;
        this.showDecimal = true;
        this.minValue = 0.0D;
        this.maxValue = 5.0D;
        this.precision = 1;
        this.parent = null;
        this.suffix = "";
        this.drawString = true;
        this.minValue = minVal;
        this.maxValue = maxVal;
        this.sliderValue = (currentVal - this.minValue) / (this.maxValue - this.minValue);
        this.dispString = prefix;
        this.parent = par;
        this.suffix = suf;
        this.showDecimal = showDec;
        this.mod = mod;
        String val = getStringTime((int)Math.round(this.sliderValue * (this.maxValue - this.minValue) + this.minValue));
        this.displayString = this.dispString + val + this.suffix;
        this.drawString = drawStr;
        if (!this.drawString) {
            this.displayString = "";
        }

    }

    public int getHoverState(boolean par1) {
        return 0;
    }

    protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {
        if (this.visible) {
            if (this.dragging) {
                this.sliderValue = (double)((float)(par2 - (this.xPosition + 4)) / (float)(this.width - 8));
                this.updateSlider();
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (double)((float)(this.width - 8))), this.yPosition, 0, 66, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (double)((float)(this.width - 8))) + 4, this.yPosition, 196, 66, 4, 20);
        }

    }

    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
        if (super.mousePressed(par1Minecraft, par2, par3)) {
            this.sliderValue = (double)((float)(par2 - (this.xPosition + 4)) / (float)(this.width - 8));
            this.updateSlider();
            this.dragging = true;
            return true;
        } else {
            return false;
        }
    }

    public void updateSlider() {
        if (this.sliderValue < 0.0D) {
            this.sliderValue = 0.0D;
        }

        if (this.sliderValue > 1.0D) {
            this.sliderValue = 1.0D;
        }
        this.mod.setTime(getValueInt());
        String val = getStringTime(getValueInt());
        if (this.drawString) {
            this.displayString = this.dispString + val + this.suffix;
        }

        if (this.parent != null) {
            this.parent.onChangeSliderValue(this);
        }

    }

    public void mouseReleased(int par1, int par2) {
        this.dragging = false;
    }

    public int getValueInt() {
        return (int)Math.round(this.sliderValue * (this.maxValue - this.minValue) + this.minValue);
    }

    public String getStringTime(int time) {
        String tag;
        int hours = (time + 6000)/1000 - 24;
        int minutes = (int)((double)(time % 1000) / 1000 * 60);
        if (hours < 12) {
            tag = "AM";
        } else {
            hours = hours % 12;
            tag = "PM";
        }

        if (hours == 0) {
            hours = 12;
        }
        if (minutes < 10) {
            return hours + ":0" + minutes + " " + tag;
        }
        return hours + ":" + minutes + " " + tag;
    }
}
