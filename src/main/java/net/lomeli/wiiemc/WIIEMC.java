package net.lomeli.wiiemc;

import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import net.lomeli.wiiemc.config.ModConfig;

@Mod(modid = WIIEMC.MOD_ID, name = WIIEMC.MOD_NAME, version = WIIEMC.VERSION, dependencies = WIIEMC.DEPENDENCIES, guiFactory = WIIEMC.FACTORY, acceptedMinecraftVersions = WIIEMC.MINECRAFT_VERSION)
public class WIIEMC {
    public static final String MOD_ID = "wiiemc";
    public static final String MOD_NAME = "What Is Its EMC?";
    public static final int MAJOR = 1, MINOR = 0, REV = 0;
    public static final String VERSION = MAJOR + "." + MINOR + "." + REV;
    public static final String DEPENDENCIES = "required-after:Waila;required-after:EE3";
    public static final String FACTORY = "net.lomeli.wiiemc.config.ConfigFactory";
    public static final String MINECRAFT_VERSION = "1.7.10";

    public static ModConfig config;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new ModConfig(new Configuration(event.getSuggestedConfigurationFile()));
        config.loadConfig();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        config.initEvent();
        FMLInterModComms.sendMessage("Waila", "register", "net.lomeli.wiiemc.EMCDataProvider.callbackRegister");
    }
}
