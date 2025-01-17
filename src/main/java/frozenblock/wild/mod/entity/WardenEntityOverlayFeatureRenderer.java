package frozenblock.wild.mod.entity;

import frozenblock.wild.mod.WildMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class WardenEntityOverlayFeatureRenderer extends EyesFeatureRenderer<WardenEntity, WardenEntityModel> {
    private static final RenderLayer OVERLAY = RenderLayer.getEyes(new Identifier(WildMod.MOD_ID, "textures/entity/warden/warden_overlay.png"));

    public WardenEntityOverlayFeatureRenderer(FeatureRendererContext<WardenEntity, WardenEntityModel> featureRendererContext) {
        super(featureRendererContext);
    }

    public RenderLayer getEyesTexture() {
        return OVERLAY;
    }
}