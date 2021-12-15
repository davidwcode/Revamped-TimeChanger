package net.shatterpoint.timechanger.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.shatterpoint.timechanger.TimeChanger;

public class CommandIrlTime extends CommandBase {
    private final TimeChanger mod;

    public CommandIrlTime(TimeChanger mod) {
        this.mod = mod;
    }

    @Override
    public String getCommandName() {
        return "irltime";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "irltime";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        this.mod.setTime(-1);
        TimeChanger.fastTime = false;
        TimeChanger.isVanilla = false;
        TimeChanger.isIRLTime = true;
        sender.addChatMessage((new ChatComponentText("Now using irl time.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
        this.mod.saveSettings();
    }
}
