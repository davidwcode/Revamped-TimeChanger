package net.shatterpoint.timechanger.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.shatterpoint.timechanger.TimeChanger;

public class CommandResetTime extends CommandBase {
    private Minecraft mc = Minecraft.getMinecraft();
    private TimeChanger mod;

    public CommandResetTime(TimeChanger mod) {
        this.mod = mod;
    }

    public String getCommandName() {
        return "resettime";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/resettime";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        TimeChanger.fastTime = false;
        this.mod.setTime(-1);;
        TimeChanger.isVanilla = true;
        sender.addChatMessage((new ChatComponentText("Now using vanilla time.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
