package frozenblock.wild.mod.registry;

import frozenblock.wild.mod.WildMod;
import frozenblock.wild.mod.entity.FrogEntity;
import frozenblock.wild.mod.entity.WardenEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterEntities {

    public static final EntityType<FrogEntity> FROG = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(WildMod.MOD_ID, "frog"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FrogEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    public static final EntityType<WardenEntity> WARDEN = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(WildMod.MOD_ID, "warden"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WardenEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    public static void RegisterEntities() {
        FabricDefaultAttributeRegistry.register(FROG, FrogEntity.createMobAttributes());
        FabricDefaultAttributeRegistry.register(WARDEN, WardenEntity.createMobAttributes());
    }
}