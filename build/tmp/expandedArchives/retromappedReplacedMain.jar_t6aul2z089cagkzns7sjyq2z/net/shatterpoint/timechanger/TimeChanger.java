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
    private Minecraft mc = Minecraft.func_71410_x();
    private KeyBinding keyTimeChange = new KeyBinding("Open GUI", 25, "Time Changer");

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ClientRegistry.registerKeyBinding(this.keyTimeChange);
        ClientCommandHandler.instance.func_71560_a(new CommandDay(this));
        ClientCommandHandler.instance.func_71560_a(new CommandNight(this));
        ClientCommandHandler.instance.func_71560_a(new CommandSunset(this));
        ClientCommandHandler.instance.func_71560_a(new CommandResetTime(this));
        ClientCommandHandler.instance.func_71560_a(new CommandFastTime());
        ClientCommandHandler.instance.func_71560_a(new CommandTimeChange(this));
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        if (this.mc.field_71441_e != null) {
            INetHandler parent = this.mc.field_71439_g.field_71174_a.func_147298_b().func_150729_e();
            if (!(parent instanceof TimeChangerNetHandler)) {
                this.mc.field_71439_g.field_71174_a.func_147298_b().func_150719_a(new TimeChangerNetHandler((NetHandlerPlayClient)parent));
            }

            if (fastTime) {
                this.mc.field_71441_e.func_72877_b((long)((double)System.currentTimeMillis() * fastTimeMultiplier % 24000.0D));
            } else if (smoothUpdate) {
                this.mc.field_71441_e.func_72877_b(time);
            }
        }

    }

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
        int code = this.keyTimeChange.func_151463_i();
        if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == code) {
            fastTime = false;
            mc.func_147108_a(new GuiTimeChanger(this));

        }
    }

    @SubscribeEvent
    public void onMouseInput(MouseInputEvent event) {
        int code = this.keyTimeChange.func_151463_i();
        if (Mouse.getEventButtonState() && Mouse.getEventButton() == code + 100) {
            fastTime = false;
            mc.func_147108_a(new GuiTimeChanger(this));
        }
    }


    public int getGuiTime() {
        if (this.isVanilla) {
            this.time = (int)(mc.field_71441_e.func_72820_D() % 24000);
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