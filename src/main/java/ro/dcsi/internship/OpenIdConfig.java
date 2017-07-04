package ro.dcsi.internship;

public class OpenIdConfig {
  final String openIDMServer;
  final String openIDMUsername;
  final String openIDMPassword;

  public OpenIdConfig(String openIDMServer, String openIDMUsername, String openIDMPassword) {
    this.openIDMServer = openIDMServer;
    this.openIDMUsername = openIDMUsername;
    this.openIDMPassword = openIDMPassword;
  }
}
