package net.shatterpoint.timechanger.mixin;

import net.minecraft.world.storage.WorldInfo;
import net.shatterpoint.timechanger.TimeChanger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(WorldInfo.class)
public abstract class MixinWorldInfo {

    @Shadow
    private long worldTime;

    /**
     * @author - Shatterpoint
     * @reason - to return custom time
     */
    @Overwrite
    public long getWorldTime() {
        if (TimeChanger.isVanilla) {
            return this.worldTime;
        }
        return TimeChanger.getTime();
    }

}
