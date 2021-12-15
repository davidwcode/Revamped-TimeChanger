package net.shatterpoint.timechanger.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.shatterpoint.timechanger.TimeChanger;

public class CommandDay extends CommandBase {
    private TimeChanger mod;
    private Minecraft mc = Minecraft.getMinecraft();
    public CommandDay(TimeChanger mod) {
        this.mod = mod;
    }


    public String getCommandName() {
        return "day";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/day";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        TimeChanger.fastTime = false;
        this.mod.setTime(6000);
        TimeChanger.isVanilla = false;
        TimeChanger.isIRLTime = false;
        sender.addChatMessage((new ChatComponentText("Time set to day.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
        this.mod.saveSettings();
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
