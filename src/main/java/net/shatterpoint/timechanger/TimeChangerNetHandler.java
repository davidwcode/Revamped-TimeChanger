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
        super(Minecraft.getMinecraft(), getGuiScreen(parent), parent.getNetworkManager(), parent.getGameProfile());
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

    public void handleJoinGame(S01PacketJoinGame p_147282_1_) {
        this.parent.handleJoinGame(p_147282_1_);
    }

    public void handleSpawnObject(S0EPacketSpawnObject p_147235_1_) {
        this.parent.handleSpawnObject(p_147235_1_);
    }

    public void handleSpawnExperienceOrb(S11PacketSpawnExperienceOrb p_147286_1_) {
        this.parent.handleSpawnExperienceOrb(p_147286_1_);
    }

    public void handleSpawnGlobalEntity(S2CPacketSpawnGlobalEntity p_147292_1_) {
        this.parent.handleSpawnGlobalEntity(p_147292_1_);
    }

    public void handleSpawnPainting(S10PacketSpawnPainting p_147288_1_) {
        this.parent.handleSpawnPainting(p_147288_1_);
    }

    public void handleEntityVelocity(S12PacketEntityVelocity p_147244_1_) {
        this.parent.handleEntityVelocity(p_147244_1_);
    }

    public void handleEntityMetadata(S1CPacketEntityMetadata p_147284_1_) {
        this.parent.handleEntityMetadata(p_147284_1_);
    }

    public void handleSpawnPlayer(S0CPacketSpawnPlayer p_147237_1_) {
        this.parent.handleSpawnPlayer(p_147237_1_);
    }

    public void handleEntityTeleport(S18PacketEntityTeleport p_147275_1_) {
        this.parent.handleEntityTeleport(p_147275_1_);
    }

    public void handleHeldItemChange(S09PacketHeldItemChange p_147257_1_) {
        this.parent.handleHeldItemChange(p_147257_1_);
    }

    public void handleEntityMovement(S14PacketEntity p_147259_1_) {
        this.parent.handleEntityMovement(p_147259_1_);
    }

    public void handleEntityHeadLook(S19PacketEntityHeadLook p_147267_1_) {
        this.parent.handleEntityHeadLook(p_147267_1_);
    }

    public void handleDestroyEntities(S13PacketDestroyEntities p_147238_1_) {
        this.parent.handleDestroyEntities(p_147238_1_);
    }

    public void handlePlayerPosLook(S08PacketPlayerPosLook p_147258_1_) {
        this.parent.handlePlayerPosLook(p_147258_1_);
    }

    public void handleMultiBlockChange(S22PacketMultiBlockChange p_147287_1_) {
        this.parent.handleMultiBlockChange(p_147287_1_);
    }

    public void handleChunkData(S21PacketChunkData p_147263_1_) {
        this.parent.handleChunkData(p_147263_1_);
    }

    public void handleBlockChange(S23PacketBlockChange p_147234_1_) {
        this.parent.handleBlockChange(p_147234_1_);
    }

    public void handleDisconnect(S40PacketDisconnect p_147253_1_) {
        this.parent.handleDisconnect(p_147253_1_);
    }

    public void addToSendQueue(Packet p_147297_1_) {
        this.parent.addToSendQueue(p_147297_1_);
    }

    public void onDisconnect(IChatComponent p_147231_1_) {
        this.parent.onDisconnect(p_147231_1_);
    }

    public void handleCollectItem(S0DPacketCollectItem p_147246_1_) {
        this.parent.handleCollectItem(p_147246_1_);
    }

    public void handleChat(S02PacketChat p_147251_1_) {
        this.parent.handleChat(p_147251_1_);
    }

    public void handleAnimation(S0BPacketAnimation p_147279_1_) {
        this.parent.handleAnimation(p_147279_1_);
    }

    public void handleUseBed(S0APacketUseBed p_147278_1_) {
        this.parent.handleUseBed(p_147278_1_);
    }

    public void handleSpawnMob(S0FPacketSpawnMob p_147281_1_) {
        this.parent.handleSpawnMob(p_147281_1_);
    }

    public void handleTimeUpdate(S03PacketTimeUpdate packet) {
        if (!TimeChanger.fastTime && !TimeChanger.isVanilla) {
            this.parent.handleTimeUpdate(new S03PacketTimeUpdate(packet.getWorldTime(), TimeChanger.getTime(), false));
        } else if (TimeChanger.isVanilla) {
            this.parent.handleTimeUpdate(packet);
        }
    }

    public void handleSpawnPosition(S05PacketSpawnPosition p_147271_1_) {
        this.parent.handleSpawnPosition(p_147271_1_);
    }

    public void handleEntityAttach(S1BPacketEntityAttach p_147243_1_) {
        this.parent.handleEntityAttach(p_147243_1_);
    }

    public void handleEntityStatus(S19PacketEntityStatus p_147236_1_) {
        this.parent.handleEntityStatus(p_147236_1_);
    }

    public void handleUpdateHealth(S06PacketUpdateHealth p_147249_1_) {
        this.parent.handleUpdateHealth(p_147249_1_);
    }

    public void handleSetExperience(S1FPacketSetExperience p_147295_1_) {
        this.parent.handleSetExperience(p_147295_1_);
    }

    public void handleRespawn(S07PacketRespawn p_147280_1_) {
        this.parent.handleRespawn(p_147280_1_);
    }

    public void handleExplosion(S27PacketExplosion p_147283_1_) {
        this.parent.handleExplosion(p_147283_1_);
    }

    public void handleOpenWindow(S2DPacketOpenWindow p_147265_1_) {
        this.parent.handleOpenWindow(p_147265_1_);
    }

    public void handleSetSlot(S2FPacketSetSlot p_147266_1_) {
        this.parent.handleSetSlot(p_147266_1_);
    }

    public void handleConfirmTransaction(S32PacketConfirmTransaction p_147239_1_) {
        this.parent.handleConfirmTransaction(p_147239_1_);
    }

    public void handleWindowItems(S30PacketWindowItems p_147241_1_) {
        this.parent.handleWindowItems(p_147241_1_);
    }

    public void handleSignEditorOpen(S36PacketSignEditorOpen p_147268_1_) {
        this.parent.handleSignEditorOpen(p_147268_1_);
    }

    public void handleUpdateSign(S33PacketUpdateSign p_147248_1_) {
        this.parent.handleUpdateSign(p_147248_1_);
    }

    public void handleUpdateTileEntity(S35PacketUpdateTileEntity p_147273_1_) {
        this.parent.handleUpdateTileEntity(p_147273_1_);
    }

    public void handleWindowProperty(S31PacketWindowProperty p_147245_1_) {
        this.parent.handleWindowProperty(p_147245_1_);
    }

    public void handleEntityEquipment(S04PacketEntityEquipment p_147242_1_) {
        this.parent.handleEntityEquipment(p_147242_1_);
    }

    public void handleCloseWindow(S2EPacketCloseWindow p_147276_1_) {
        this.parent.handleCloseWindow(p_147276_1_);
    }

    public void handleBlockAction(S24PacketBlockAction p_147261_1_) {
        this.parent.handleBlockAction(p_147261_1_);
    }

    public void handleBlockBreakAnim(S25PacketBlockBreakAnim p_147294_1_) {
        this.parent.handleBlockBreakAnim(p_147294_1_);
    }

    public void handleMapChunkBulk(S26PacketMapChunkBulk p_147269_1_) {
        this.parent.handleMapChunkBulk(p_147269_1_);
    }

    public void handleChangeGameState(S2BPacketChangeGameState packet) {
        this.parent.handleChangeGameState(packet);
    }

    public void handleMaps(S34PacketMaps p_147264_1_) {
        this.parent.handleMaps(p_147264_1_);
    }

    public void handleEffect(S28PacketEffect p_147277_1_) {
        this.parent.handleEffect(p_147277_1_);
    }

    public void handleCombatEvent(S42PacketCombatEvent packetIn) {
        this.parent.handleCombatEvent(packetIn);
    }

    public void handleServerDifficulty(S41PacketServerDifficulty packetIn) {
        this.parent.handleServerDifficulty(packetIn);
    }

    public void handleCamera(S43PacketCamera packetIn) {
        this.parent.handleCamera(packetIn);
    }

    public void handleWorldBorder(S44PacketWorldBorder packetIn) {
        this.parent.handleWorldBorder(packetIn);
    }

    public void handleTitle(S45PacketTitle packetIn) {
        this.parent.handleTitle(packetIn);
    }

    public void handleStatistics(S37PacketStatistics p_147293_1_) {
        this.parent.handleStatistics(p_147293_1_);
    }

    public void handleSetCompressionLevel(S46PacketSetCompressionLevel packetIn) {
        this.parent.handleSetCompressionLevel(packetIn);
    }

    public void handlePlayerListHeaderFooter(S47PacketPlayerListHeaderFooter packetIn) {
        this.parent.handlePlayerListHeaderFooter(packetIn);
    }

    public void handleEntityEffect(S1DPacketEntityEffect p_147260_1_) {
        this.parent.handleEntityEffect(p_147260_1_);
    }

    public void handleRemoveEntityEffect(S1EPacketRemoveEntityEffect p_147262_1_) {
        this.parent.handleRemoveEntityEffect(p_147262_1_);
    }

    public void handlePlayerListItem(S38PacketPlayerListItem p_147256_1_) {
        this.parent.handlePlayerListItem(p_147256_1_);
    }

    public void handleKeepAlive(S00PacketKeepAlive p_147272_1_) {
        this.parent.handleKeepAlive(p_147272_1_);
    }

    public void handlePlayerAbilities(S39PacketPlayerAbilities p_147270_1_) {
        this.parent.handlePlayerAbilities(p_147270_1_);
    }

    public void handleTabComplete(S3APacketTabComplete p_147274_1_) {
        this.parent.handleTabComplete(p_147274_1_);
    }

    public void handleSoundEffect(S29PacketSoundEffect p_147255_1_) {
        this.parent.handleSoundEffect(p_147255_1_);
    }

    public void handleResourcePack(S48PacketResourcePackSend packetIn) {
        this.parent.handleResourcePack(packetIn);
    }

    public void handleEntityNBT(S49PacketUpdateEntityNBT packetIn) {
        this.parent.handleEntityNBT(packetIn);
    }

    public void handleCustomPayload(S3FPacketCustomPayload p_147240_1_) {
        this.parent.handleCustomPayload(p_147240_1_);
    }

    public void handleScoreboardObjective(S3BPacketScoreboardObjective p_147291_1_) {
        this.parent.handleScoreboardObjective(p_147291_1_);
    }

    public void handleUpdateScore(S3CPacketUpdateScore p_147250_1_) {
        this.parent.handleUpdateScore(p_147250_1_);
    }

    public void handleDisplayScoreboard(S3DPacketDisplayScoreboard p_147254_1_) {
        this.parent.handleDisplayScoreboard(p_147254_1_);
    }

    public void handleTeams(S3EPacketTeams p_147247_1_) {
        this.parent.handleTeams(p_147247_1_);
    }

    public void handleParticles(S2APacketParticles p_147289_1_) {
        this.parent.handleParticles(p_147289_1_);
    }

    public void handleEntityProperties(S20PacketEntityProperties p_147290_1_) {
        this.parent.handleEntityProperties(p_147290_1_);
    }
}
