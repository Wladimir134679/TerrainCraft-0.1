package com.wdeath.tc.world;

public class WorldFilters {

    public static final short CAT_PLAYER_BODY = 0x0001;
    public static final short CAT_PLAYER_ALIVE = 0x0004;
    public static final short CAT_GROUND = 0x0002;

    public static final short MASK_PLAYER = CAT_PLAYER_ALIVE | CAT_PLAYER_BODY;
    public static final short MASK_WORLD = CAT_GROUND | MASK_PLAYER;

    public static final short GROUP_PLAYER = 1;
    public static final short GROUP_WORLD = 2;

}
