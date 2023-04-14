package com.baloooouu.LizardmanShamanMinionAlert;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.Arrays;

@Slf4j
@PluginDescriptor(
	name = "Lizardman Shaman Minion Alert"
)
public class LizardmanShamanMinionAlertPlugin extends Plugin
{
	private static final Integer SPAWN_ID = 6768;
	private static Boolean SPAWNS_EXIST_PREVIOUS_TICK = false;
	@Inject
	private Client client;

	@Inject
	private LizardmanShamanMinionAlertConfig config;

	@Override
	protected void startUp() throws Exception
	{
		SPAWNS_EXIST_PREVIOUS_TICK = false;
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		SPAWNS_EXIST_PREVIOUS_TICK = false;
	}

	@Provides
	LizardmanShamanMinionAlertConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LizardmanShamanMinionAlertConfig.class);
	}

	@Subscribe
	public void onGameTick(GameTick tick) {
		boolean spawnsExistCurrentTick = spawnCurrentlyExists();

		if (! SPAWNS_EXIST_PREVIOUS_TICK && spawnsExistCurrentTick) {
			log.info("New Spawns detected.");

			playSound();
		}

		SPAWNS_EXIST_PREVIOUS_TICK = spawnsExistCurrentTick;
	}

	private boolean spawnCurrentlyExists() {
		NPC[] npcs = client.getCachedNPCs();

		return Arrays.stream(npcs).anyMatch(n -> validSpawn(n));
	}

	private boolean validSpawn(NPC npc) {
		if (npc == null) {
			return false;
		}

		if (npc.getId() != SPAWN_ID) {
			return false;
		}

		WorldPoint spawnLocation = npc.getWorldLocation();
		WorldPoint playerLocation = client.getLocalPlayer().getWorldLocation();
		int distance = playerLocation.distanceTo(spawnLocation);

		if (distance > config.maxSpawnDistance()) {
			return false;
		}

		return true;
	}

	private void playSound() {
		if (config.volume() <= 0) {
			return;
		}

		Preferences preferences = client.getPreferences();
		int preferredVolume = preferences.getSoundEffectVolume();

		preferences.setSoundEffectVolume(config.volume());
		client.playSoundEffect(config.soundEffect().getSoundEffectId(), config.volume());

		preferences.setSoundEffectVolume(preferredVolume);
	}
}
