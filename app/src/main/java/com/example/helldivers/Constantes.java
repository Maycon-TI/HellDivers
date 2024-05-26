package com.example.helldivers;

public enum Constantes {
    GALACTIC_WAR("| GALACTIC WAR |"),
    SHIP_MANAGEMENT("| SHIP MANAGEMENT |"),
    ARMORY("| ARMORY |"),
    ACQUISITIONS("| ACQUISITIONS |"),
    ORDERS("| ORDERS |"),
    DESTROYER("| DESTROYER |"),
    STRATAGEMS("| STRATAGEMS |"),
    SHIP_MODULE("| SHIP MODULE |"),
    WEAPONRY("| WEAPONRY |"),
    CHARACTER("| CHARACTER |"),
    BOOSTER("| BOOSTER |"),
    CAREER("| CAREER |"),
    WARBONDS("| WARBONDS |"),
    SUPERSTORE("| SUPERSTORE |"),
    SUPER_CREDITS("| SUPER CREDITS |"),
    GAME("| GAME |"),
    OPTIONS("| OPTIONS |"),
    PLAY_TUTORIAL("| PLAY TUTORIAL |"),
    CREDITS("| CREDITS |"),
    QUIT_GAME("| QUIT GAME |"),

    VERSION("0.0.0v"),

    NOTE("NOTE: Some game data are stored locally, and cannot" +
            "\n be restored if you delete the game."),
    AUTOMATONS("Automatons"),
    TERMINDS("Terminds"),
    SUPER_EARTH("Super Earth"),

    TRIVIAL("1 - Trivial"),
    EASY("2 - Easy"),
    MEDIUM("3 - Medium"),
    CHALLENGING("4 - Challenging"),
    HARD("5 - Hard"),
    EXTREME("6 - Extreme"),
    SUICIDE_MISSION("7 - Suicide Mission"),
    IMPOSSIBLE("8 - Impossible"),
    HELLDIVE("8 - Helldive");
    private final String description;


    Constantes(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
