package net.shatterpoint.timechanger.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.shatterpoint.timechanger.TimeChanger;

public class CommandNight extends CommandBase {
    private Minecraft mc = Minecraft.func_71410_x();
    private TimeChanger mod;
    public CommandNight(TimeChanger mod) {
        this.mod = mod;
    }

    public String func_71517_b() {
        return "night";
    }

    public String func_71518_a(ICommandSender sender) {
        return "/night";
    }

    public void func_71515_b(ICommandSender sender, String[] args) {
        TimeChanger.fastTime = false;
        this.mod.setTime(18000);
        TimeChanger.isVanilla = false;
        sender.func_145747_a((new ChatComponentText("Time set to night.")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GREEN)));
    }

    public int func_82362_a() {
        return 0;
    }

    public boolean func_71519_b(ICommandSender sender) {
        return true;
    }
}
