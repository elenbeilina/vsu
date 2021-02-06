package com.aqualen.vsu.trueSkill.layers;

import com.aqualen.vsu.trueSkill.FactorGraphs.FactorGraphLayer;
import com.aqualen.vsu.trueSkill.FactorGraphs.schedule.Schedule;
import com.aqualen.vsu.trueSkill.GameInfo;
import com.aqualen.vsu.trueSkill.Player;

import java.util.List;

public abstract class TrueSkillFactorGraphLayer<TValue> extends FactorGraphLayer {
    public abstract void buildLayer(GameInfo gameInfo, List<Player> players);

    public Schedule<TValue> createPriorSchedule() {
        return null;
    }

    public Schedule<TValue> createPosteriorSchedule() {
        return null;
    }

}
