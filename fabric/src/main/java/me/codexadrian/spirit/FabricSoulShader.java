package me.codexadrian.spirit;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;


public class FabricSoulShader {

    public static ShaderInstance rendertypeTranslucentShader;

    public static <T extends Entity> RenderType getSoulRenderType(T entity, EntityRenderer<T> livingEntity) {
        return RenderType.create(
                "mob_soul_layer_" + entity.getDisplayName().getString(),
                DefaultVertexFormat.NEW_ENTITY,
                VertexFormat.Mode.QUADS,
                256,
                true, true,
                RenderType.CompositeState
                        .builder()
                        .setShaderState(new RenderStateShard.ShaderStateShard(() -> rendertypeTranslucentShader))
                        .setTextureState(new RenderStateShard.TextureStateShard(livingEntity.getTextureLocation(entity), false, false))
                        .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                        .setLightmapState(RenderStateShard.LIGHTMAP)
                        .createCompositeState(true)
        );
    }
}
