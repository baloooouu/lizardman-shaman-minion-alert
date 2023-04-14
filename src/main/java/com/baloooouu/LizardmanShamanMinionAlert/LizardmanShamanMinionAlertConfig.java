package com.baloooouu.LizardmanShamanMinionAlert;

import com.baloooouu.LizardmanShamanMinionAlert.config.Alert;
import net.runelite.api.SoundEffectVolume;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("lizardmanshamanminionalert")
public interface LizardmanShamanMinionAlertConfig extends Config
{
	int VOLUME_MAX = SoundEffectVolume.HIGH;

	@Range(
			min = 1,
			max = 24
	)
	@ConfigItem(
			keyName = "maxSpawnDistance",
			name = "Maximum Distance",
			description = "Configures the distance, in tiles, in which it will detect Spawns.",
			position = 0
	)
	default int maxSpawnDistance()
	{
		return 16;
	}

	@Range(
			max = VOLUME_MAX
	)
	@ConfigItem(
			keyName = "volume",
			name = "Volume",
			description = "Configures the volume of the alert.",
			position = 1
	)
	default int volume()
	{
		return SoundEffectVolume.MEDIUM_HIGH;
	}

	@ConfigItem(
			keyName = "soundEffect",
			name = "Sound effect",
			description = "Configures the sound it plays when alerting.",
			position = 2
	)
	default Alert soundEffect() {
		return Alert.SQUEAK;
	}
}
