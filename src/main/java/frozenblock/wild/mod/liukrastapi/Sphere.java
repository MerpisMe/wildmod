package frozenblock.wild.mod.liukrastapi;

import frozenblock.wild.mod.registry.RegisterBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;

public abstract class Sphere {

    public static ArrayList<BlockPos> checkSphereSolid(BlockState state, World world, BlockPos pos, Integer radius, boolean defaultstate) {

        int fixedradius = radius - 1;

        ArrayList<BlockPos> exitList = new ArrayList<BlockPos>();

        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        double sx = fixedradius * -1;
        double sy = 0;
        double sz = 0;

        for (int index0 = 0; index0 < (int) ((radius * 2) - 1); index0++) {
            sy = fixedradius * -1;
            for (int index1 = 0; index1 < (int) ((radius * 2) - 1); index1++) {
                sz = fixedradius * -1;
                for (int index2 = 0; index2 < (int) ((radius * 2) - 1); index2++) {
                    if (Math.sqrt(Math.pow(sx, 2) + Math.pow(sy, 2) + Math.pow(sz, 2)) <= radius) {
                        if(defaultstate) {
                            if (world.getBlockState(new BlockPos(x + sx, y + sy, z + sz)).isSolidBlock(world, new BlockPos(x + sx, y + sy, z + sz))) {
                                exitList.add(new BlockPos(x + sx, y + sy, z + sz));
                            }
                        }
                    }
                    sz = sz + 1;
                }
                sy = sy + 1;
            }
            sx = sx + 1;
        }
        return exitList;
    }

    public static ArrayList<BlockPos> checkSpherePos(BlockState state, World world, BlockPos pos, Integer radius, boolean defaultstate) {

        int fixedradius = radius - 1;

        ArrayList<BlockPos> exitList = new ArrayList<BlockPos>();

        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        double sx = fixedradius * -1;
        double sy = 0;
        double sz = 0;

        for (int index0 = 0; index0 < (int) ((radius * 2) - 1); index0++) {
            sy = fixedradius * -1;
            for (int index1 = 0; index1 < (int) ((radius * 2) - 1); index1++) {
                sz = fixedradius * -1;
                for (int index2 = 0; index2 < (int) ((radius * 2) - 1); index2++) {
                    if (Math.sqrt(Math.pow(sx, 2) + Math.pow(sy, 2) + Math.pow(sz, 2)) <= radius) {
                        if(defaultstate) {
                            if (world.getBlockState(new BlockPos(x + sx, y + sy, z + sz)) == state) {
                                exitList.add(new BlockPos(x + sx, y + sy, z + sz));
                            }
                        } else {
                            if(world.getBlockState(new BlockPos(x + sx, y + sy, z + sz)).getBlock().getDefaultState() == state.getBlock().getDefaultState()) {
                                exitList.add(new BlockPos(x + sx, y + sy, z + sz));
                            }
                        }
                    }
                    sz = sz + 1;
                }
                sy = sy + 1;
            }
            sx = sx + 1;
        }
        return exitList;
    }

    public static boolean checkSphere(BlockState state, World world, BlockPos pos, Integer radius) {

        int fixedradius = radius - 1;

        boolean exit = false;

        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        double sx = fixedradius * -1;
        double sy = 0;
        double sz = 0;

        for (int index0 = 0; index0 < (int) ((radius * 2) - 1); index0++) {
            sy = fixedradius * -1;
            for (int index1 = 0; index1 < (int) ((radius * 2) - 1); index1++) {
                sz = fixedradius * -1;
                for (int index2 = 0; index2 < (int) ((radius * 2) - 1); index2++) {
                    if (Math.sqrt(Math.pow(sx, 2) + Math.pow(sy, 2) + Math.pow(sz, 2)) <= radius) {
                        if (world.getBlockState(new BlockPos(x + sx, y + sy, z + sz)) == state) {
                            exit = true;
                        }
                    }
                    sz = sz + 1;
                }
                sy = sy + 1;
            }
            sx = sx + 1;
        }
        return exit;
    }

    public static boolean sphereBlock(Block block, World world, BlockPos pos, Integer radius) {

        int fixedradius = radius - 1;

        boolean exit = false;

        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();

        double sx = fixedradius * -1;
        double sy = 0;
        double sz = 0;

        for (int index0 = 0; index0 < ((radius * 2) - 1); index0++) {
            sy = fixedradius * -1;
            for (int index1 = 0; index1 < ((radius * 2) - 1); index1++) {
                sz = fixedradius * -1;
                for (int index2 = 0; index2 < ((radius * 2) - 1); index2++) {
                    if (Math.sqrt(Math.pow(sx, 2) + Math.pow(sy, 2) + Math.pow(sz, 2)) <= radius) {
                        if (world.getBlockState(new BlockPos(x + sx, y + sy, z + sz)).getBlock() == block) {
                            exit = true;
                        }
                    }
                    sz = sz + 1;
                }
                sy = sy + 1;
            }
            sx = sx + 1;
        }
        return exit;
    }

    public static int generateSphere(BlockPos centerBlock, int radius, boolean hollow, World world) {
        if (centerBlock == null) {
            return 1;
        }

        int circleBlocks = 0;

        int bx = centerBlock.getX();
        int by = centerBlock.getY();
        int bz = centerBlock.getZ();

        for(int x = bx - radius; x <= bx + radius; x++) {
            for(int y = by - radius; y <= by + radius; y++) {
                for(int z = bz - radius; z <= bz + radius; z++) {

                    double distance = ((bx-x) * (bx-x) + ((bz-z) * (bz-z)) + ((by-y) * (by-y)));

                    if(distance < radius * radius && !(hollow && distance < ((radius - 1) * (radius - 1)))) {

                        BlockPos l = new BlockPos(x, y, z);
                        if (world.getBlockState(l).getBlock()== RegisterBlocks.SCULK_CATALYST) {

                            circleBlocks = circleBlocks+1;
                        }
                    }

                }
            }
        }

        return circleBlocks;
    }

}
