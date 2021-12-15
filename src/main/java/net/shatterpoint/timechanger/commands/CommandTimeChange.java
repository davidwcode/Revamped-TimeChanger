package net.shatterpoint.timechanger.commands;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.shatterpoint.timechanger.TimeChanger;
import net.shatterpoint.timechanger.gui.GuiTimeChanger;

import java.util.ArrayList;
import java.util.List;

public class CommandTimeChange extends CommandBase {
    private final Minecraft mc = Minecraft.getMinecraft();
    private final TimeChanger mod;

    public CommandTimeChange(TimeChanger mod) {
        this.mod = mod;
    }

    public String getCommandName() {
        return "timechange";
    }

    public List<String> getCommandAliases() {
        List<String> aliases = new ArrayList<>();
        aliases.add("tc");
        return aliases;
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/timechange <TIME>";
    }

    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            switch (args[0].toLowerCase()) {
                case "day":
                    this.mod.setTime(6000);
                    TimeChanger.fastTime = false;
                    TimeChanger.isVanilla = false;
                    TimeChanger.isIRLTime = false;
                    sender.addChatMessage((new ChatComponentText("Time set to day.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
                    this.mod.saveSettings();
                    break;
                case "sunset":
                    this.mod.setTime(13000);
                    TimeChanger.fastTime = false;
                    TimeChanger.isVanilla = false;
                    TimeChanger.isIRLTime = false;
                    sender.addChatMessage((new ChatComponentText("Time set to sunset.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
                    this.mod.saveSettings();
                    break;
                case "night":
                    this.mod.setTime(18000);
                    TimeChanger.fastTime = false;
                    TimeChanger.isVanilla = false;
                    TimeChanger.isIRLTime = false;
                    sender.addChatMessage((new ChatComponentText("Time set to night.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
                    this.mod.saveSettings();
                    break;
                case "vanilla":
                    this.mod.setTime(-1);
                    TimeChanger.fastTime = false;
                    TimeChanger.isVanilla = true;
                    TimeChanger.isIRLTime = false;
                    sender.addChatMessage((new ChatComponentText("Now using vanilla time.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
                    this.mod.saveSettings();
                    break;
                case "irl":
                    this.mod.setTime(-1);
                    TimeChanger.fastTime = false;
                    TimeChanger.isVanilla = false;
                    TimeChanger.isIRLTime = true;
                    sender.addChatMessage((new ChatComponentText("Now using irl time.")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
                    this.mod.saveSettings();
                    break;
                default:
                    try {
                        if (Integer.parseInt(args[0]) >= 0 && Integer.parseInt(args[0]) <= 24000) {
                            mod.setTime(Integer.parseInt(args[0]));
                            TimeChanger.fastTime = false;
                            TimeChanger.isVanilla = false;
                            TimeChanger.isIRLTime = false;
                            sender.addChatMessage((new ChatComponentText("Time set to " + args[0] + ".")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.GREEN)));
                            this.mod.saveSettings();
                        } else {
                            sender.addChatMessage((new ChatComponentText("Time must be between 0 - 24000")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.RED)));
                        }
                    } catch (Exception e) {
                        sender.addChatMessage((new ChatComponentText("Usage: /timechange day/sunset/night/<TIME>")).setChatStyle((new ChatStyle()).setColor(EnumChatFormatting.RED)));
                    }
                    break;
            }
        } else {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        MinecraftForge.EVENT_BUS.unregister(this);
        this.mc.displayGuiScreen(new GuiTimeChanger(this.mod));
    }
}
