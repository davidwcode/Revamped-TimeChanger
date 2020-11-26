package net.shatterpoint.timechanger.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.lang3.math.NumberUtils;
import net.shatterpoint.timechanger.TimeChanger;

public class CommandFastTime extends CommandBase {
    private Minecraft mc = Minecraft.func_71410_x();
    TimeChanger mod;

    public CommandFastTime(TimeChanger mod) {
        this.mod = mod;
    }

    public String func_71517_b() {
        return "fasttime";
    }

    public String func_71518_a(ICommandSender sender) {
        return "/fasttime <multiplier>";
    }

    public void func_71515_b(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.func_145747_a((new ChatComponentText("Please use /fasttime <multiplier>!")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.RED)));
        } else {
            double multiplier = NumberUtils.toDouble(args[0], -1.0D);
            if (multiplier < 0.0D) {
                sender.func_145747_a((new ChatComponentText("Invalid multiplier!")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.RED)));
            } else {
                TimeChanger.fastTime = true;
                TimeChanger.isVanilla = false;
                TimeChanger.fastTimeMultiplier = multiplier;
                sender.func_145747_a((new ChatComponentText("Time set to fast (" + multiplier + ").")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GREEN)));
                this.mod.saveSettings();
            }
        }
    }

    public int func_82362_a() {
        return 0;
    }

    public boolean func_71519_b(ICommandSender sender) {
        return true;
    }
}
