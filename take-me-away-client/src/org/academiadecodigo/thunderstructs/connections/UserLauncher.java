package org.academiadecodigo.thunderstructs.connections;


public class UserLauncher {
    public static void main(String[] args) {
      UserFactory userFactory = new UserFactory(args[0], Integer.parseInt(args[1]));
      userFactory.setDisplay();

    }

}
