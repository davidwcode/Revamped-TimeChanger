package net.shatterpoint.timechanger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.network.INetHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
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

@Mod(
        name = "TimeChanger",
        modid = "timechanger",
        version = "2.0",
        acceptedMinecraftVersions = "1.8.9"
)
public class TimeChanger {
    public static boolean fastTime = false;
    public static double fastTimeMultiplier = 1.0D;
    private static int time = 0;
    public static boolean smoothUpdate = false;
    public static boolean isVanilla = true;
    private Minecraft mc = Minecraft.getMinecraft();
    private KeyBinding keyTimeChange = new KeyBinding("Open GUI", 25, "Time Changer");

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ClientRegistry.registerKeyBinding(this.keyTimeChange);
        ClientCommandHandler.instance.registerCommand(new CommandDay(this));
        ClientCommandHandler.instance.registerCommand(new CommandNight(this));
        ClientCommandHandler.instance.registerCommand(new CommandSunset(this));
        ClientCommandHandler.instance.registerCommand(new CommandResetTime(this));
        ClientCommandHandler.instance.registerCommand(new CommandFastTime());
        ClientCommandHandler.instance.registerCommand(new CommandTimeChange(this));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (this.mc.theWorld != null) {
            INetHandler parent = this.mc.thePlayer.sendQueue.getNetworkManager().getNetHandler();
            if (!(parent instanceof TimeChangerNetHandler)) {
                this.mc.thePlayer.sendQueue.getNetworkManager().setNetHandler(new TimeChangerNetHandler((NetHandlerPlayClient)parent));
            }

            if (fastTime) {
                this.mc.theWorld.setWorldTime((long)((double)System.currentTimeMillis() * fastTimeMultiplier % 24000.0D));
            } else if (smoothUpdate) {
                this.mc.theWorld.setWorldTime(time);
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
        this.time = time;
    }

}