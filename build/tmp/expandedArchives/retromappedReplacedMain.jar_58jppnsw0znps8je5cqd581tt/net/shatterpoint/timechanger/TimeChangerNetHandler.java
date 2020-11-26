package net.shatterpoint.timechanger;

import java.lang.reflect.Field;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S00PacketKeepAlive;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.network.play.server.S03PacketTimeUpdate;
import net.minecraft.network.play.server.S04PacketEntityEquipment;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.network.play.server.S06PacketUpdateHealth;
import net.minecraft.network.play.server.S07PacketRespawn;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.network.play.server.S09PacketHeldItemChange;
import net.minecraft.network.play.server.S0APacketUseBed;
import net.minecraft.network.play.server.S0BPacketAnimation;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import net.minecraft.network.play.server.S0EPacketSpawnObject;
import net.minecraft.network.play.server.S0FPacketSpawnMob;
import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.network.play.server.S12PacketEntityVelocity;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.network.play.server.S14PacketEntity;
import net.minecraft.network.play.server.S18PacketEntityTeleport;
import net.minecraft.network.play.server.S19PacketEntityHeadLook;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.network.play.server.S1CPacketEntityMetadata;
import net.minecraft.network.play.server.S1DPacketEntityEffect;
import net.minecraft.network.play.server.S1EPacketRemoveEntityEffect;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.network.play.server.S20PacketEntityProperties;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.network.play.server.S22PacketMultiBlockChange;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.network.play.server.S24PacketBlockAction;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.network.play.server.S27PacketExplosion;
import net.minecraft.network.play.server.S28PacketEffect;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.network.play.server.S2APacketParticles;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.network.play.server.S2CPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.minecraft.network.play.server.S2EPacketCloseWindow;
import net.minecraft.network.play.server.S2FPacketSetSlot;
import net.minecraft.network.play.server.S30PacketWindowItems;
import net.minecraft.network.play.server.S31PacketWindowProperty;
import net.minecraft.network.play.server.S32PacketConfirmTransaction;
import net.minecraft.network.play.server.S33PacketUpdateSign;
import net.minecraft.network.play.server.S34PacketMaps;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.network.play.server.S36PacketSignEditorOpen;
import net.minecraft.network.play.server.S37PacketStatistics;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.network.play.server.S39PacketPlayerAbilities;
import net.minecraft.network.play.server.S3APacketTabComplete;
import net.minecraft.network.play.server.S3BPacketScoreboardObjective;
import net.minecraft.network.play.server.S3CPacketUpdateScore;
import net.minecraft.network.play.server.S3DPacketDisplayScoreboard;
import net.minecraft.network.play.server.S3EPacketTeams;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import net.minecraft.network.play.server.S40PacketDisconnect;
import net.minecraft.network.play.server.S41PacketServerDifficulty;
import net.minecraft.network.play.server.S42PacketCombatEvent;
import net.minecraft.network.play.server.S43PacketCamera;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import net.minecraft.network.play.server.S45PacketTitle;
import net.minecraft.network.play.server.S46PacketSetCompressionLevel;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import net.minecraft.network.play.server.S48PacketResourcePackSend;
import net.minecraft.network.play.server.S49PacketUpdateEntityNBT;
import net.minecraft.util.IChatComponent;

public class TimeChangerNetHandler extends NetHandlerPlayClient {
    private NetHandlerPlayClient parent;

    public TimeChangerNetHandler(NetHandlerPlayClient parent) {
        super(Minecraft.func_71410_x(), getGuiScreen(parent), parent.func_147298_b(), parent.func_175105_e());
        this.parent = parent;
    }

    private static GuiScreen getGuiScreen(NetHandlerPlayClient parent) {
        Field[] var1 = parent.getClass().getDeclaredFields();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Field field = var1[var3];
            if (field.getType().equals(GuiScreen.class)) {
                field.setAccessible(true);

                try {
                    return (GuiScreen)field.get(parent);
                } catch (Exception var6) {
                    return null;
                }
            }
        }

