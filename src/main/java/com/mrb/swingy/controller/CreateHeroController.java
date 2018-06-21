package com.mrb.swingy.controller;

import com.mrb.swingy.exception.HeroValidationException;
import com.mrb.swingy.model.Game;
import com.mrb.swingy.model.character.Hero;
import com.mrb.swingy.model.character.HeroFactory;
import com.mrb.swingy.view.create.CreateHeroView;

/**
 * Created by chvs on 19.06.2018.
 */
public class CreateHeroController {

    private CreateHeroView view;
    private Game game;

    public CreateHeroController(CreateHeroView view){
        this.view = view;
        game = Game.getInstance();
    }

    public void onCreateButtonPressed(String name, String heroClass){
        System.out.println("Controller: " + name + " " + heroClass);
        Hero hero;
        try {
            hero = HeroFactory.newHero(name, heroClass);
            hero.validateHero();
        } catch (IllegalArgumentException | HeroValidationException e){
            view.showErrorMessage(e.getMessage());
            view.getUserInput();
            return;
        }
        game.initGame(hero);
        view.openGame();
    }
}