package com.baloooouu.LizardmanShamanMinionAlert;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class LizardmanShamanMinionAlertPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(LizardmanShamanMinionAlertPlugin.class);
		RuneLite.main(args);
	}
}