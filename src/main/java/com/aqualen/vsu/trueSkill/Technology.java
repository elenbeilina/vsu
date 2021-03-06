package com.aqualen.vsu.trueSkill;

import com.aqualen.vsu.enums.TournamentLabel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Technology {
    private TournamentLabel language;
    private Rating rating;
    private int percent;

    public int getPercent(){
        return percent == 0 ? 1 : percent;
    }

    @Override
    public String toString() {
        return language.toString();
    }
}
