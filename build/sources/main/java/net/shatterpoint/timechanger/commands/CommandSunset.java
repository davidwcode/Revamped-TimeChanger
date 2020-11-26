package net.shatterpoint.timechanger.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.shatterpoint.timechanger.TimeChanger;

public class CommandSunset extends CommandBase {
    private Minecraft mc = Minecraft.getMinecraft();
    private TimeChanger mod;

    public CommandSunset(TimeChanger mod) {
        this.mod = mod;
    }

    public String getCommandName() {
        return "sunset";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/sunset";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        TimeChanger.fastTime = false;
        this.mod.setTime(13000);
        TimeChanger.isVanilla = false;
        sender.addChatMessage((new ChatComponentText("Time set to sunset.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
        this.mod.saveSettings();
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }
}
