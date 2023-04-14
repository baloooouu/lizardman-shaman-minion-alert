package com.baloooouu.LizardmanShamanMinionAlert.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Alert {
    SQUEAK("Squeak", 3816),
    BELL("Bell", 3817);

    private final String soundEffectName;
    private final int soundEffectId;

    @Override
    public String toString()
    {
        return soundEffectName;
    }
}
