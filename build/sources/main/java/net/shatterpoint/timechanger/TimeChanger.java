package net.shatterpoint.timechanger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.MouseInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.shatterpoint.timechanger.commands.*;
import net.shatterpoint.timechanger.gui.GuiTimeChanger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.io.File;

@Mod(
        name = "TimeChanger",
        modid = "timechanger",
        version = "2.1",
        acceptedMinecraftVersions = "1.8.9"
)
public class TimeChanger {
    public static boolean fastTime = false;
    public static double fastTimeMultiplier = 1.0D;
    private static int time = 0;
    public static boolean isVanilla = true;
    private File saveFile;
    private Minecraft mc = Minecraft.getMinecraft();
    public KeyBinding keyTimeChange = new KeyBinding("Open TC GUI", 25, "Time Changer");

    @EventHandler
    public void init(FMLInitializationEvent event) {
        this.saveFile = new File(this.mc.mcDataDir + "/config", "shatterpoint_timechanger.cfg");
        ClientRegistry.registerKeyBinding(this.keyTimeChange);
        this.loadSettings();
        ClientCommandHandler.instance.registerCommand(new CommandDay(this));
        ClientCommandHandler.instance.registerCommand(new CommandNight(this));
        ClientCommandHandler.instance.registerCommand(new CommandSunset(this));
        ClientCommandHandler.instance.registerCommand(new CommandResetTime(this));
        ClientCommandHandler.instance.registerCommand(new CommandFastTime(this));
        ClientCommandHandler.instance.registerCommand(new CommandTimeChange(this));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (this.mc.theWorld != null) {
            if (fastTime) {
                this.setTime((int)((double)System.currentTimeMillis() * fastTimeMultiplier % 24000.0D));
            }
        }

    }

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
        int code = this.keyTimeChange.getKeyCode();
        if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == code) {
            fastTime = false;
            mc.displayGuiScreen(new GuiTimeChanger(this));
        }
    }

    @SubscribeEvent
    public void onMouseInput(MouseInputEvent event) {
        int code = this.keyTimeChange.getKeyCode();
        if (Mouse.getEventButtonState() && Mouse.getEventButton() == code + 100) {
            fastTime = false;
            mc.displayGuiScreen(new GuiTimeChanger(this));
        }
    }


    public int getGuiTime() {
        if (this.isVanilla) {
            this.time = (int)(mc.theWorld.getWorldTime() % 24000);
            isVanilla = false;

        }
        if (this.time < 18000) {
            this.time += 24000;
        }
        return this.time;
    }

    public static int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time % 24000;
    }

    public void saveSettings() {
        Configuration config = new Configuration(this.saveFile);
        this.updateSettings(config, true);
        config.save();
    }

    public void loadSettings() {
        Configuration config = new Configuration(this.saveFile);
        config.load();
        this.updateSettings(config, false);
    }

    private void updateSettings(Configuration config, boolean save) {
        Property prop = config.get("TimeChanger", "time", 0);
        if (save) {
            prop.set(this.time);
        } else {
            this.time = prop.getInt();
        }

        prop = config.get("TimeChanger", "isVanilla", true);
        if (save) {
            prop.set(this.isVanilla);
        } else {
            this.isVanilla = prop.getBoolean();
        }

        prop = config.get("TimeChanger", "fastTime", false);
        if (save) {
            prop.set(this.fastTime);
        } else {
            this.fastTime = prop.getBoolean();
        }

        prop = config.get("TimeChanger", "fastTimeMultiplier", 1.0D);
        if (save) {
            prop.set(this.fastTimeMultiplier);
        } else {
            this.fastTimeMultiplier = prop.getDouble();
        }


    }

}