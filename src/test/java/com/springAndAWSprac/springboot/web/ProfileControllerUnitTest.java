package com.springAndAWSprac.springboot.web;

import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.*;

public class ProfileControllerUnitTest {

    @Test
    public void initializing_real_profile(){
        String exprectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(exprectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        String profile = controller.profile();

        assertThat(profile).isEqualTo(exprectedProfile);
    }

    @Test
    public void get_Active_Profile_else_get_default(){
        String expectedProfile = "default";
        MockEnvironment env = new MockEnvironment();
        ProfileController controller = new ProfileController(env);

        String profile = controller.profile();

        assertThat(profile).isEqualTo(expectedProfile);
    }

}