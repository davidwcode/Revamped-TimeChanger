package net.shatterpoint.timechanger.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.shatterpoint.timechanger.TimeChanger;

public class CommandNight extends CommandBase {
    private Minecraft mc = Minecraft.getMinecraft();
    private TimeChanger mod;
    public CommandNight(TimeChanger mod) {
        this.mod = mod;
    }

    public String getCommandName() {
        return "night";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/night";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        TimeChanger.fastTime = false;
        this.mod.setTime(18000);
        TimeChanger.isVanilla = false;
        sender.addChatMessage((new ChatComponentText("Time set to night.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
