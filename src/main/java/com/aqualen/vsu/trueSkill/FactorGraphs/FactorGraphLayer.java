package com.aqualen.vsu.trueSkill.FactorGraphs;

import com.aqualen.vsu.entity.User;
import com.aqualen.vsu.trueSkill.FactorGraphs.schedule.Schedule;
import com.aqualen.vsu.trueSkill.FactorGraphs.schedule.ScheduleSequence;
import com.aqualen.vsu.trueSkill.FactorGraphs.variable.KeyedVariable;
import com.aqualen.vsu.trueSkill.GameInfo;
import com.aqualen.vsu.trueSkill.GaussianDistribution;
import com.aqualen.vsu.trueSkill.Player;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class FactorGraphLayer<T> {

    private final List<Factor<T>> localFactors = new ArrayList<>();
    private final List<KeyedVariable<Player, GaussianDistribution>> outputVariables = new ArrayList<>();
    private List<KeyedVariable<Player, GaussianDistribution>> inputVariables = new ArrayList<>();

    public void addToOutputVariables(KeyedVariable<Player, GaussianDistribution> variable) {
        outputVariables.add(variable);
    }


    public void setRawInputVariablesGroups(Object value) {
        if (value == null) {
            // TODO: message
            throw new IllegalArgumentException();
        }

        inputVariables = (List<KeyedVariable<Player, GaussianDistribution>>) value;
    }

    public abstract void buildLayer(GameInfo gameInfo, List<Player> players);

    public Object getRawOutputVariablesGroups() {
        return outputVariables;
    }

    protected void addLayerFactor(Factor<T> factor) {
        localFactors.add(factor);
    }

    protected Schedule<GaussianDistribution> scheduleSequence(List<Schedule<GaussianDistribution>> itemsToSequence,
                                                              String nameFormat,
                                                              Object[]... args) {
        String formattedName = String.format(nameFormat, args);
        return new ScheduleSequence(formattedName, itemsToSequence);
    }
}
