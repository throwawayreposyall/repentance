package com.gayshit.repentance.util;

import com.gayshit.repentance.util.enums.NPCSkin;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.datafixers.util.Pair;
import net.minecraft.network.protocol.game.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftArmorStand;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class NPC {
    public static ServerPlayer spawnNpc(Player player, double x, double y, double z, float yaw, NPCSkin skin) {
        CraftPlayer craftPlayer = (CraftPlayer) player;

        MinecraftServer server = craftPlayer.getHandle().getServer();
        ServerLevel level = craftPlayer.getHandle().serverLevel().getLevel();

        ServerPlayer npc = new ServerPlayer(server, level, new GameProfile(UUID.randomUUID(), ""));

        npc.setPos(x, y, z);

        String signature = "";
        String texture = "";

        switch (skin) {
            case JESUS -> {
                signature = "Eu8gJCE3kncTvMEvS+rxw745Zxi39fo5J9K+3Tx3XRHWOCP2pIIgRmSG58lANYFnRioTxFVR9OO0+iNxGWuc/wqMF/9Cdkvq+kpQsWD7BU2kd0kDERoyVaMPy1pfHO83GboO9pK5Z9l5Q3PC9Aw+P26/o7fXxkTHLpxzdLYoqdUIcSxB2HHEdIW0hrvV5s6RxN5pmhl9W0P+Qr9LZ5ELyshBC4uYtjB2bUF3YVx8+0womU1skxWFoBoRsRKmbFRYJwrQs2PFZ+CHcrdeQyZnyaTVhVi/HJ16n5GvPMgjlu+aE33OSv0ujQnWzYmzYjcFvTIkwZ1fyEBPaEheYtW6plkHC4+aCZqj2Qj3VRpbzmaOUMYnI9jHcw1ywi8tFtw9uQN/CLMQYNY0lubmu5WTVISpXZ2BTNE5Cj4wutbjNNWOzvQQWiX0Q2gXKC3Km+U9n5TQ5wF0MeljC9O5hLa0BtISvJvzupeTTgTy2smVV5Au/lRWCrKS/JR1TTWT79PV58eS1i3aUYXTD/RFfnWG3mMrpjgBb1ZMj/iOZaDBX/qlPT1vXwtpr789Oqpp+oZsN5CeV1s+CEpCd3PdQISOR8cr29TinbqeRuJvGjdmrivGxBNEpGCSJfVjxxiopDSXBlmBgRMk9D2OFBiIQEaJA2tQ0w5gxacD0C3tTr98e/4=";
                texture = "ewogICJ0aW1lc3RhbXAiIDogMTY5NjAxOTk1Nzk0NiwKICAicHJvZmlsZUlkIiA6ICI5ZTkzM2JjNmU0MmE0MzQxOGFiMzVkNGFkOTEzNDdjNyIsCiAgInByb2ZpbGVOYW1lIiA6ICJTQ1BfNl8yXzgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWIzZTY2YWM5MjBjNzRkOWY1Y2M4YmQzMGY3YmUxOWFiNjA3ODUzYjU5ZDk4MzU3Y2Y1YmY3NzRjMzdlNmE5OSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";

            }
            case NIGGA -> {
                signature = "j1HZdt9SyZS3eC3qE1LPzo/vgaX3Uc6qcMLo0vLMjxUPr4LqIOR/Pzp4Rb3SFmTgQiDO+LymcpoCzYKYVV9iVtkKSj/qL0m0XGBISoGiolN4xO99dZxLBlgp4TdYUFiqBibBMm6bWdsLfSZ2WDxw/D5YIbYl2OHkfjObmcorJWEVzpWUkb1zxwh9tM9rvG9xec+IR+iy7tT2Uigor5PGS+lCCK4Xxf+p9CD33IV/ix9PtQeQ5V2PZGtlDtaOMM0eWTCLl3L8TygOZz1qWyqVtEcljhfxfxOfI5iTzkpKFqBdkY+EdpOQ6n11t7N/CQxk1GB/JA9IErTbnAiKrRWBueICWEwSwnTqOTX5PTiXhR00krg3HYoPExs5xrAbxy/IXBmLR6e6o9SmQx/HGfcbExr9yrnS2VMkwxa37hT3MeSI0VopdTjlemsq8m1UGbWv0WjnresGZhkSqUJ26vRa7S3/IqQ3UOWiNiUuDtPPJvyDruRSI5N2gWFUO+NYuD/I2GboYyKjVDQ0Zw6VqFo7BIR4KzDvnrHlHz6GhJ9s0Fg5x0wzUk2DqsEApJlUPec23wAPmlqHusLthRYQWubDbKoTrza0U8L9ZGnmNwybDySJFhl87jkZVQ3+XVo8XvX3UARGM3mHigfv7K2Y5gwM8Z7rVR5Da1yG1jOoK7fsq5Y=";
                texture = "ewogICJ0aW1lc3RhbXAiIDogMTY5NjA3MTQxOTk5NiwKICAicHJvZmlsZUlkIiA6ICIzODMxZDVhYjZhYTU0YzNkYjg0OGEwMDA5YWE5MTE1OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJHSVJMUE9XRVIxMTI0IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2RiZmE3N2ZhMjA2ZTE1Y2E3ZGNjMjhlMDExMGI1OTk3YjMxNmI0Y2ZjZDc1NWYyNWFjZjI5NzgzNGNmMGJiNmMiCiAgICB9CiAgfQp9";
            }
        }

        npc.getGameProfile().getProperties().put("textures", new Property("textures", texture, signature));

        ServerGamePacketListenerImpl connection = craftPlayer.getHandle().connection;

        connection.send(new ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action.ADD_PLAYER, npc));
        connection.send(new ClientboundAddPlayerPacket(npc));
        connection.send(new ClientboundRotateHeadPacket(npc, (byte) (((yaw % 360) * 256 / 360) - 1)));
        connection.send(new ClientboundMoveEntityPacket.Rot(npc.getBukkitEntity().getEntityId(), (byte) ((yaw%360.)*256/360), (byte) 0, false));
        connection.send(new ClientboundAnimatePacket(npc, ClientboundAnimatePacket.SWING_MAIN_HAND));

        return npc;
    }

    public static void spawnPenis(Player player, double x, double y, double z, int yaw) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        ServerGamePacketListenerImpl connection = craftPlayer.getHandle().connection;

        CraftArmorStand penis = (CraftArmorStand) new ArmorStand(
                craftPlayer.getHandle().serverLevel().getLevel(),
                x,
                y - 1,
                z
        ).getBukkitEntity();

        penis.setInvisible(true);
        penis.setGravity(false);
        penis.setRotation(yaw, 10);

        connection.send(new ClientboundAddEntityPacket(penis.getHandle()));
        connection.send(new ClientboundSetEntityDataPacket(
                penis.getEntityId(),
                penis.getHandle().getEntityData().getNonDefaultValues())
        );

        List<Pair<EquipmentSlot, ItemStack>> equipment = List.of(
                new Pair<>(EquipmentSlot.HEAD, new ItemStack(Items.END_ROD))
        );

        connection.send(new ClientboundSetEquipmentPacket(penis.getEntityId(), equipment));
    }
}
