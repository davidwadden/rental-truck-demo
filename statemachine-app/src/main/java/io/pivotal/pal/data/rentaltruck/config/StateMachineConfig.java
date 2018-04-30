package io.pivotal.pal.data.rentaltruck.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states) throws Exception {
        states.withStates()
                .initial(States.START)
                .end(States.END)
                .states(new HashSet<>(Arrays.asList(States.STATE1, States.STATE2)));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions) throws Exception {
        transitions
                .withExternal()
                .source(States.START).target(States.STATE1).event(Events.EVENT1)
                .and()
                .withExternal()
                .source(States.STATE1).target(States.STATE2).event(Events.EVENT2)
                .and()
                .withExternal()
                .source(States.STATE2).target(States.END).event(Events.EVENT3);
    }
}

enum States {
    START, STATE1, STATE2, END
}

enum Events {
    EVENT1, EVENT2, EVENT3
}
