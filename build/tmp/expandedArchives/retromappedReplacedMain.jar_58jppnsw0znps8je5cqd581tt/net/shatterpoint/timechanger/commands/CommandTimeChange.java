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
    private Minecraft mc = Minecraft.func_71410_x();
    private TimeChanger mod;
    public CommandTimeChange(TimeChanger mod) {
        this.mod = mod;
    }

    public String func_71517_b() {
        return "timechange";
    }

    public List func_71514_a() {
        List aliases = new ArrayList();
        aliases.add("tc");
        return aliases;
    }

    public String func_71518_a(ICommandSender sender) {
        return "/timechange <TIME>";
    }

    public void func_71515_b(ICommandSender sender, String[] args) {
        if (args.length > 0) {
            if (args[0].toLowerCase().equals("day")) {
                this.mod.setTime(6000);
                TimeChanger.fastTime = false;
                TimeChanger.isVanilla = false;
                sender.func_145747_a((new ChatComponentText("Time set to day.")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GREEN)));
                this.mod.saveSettings();
            } else if (args[0].toLowerCase().equals("sunset")) {
                this.mod.setTime(13000);
                TimeChanger.fastTime = false;
                TimeChanger.isVanilla = false;
                sender.func_145747_a((new ChatComponentText("Time set to sunset.")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GREEN)));
                this.mod.saveSettings();
            } else if (args[0].toLowerCase().equals("night")) {
                this.mod.setTime(18000);
                TimeChanger.fastTime = false;
                TimeChanger.isVanilla = false;
                sender.func_145747_a((new ChatComponentText("Time set to night.")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GREEN)));
                this.mod.saveSettings();
            } else if (args[0].toLowerCase().equals("vanilla")) {
                this.mod.setTime(-1);
                TimeChanger.fastTime = false;
                TimeChanger.isVanilla = true;
                sender.func_145747_a((new ChatComponentText("Now using vanilla time.")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GREEN)));
                this.mod.saveSettings();
            } else {
                try {
                    if (Integer.parseInt(args[0]) >= 0 && Integer.parseInt(args[0]) <= 24000) {
                        mod.setTime(Integer.parseInt(args[0]));
                        TimeChanger.fastTime = false;
                        TimeChanger.isVanilla = false;
                        sender.func_145747_a((new ChatComponentText("Time set to " + args[0] + ".")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.GREEN)));
                        this.mod.saveSettings();
                    } else {
                        sender.func_145747_a((new ChatComponentText("Time must be between 0 - 24000")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.RED)));
                    }
                } catch (Exception e) {
                    sender.func_145747_a((new ChatComponentText("Usage: /timechange day/sunset/night/<TIME>")).func_150255_a((new ChatStyle()).func_150238_a(EnumChatFormatting.RED)));
                }
            }
        } else {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    public int func_82362_a() {
        return 0;
    }

    public boolean func_71519_b(ICommandSender sender) {
        return true;
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        MinecraftForge.EVENT_BUS.unregister(this);
        this.mc.func_147108_a(new GuiTimeChanger(this.mod));
    }
}