        return null;
    }

    public void onNetworkTick() {
        ((TimeChangerNetHandler)this.parent).onNetworkTick();
    }

    public void func_147282_a(S01PacketJoinGame p_147282_1_) {
        this.parent.func_147282_a(p_147282_1_);
    }

    public void func_147235_a(S0EPacketSpawnObject p_147235_1_) {
        this.parent.func_147235_a(p_147235_1_);
    }

    public void func_147286_a(S11PacketSpawnExperienceOrb p_147286_1_) {
        this.parent.func_147286_a(p_147286_1_);
    }

    public void func_147292_a(S2CPacketSpawnGlobalEntity p_147292_1_) {
        this.parent.func_147292_a(p_147292_1_);
    }

    public void func_147288_a(S10PacketSpawnPainting p_147288_1_) {
        this.parent.func_147288_a(p_147288_1_);
    }

    public void func_147244_a(S12PacketEntityVelocity p_147244_1_) {
        this.parent.func_147244_a(p_147244_1_);
    }

    public void func_147284_a(S1CPacketEntityMetadata p_147284_1_) {
        this.parent.func_147284_a(p_147284_1_);
    }

    public void func_147237_a(S0CPacketSpawnPlayer p_147237_1_) {
        this.parent.func_147237_a(p_147237_1_);
    }

    public void func_147275_a(S18PacketEntityTeleport p_147275_1_) {
        this.parent.func_147275_a(p_147275_1_);
    }

    public void func_147257_a(S09PacketHeldItemChange p_147257_1_) {
        this.parent.func_147257_a(p_147257_1_);
    }

    public void func_147259_a(S14PacketEntity p_147259_1_) {
        this.parent.func_147259_a(p_147259_1_);
    }

    public void func_147267_a(S19PacketEntityHeadLook p_147267_1_) {
        this.parent.func_147267_a(p_147267_1_);
    }

    public void func_147238_a(S13PacketDestroyEntities p_147238_1_) {
        this.parent.func_147238_a(p_147238_1_);
    }

    public void func_147258_a(S08PacketPlayerPosLook p_147258_1_) {
        this.parent.func_147258_a(p_147258_1_);
    }

    public void func_147287_a(S22PacketMultiBlockChange p_147287_1_) {
        this.parent.func_147287_a(p_147287_1_);
    }

    public void func_147263_a(S21PacketChunkData p_147263_1_) {
        this.parent.func_147263_a(p_147263_1_);
    }

    public void func_147234_a(S23PacketBlockChange p_147234_1_) {
        this.parent.func_147234_a(p_147234_1_);
    }

    public void func_147253_a(S40PacketDisconnect p_147253_1_) {
        this.parent.func_147253_a(p_147253_1_);
    }

    public void func_147297_a(Packet p_147297_1_) {
        this.parent.func_147297_a(p_147297_1_);
    }

    public void func_147231_a(IChatComponent p_147231_1_) {
        this.parent.func_147231_a(p_147231_1_);
    }

    public void func_147246_a(S0DPacketCollectItem p_147246_1_) {
        this.parent.func_147246_a(p_147246_1_);
    }

    public void func_147251_a(S02PacketChat p_147251_1_) {
        this.parent.func_147251_a(p_147251_1_);
    }

    public void func_147279_a(S0BPacketAnimation p_147279_1_) {
        this.parent.func_147279_a(p_147279_1_);
    }

    public void func_147278_a(S0APacketUseBed p_147278_1_) {
        this.parent.func_147278_a(p_147278_1_);
    }

    public void func_147281_a(S0FPacketSpawnMob p_147281_1_) {
        this.parent.func_147281_a(p_147281_1_);
    }

    public void func_147285_a(S03PacketTimeUpdate packet) {
        if (!TimeChanger.fastTime && !TimeChanger.isVanilla) {
            this.parent.func_147285_a(new S03PacketTimeUpdate(packet.func_149365_d(), TimeChanger.getTime(), false));
        } else if (TimeChanger.isVanilla) {
            this.parent.func_147285_a(packet);
        }
    }

    public void func_147271_a(S05PacketSpawnPosition p_147271_1_) {
        this.parent.func_147271_a(p_147271_1_);
    }

    public void func_147243_a(S1BPacketEntityAttach p_147243_1_) {
        this.parent.func_147243_a(p_147243_1_);
    }

    public void func_147236_a(S19PacketEntityStatus p_147236_1_) {
        this.parent.func_147236_a(p_147236_1_);
    }

    public void func_147249_a(S06PacketUpdateHealth p_147249_1_) {
        this.parent.func_147249_a(p_147249_1_);
    }

    public void func_147295_a(S1FPacketSetExperience p_147295_1_) {
        this.parent.func_147295_a(p_147295_1_);
    }

    public void func_147280_a(S07PacketRespawn p_147280_1_) {
        this.parent.func_147280_a(p_147280_1_);
    }

    public void func_147283_a(S27PacketExplosion p_147283_1_) {
        this.parent.func_147283_a(p_147283_1_);
    }

    public void func_147265_a(S2DPacketOpenWindow p_147265_1_) {
        this.parent.func_147265_a(p_147265_1_);
    }

    public void func_147266_a(S2FPacketSetSlot p_147266_1_) {
        this.parent.func_147266_a(p_147266_1_);
    }

    public void func_147239_a(S32PacketConfirmTransaction p_147239_1_) {
        this.parent.func_147239_a(p_147239_1_);
    }

    public void func_147241_a(S30PacketWindowItems p_147241_1_) {
        this.parent.func_147241_a(p_147241_1_);
    }

    public void func_147268_a(S36PacketSignEditorOpen p_147268_1_) {
        this.parent.func_147268_a(p_147268_1_);
    }

    public void func_147248_a(S33PacketUpdateSign p_147248_1_) {
        this.parent.func_147248_a(p_147248_1_);
    }

    public void func_147273_a(S35PacketUpdateTileEntity p_147273_1_) {
        this.parent.func_147273_a(p_147273_1_);
    }

    public void func_147245_a(S31PacketWindowProperty p_147245_1_) {
        this.parent.func_147245_a(p_147245_1_);
    }

    public void func_147242_a(S04PacketEntityEquipment p_147242_1_) {
        this.parent.func_147242_a(p_147242_1_);
    }

    public void func_147276_a(S2EPacketCloseWindow p_147276_1_) {
        this.parent.func_147276_a(p_147276_1_);
    }

    public void func_147261_a(S24PacketBlockAction p_147261_1_) {
        this.parent.func_147261_a(p_147261_1_);
    }

    public void func_147294_a(S25PacketBlockBreakAnim p_147294_1_) {
        this.parent.func_147294_a(p_147294_1_);
    }

    public void func_147269_a(S26PacketMapChunkBulk p_147269_1_) {
        this.parent.func_147269_a(p_147269_1_);
    }

    public void func_147252_a(S2BPacketChangeGameState packet) {
        this.parent.func_147252_a(packet);
    }

    public void func_147264_a(S34PacketMaps p_147264_1_) {
        this.parent.func_147264_a(p_147264_1_);
    }

    public void func_147277_a(S28PacketEffect p_147277_1_) {
        this.parent.func_147277_a(p_147277_1_);
    }

    public void func_175098_a(S42PacketCombatEvent packetIn) {
        this.parent.func_175098_a(packetIn);
    }

    public void func_175101_a(S41PacketServerDifficulty packetIn) {
        this.parent.func_175101_a(packetIn);
    }

    public void func_175094_a(S43PacketCamera packetIn) {
        this.parent.func_175094_a(packetIn);
    }

    public void func_175093_a(S44PacketWorldBorder packetIn) {
        this.parent.func_175093_a(packetIn);
    }

    public void func_175099_a(S45PacketTitle packetIn) {
        this.parent.func_175099_a(packetIn);
    }

    public void func_147293_a(S37PacketStatistics p_147293_1_) {
        this.parent.func_147293_a(p_147293_1_);
    }

    public void func_175100_a(S46PacketSetCompressionLevel packetIn) {
        this.parent.func_175100_a(packetIn);
    }

    public void func_175096_a(S47PacketPlayerListHeaderFooter packetIn) {
        this.parent.func_175096_a(packetIn);
    }

    public void func_147260_a(S1DPacketEntityEffect p_147260_1_) {
        this.parent.func_147260_a(p_147260_1_);
    }

    public void func_147262_a(S1EPacketRemoveEntityEffect p_147262_1_) {
        this.parent.func_147262_a(p_147262_1_);
    }

    public void func_147256_a(S38PacketPlayerListItem p_147256_1_) {
        this.parent.func_147256_a(p_147256_1_);
    }

    public void func_147272_a(S00PacketKeepAlive p_147272_1_) {
        this.parent.func_147272_a(p_147272_1_);
    }

    public void func_147270_a(S39PacketPlayerAbilities p_147270_1_) {
        this.parent.func_147270_a(p_147270_1_);
    }

    public void func_147274_a(S3APacketTabComplete p_147274_1_) {
        this.parent.func_147274_a(p_147274_1_);
    }

    public void func_147255_a(S29PacketSoundEffect p_147255_1_) {
        this.parent.func_147255_a(p_147255_1_);
    }

    public void func_175095_a(S48PacketResourcePackSend packetIn) {
        this.parent.func_175095_a(packetIn);
    }

    public void func_175097_a(S49PacketUpdateEntityNBT packetIn) {
        this.parent.func_175097_a(packetIn);
    }

    public void func_147240_a(S3FPacketCustomPayload p_147240_1_) {
        this.parent.func_147240_a(p_147240_1_);
    }

    public void func_147291_a(S3BPacketScoreboardObjective p_147291_1_) {
        this.parent.func_147291_a(p_147291_1_);
    }

    public void func_147250_a(S3CPacketUpdateScore p_147250_1_) {
        this.parent.func_147250_a(p_147250_1_);
    }

    public void func_147254_a(S3DPacketDisplayScoreboard p_147254_1_) {
        this.parent.func_147254_a(p_147254_1_);
    }

    public void func_147247_a(S3EPacketTeams p_147247_1_) {
        this.parent.func_147247_a(p_147247_1_);
    }

    public void func_147289_a(S2APacketParticles p_147289_1_) {
        this.parent.func_147289_a(p_147289_1_);
    }

    public void func_147290_a(S20PacketEntityProperties p_147290_1_) {
        this.parent.func_147290_a(p_147290_1_);
    }
}
