package io.namoosori.travelclub.spring;

import io.namoosori.travelclub.spring.mapstore.ClubMapStore;
import io.namoosori.travelclub.spring.mapstore.MemberMapStore;
import io.namoosori.travelclub.spring.mapstore.MembershipMapStore;
import io.namoosori.travelclub.spring.mapstore.SequenceMapStore;
import io.namoosori.travelclub.spring.logic.ClubServiceLogic;
import io.namoosori.travelclub.spring.logic.MemberServiceLogic;
import io.namoosori.travelclub.spring.logic.MembershipServiceLogic;
import io.namoosori.travelclub.spring.service.ClubService;
import io.namoosori.travelclub.spring.service.MemberService;
import io.namoosori.travelclub.spring.service.MembershipService;
import io.namoosori.travelclub.spring.store.ClubStore;
import io.namoosori.travelclub.spring.store.SequenceStore;
import io.namoosori.travelclub.spring.store.MemberStore;
import io.namoosori.travelclub.spring.store.MembershipStore;
import io.namoosori.travelclub.spring.ui.console.ClubConsole;
import io.namoosori.travelclub.spring.ui.console.MemberConsole;
import io.namoosori.travelclub.spring.ui.console.MembershipConsole;
import io.namoosori.travelclub.spring.ui.menu.ClubMenu;
import io.namoosori.travelclub.spring.ui.menu.MainMenu;
import io.namoosori.travelclub.spring.ui.menu.MemberMenu;
import io.namoosori.travelclub.spring.ui.menu.MembershipMenu;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("io.namoosori.travelclub.spring")
public class TravelClubConfig {
    //
    /* Store */
    @Bean
    public ClubStore clubStore() {
        //
        return new ClubMapStore();
    }

    @Bean
    public MemberStore memberStore() {
        //
        return new MemberMapStore();
    }

    @Bean
    public MembershipStore membershipStore() {
        //
        return new MembershipMapStore();
    }

    @Bean
    public SequenceStore sequenceStore() {
        //
        return new SequenceMapStore();
    }

    /* Service */
    @Bean
    public ClubService clubService() {
        //
        return new ClubServiceLogic();
    }

    @Bean
    public MemberService memberService() {
        //
        return new MemberServiceLogic();
    }

    @Bean
    public MembershipService membershipService() {
        //
        return new MembershipServiceLogic();
    }

    /* Console */
    @Bean
    public ClubConsole clubConsole() {
        //
        return new ClubConsole();
    }

    @Bean
    public MemberConsole memberConsole() {
        //
        return new MemberConsole();
    }

    @Bean
    public MembershipConsole membershipConsole() {
        //
        return new MembershipConsole();
    }

    /* Menu */
    @Bean
    public MainMenu mainMenu() {
        //
        return new MainMenu();
    }

    @Bean
    public ClubMenu clubMenu() {
        //
        return new ClubMenu();
    }

    @Bean
    public MemberMenu memberMenu() {
        //
        return new MemberMenu();
    }

    @Bean
    public MembershipMenu membershipMenu() {
        //
        return new MembershipMenu();
    }
}
